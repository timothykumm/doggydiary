package de.unternehmenssoftware.doggydiary.web.service;

import de.unternehmenssoftware.doggydiary.web.config.WebSecurityConfig;
import de.unternehmenssoftware.doggydiary.web.entity.dao.UserEntity;
import de.unternehmenssoftware.doggydiary.web.entity.dto.User;
import de.unternehmenssoftware.doggydiary.web.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class AuthService {

    UserRepository userRepository;
    WebSecurityConfig securityConfig;

    public AuthService(UserRepository userRepository, WebSecurityConfig securityConfig) {
        this.userRepository = userRepository;
        this.securityConfig = securityConfig;
    }

    public User validateUser(HttpServletRequest request) {
        String[] credentials = getUsernameAndPasswordFromBasicAuthHeader(request);
        String email = credentials[0], password = credentials[1];

        String encodedPassword = securityConfig.passwordEncoder().encode(password);
        UserEntity userEntity = userRepository.findByEmail(email);

        if(userEntity == null) {
            return null;
        }

        if(securityConfig.passwordEncoder().matches(password, userEntity.getPassword())) {
            return new User(email, encodedPassword);
        }

        return null;
    }

    public String[] getUsernameAndPasswordFromBasicAuthHeader(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.toLowerCase().startsWith("basic")) {
            // Extract username and password from header
            String base64Credentials = authHeader.substring("Basic".length()).trim();
            byte[] decoded = Base64.getDecoder().decode(base64Credentials);
            String credentials = new String(decoded);
            return credentials.split(":", 2);
        }
        return null;
    }

}
