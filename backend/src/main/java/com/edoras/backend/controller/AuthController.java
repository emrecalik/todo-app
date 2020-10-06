package com.edoras.backend.controller;

import com.edoras.backend.domain.User;
import com.edoras.backend.domain.dto.*;
import com.edoras.backend.exception.BadRequestException;
import com.edoras.backend.mapper.UserMapper;
import com.edoras.backend.security.JwtTokenUtility;
import com.edoras.backend.security.MyUserDetails;
import com.edoras.backend.security.MyUserDetailsService;
import com.edoras.backend.service.PasswordResetTokenService;
import com.edoras.backend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.util.Calendar;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenUtility jwtTokenUtility;

    private final MyUserDetailsService myUserDetailsService;

    private final UserService userService;

    private final PasswordResetTokenService passwordResetTokenService;

    private MessageSource messageSource;

    @Value("${password.reset.mail.from}")
    private String mailFrom;

    @Value("${password.reset.mail.subject}")
    private String mailSubject;


    public AuthController(AuthenticationManager authenticationManager, JwtTokenUtility jwtTokenUtility,
                          MyUserDetailsService myUserDetailsService, UserService userService,
                          PasswordResetTokenService passwordResetTokenService, MessageSource messageSource) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtility = jwtTokenUtility;
        this.myUserDetailsService = myUserDetailsService;
        this.userService = userService;
        this.passwordResetTokenService = passwordResetTokenService;
        this.messageSource = messageSource;
    }

    @PostMapping("/signin")
    public ResponseEntity<SignInResponseDTO> signIn(@Valid @RequestBody SignInRequestDTO signInRequestDTO) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(signInRequestDTO.getUserName(), signInRequestDTO.getPassword());

        try {
            authenticationManager.authenticate(authenticationToken);
        } catch (Exception exc) {
            throw new BadRequestException("Invalid username or password!");
        }

        UserDetails userDetails = myUserDetailsService.loadUserByUsername(signInRequestDTO.getUserName());

        String jwtToken = jwtTokenUtility.generateToken(userDetails);

        return ResponseEntity.ok(new SignInResponseDTO(true, jwtToken, userDetails.getUsername(), ((MyUserDetails) userDetails).getId()));
    }

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDTO> signUp(@Valid @RequestBody SignUpRequestDTO signUpRequestDTO) {
        if (userService.existsByUserName(signUpRequestDTO.getUserName())) {
            throw new BadRequestException("Username is already taken!");
        }

        if (userService.existsByEmail(signUpRequestDTO.getEmail())) {
            throw  new BadRequestException("Email is already taken!");
        }

        User userRegistered = userService.registerUser(signUpRequestDTO);

        URI uriLocation = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/user/{userName}").buildAndExpand(signUpRequestDTO.getUserName()).toUri();

        return ResponseEntity.created(uriLocation).body(UserMapper.convertUserToSignUpResponseDto(userRegistered));
    }

    @PostMapping("/reset")
    public ResponseEntity<SimpleMailMessage> resetPassword(@Valid @RequestBody PasswordResetRequestDTO passwordResetRequestDTO,
                              HttpServletRequest request) {
        if (!userService.existsByUserNameAndEmail(passwordResetRequestDTO.getUserName(),
                passwordResetRequestDTO.getEmail())) {
            throw new BadRequestException("User does not exist!");
        }
        String resetToken = UUID.randomUUID().toString();
        User user = userService.getByUserName(passwordResetRequestDTO.getUserName());
        passwordResetTokenService.createPasswordResetToken(resetToken, user);

//        mailSender.send(constructResetTokenEmail(getAppUrl(request), resetToken, user));

        return ResponseEntity.ok(constructResetTokenEmail(getAppUrl(request), resetToken, user));
    }

    private SimpleMailMessage constructResetTokenEmail(String contextPath, String token, User user) {
        String url = contextPath + "/api/auth/" + user.getUserName() + "/changePassword?token=" + token;
        return constructEmail(url, user);
    }

    private SimpleMailMessage constructEmail(String body, User user) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject(mailSubject);
        email.setSentDate(Calendar.getInstance().getTime());
        email.setText(body);
        email.setTo(user.getEmail());
        email.setFrom(mailFrom);
        return email;
    }

    private String getAppUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }

    @PostMapping("/{userName}/changePassword")
    public ResponseEntity<ApiResponseDTO> saveNewPassword(@PathVariable String userName,
                                                          @RequestParam String token,
                                                          @Valid @RequestBody NewPasswordDTO newPasswordDTO) {
        passwordResetTokenService.validateToken(token, userName);
        userService.updateUserPassword(userName, newPasswordDTO.getNewPassword());
        return ResponseEntity.ok(new ApiResponseDTO(true,
                "User " + userName + " password has been successfully changed."));
    }
}
