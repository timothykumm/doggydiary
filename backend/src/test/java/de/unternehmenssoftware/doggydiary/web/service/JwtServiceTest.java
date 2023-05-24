package de.unternehmenssoftware.doggydiary.web.service;

import de.unternehmenssoftware.doggydiary.web.entity.dao.UserEntity;
import io.jsonwebtoken.JwtException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class JwtServiceTest {

    final UserDetails userDetails = new UserEntity("test@gmail.com", "Reiner", "Wahnsinn", "12345");
    final String validToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJ0ZXN0QGdtYWlsLmNvbSIsImV4cCI6MTY4NDQxODMwOTksImlhdCI6MTUxNjIzOTAyMn0.vYmEJqzcWpPQdsHQbq0qCPn30fZjg1W5qToXrBdcNfA";
    final String invalidToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJ0ZXN0QGdtYWlsLmNvbSIsImV4cCI6MTY4NDQxODMwOTksImlhdCI6MTUxNjIzOTAyMn0.rdvNherDW9vEBsLjO-hCIfxFGUt0gNyX9zz6dTrwF8c";

    JwtService jwtService = new JwtService();

    @Test
    void generateTokenWithUserDetails() {
        String token = jwtService.generateToken(userDetails);

        assertTrue(token.startsWith("ey"));
    }

    @Test
    void extractEmail() {
        String token = jwtService.extractEmail(validToken);

        assertEquals(token, userDetails.getUsername());
    }

    @Test
    void tokenIsNotExpired() {
        assertFalse(jwtService.isTokenExpired(validToken));
    }

    @Test
    void tokenIsNotValid() {
        UserDetails differentUserDetails = new UserEntity("different@gmail.com", "Reiner", "Wahnsinn", "12345");

        assertFalse(jwtService.isTokenValid(validToken, differentUserDetails));
    }

    @Test
    void manipulatedTokenThrowsException() {
        assertThrows(JwtException.class, () -> jwtService.extractEmail(invalidToken));
    }
}