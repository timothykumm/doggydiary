package de.unternehmenssoftware.doggydiary.web.service;

import de.unternehmenssoftware.doggydiary.web.config.ApplicationConfig;
import de.unternehmenssoftware.doggydiary.web.controller.request.AuthRequest;
import de.unternehmenssoftware.doggydiary.web.entity.dao.UserEntity;
import de.unternehmenssoftware.doggydiary.web.entity.dto.User;
import de.unternehmenssoftware.doggydiary.web.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final ApplicationConfig applicationConfig;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public String register(AuthRequest authRequest) {
        String encodedPassword = applicationConfig.passwordEncoder().encode(authRequest.password());
        UserEntity encodedPasswordUserEntity = new UserEntity(authRequest.email(), authRequest.forename(), authRequest.surname(), encodedPassword);

        try {
            userRepository.save(encodedPasswordUserEntity);
            return jwtService.generateToken(encodedPasswordUserEntity);
        }catch (Exception e) {
            return null;
        }
    }

    public String authenticate(AuthRequest authRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.email(), authRequest.password()));
        UserEntity userEntity = userRepository.findByEmail(authRequest.email());

        if(userEntity == null) {
            return null;
        }

        return jwtService.generateToken(userEntity);
    }

    public User getAuthenticatedUser() {
        return ((UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).transformToUser();
    }

    public UserEntity getAuthenticatedUserEntity() {
        return (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
