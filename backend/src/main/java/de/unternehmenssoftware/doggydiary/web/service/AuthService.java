package de.unternehmenssoftware.doggydiary.web.service;

import de.unternehmenssoftware.doggydiary.web.config.ApplicationConfig;
import de.unternehmenssoftware.doggydiary.web.config.WebSecurityConfig;
import de.unternehmenssoftware.doggydiary.web.entity.dao.UserEntity;
import de.unternehmenssoftware.doggydiary.web.entity.dto.User;
import de.unternehmenssoftware.doggydiary.web.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final ApplicationConfig applicationConfig;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public String register(User user) {
        String encodedPassword = applicationConfig.passwordEncoder().encode(user.getPassword());

        User encodedPasswordUser = new User(user.getEmail(), encodedPassword);
        UserEntity userEntity = new UserEntity(user.getEmail(), encodedPassword);

        try {
            userRepository.save(userEntity);
            return jwtService.generateToken(encodedPasswordUser);
        }catch (Exception e) {
            return null;
        }
    }

    public String authenticate(User user) {
        String encodedPassword = applicationConfig.passwordEncoder().encode(user.getPassword());

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        UserEntity userEntity = userRepository.findByEmail(user.getEmail());

        if(userEntity == null) {
            return null;
        }

        User encodedPasswordUser = new User(userEntity.getEmail(), encodedPassword);
        return jwtService.generateToken(null, encodedPasswordUser);
    }

    /*
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
    }*/

}
