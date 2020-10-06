package com.edoras.backend.repository;

import com.edoras.backend.domain.PasswordResetToken;
import com.edoras.backend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Optional;

@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

    boolean existsByUser(User user);

    PasswordResetToken findByUser(User user);

    @Transactional
    @Modifying
    @Query("UPDATE PasswordResetToken p SET p.expiredAt = :expiredAt, p.token = :token WHERE p.user.id = :userId")
    void updatePasswordResetTokenByUserId(@Param("userId") Long userId,
                                          @Param("expiredAt") Instant expiredAt,
                                          @Param("token") String token);

    Optional<PasswordResetToken> findByToken(String token);
}
