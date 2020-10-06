package com.edoras.backend.service;

import com.edoras.backend.domain.PasswordResetToken;
import com.edoras.backend.domain.User;
import com.edoras.backend.exception.BadRequestException;
import com.edoras.backend.exception.ExpiredPasswordResetTokenException;
import com.edoras.backend.exception.ResourceNotFoundException;
import com.edoras.backend.repository.PasswordResetTokenRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class PasswordResetTokenService {

    @Value("${password.reset.token.expirationInMs}")
    private String tokenExpirationInMs;

    private final PasswordResetTokenRepository passwordResetTokenRepository;

    public PasswordResetTokenService(PasswordResetTokenRepository passwordResetTokenRepository) {
        this.passwordResetTokenRepository = passwordResetTokenRepository;
    }

    public PasswordResetToken createPasswordResetToken(String token, User user) {
        Instant expiredAt = Instant.now().plusMillis(Long.parseLong(tokenExpirationInMs));

        if (passwordResetTokenRepository.existsByUser(user)) {
            passwordResetTokenRepository.updatePasswordResetTokenByUserId(user.getId(), expiredAt, token);
            return passwordResetTokenRepository.findByUser(user);
        }

        PasswordResetToken passwordResetToken = new PasswordResetToken(token, user, expiredAt);
        return passwordResetTokenRepository.save(passwordResetToken);
    }

    public void validateToken(String token, String userName) {
        PasswordResetToken passwordResetToken = passwordResetTokenRepository.findByToken(token)
                .orElseThrow(() -> new ResourceNotFoundException("Not found reset token for token = " + token));

        if (passwordResetToken.getExpiredAt().isBefore(Instant.now())) {
            throw new ExpiredPasswordResetTokenException("Password Reset Token has been expired!");
        }

        if (!passwordResetToken.getUser().getUserName().equals(userName)) {
            throw new BadRequestException("Username = " + userName + " is not the same as token username!");
        }
    }
}
