package de.unternehmenssoftware.doggydiary.web.service;

import de.unternehmenssoftware.doggydiary.web.config.ApplicationConfig;
import de.unternehmenssoftware.doggydiary.web.controller.request.AuthRequest;
import de.unternehmenssoftware.doggydiary.web.entity.UserEntity;
import de.unternehmenssoftware.doggydiary.web.entity.dto.User;
import de.unternehmenssoftware.doggydiary.web.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final ApplicationConfig applicationConfig;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public ResponseEntity<String> register(AuthRequest authRequest) {
        //Email already exists
        if(userRepository.existsUserEntityByEmail(authRequest.email())) return ResponseEntity.status(HttpStatus.CONFLICT).build();

        String encodedPassword = applicationConfig.passwordEncoder().encode(authRequest.password());
        UserEntity encodedPasswordUserEntity = new UserEntity(authRequest.email(), authRequest.forename(), authRequest.surname(), encodedPassword);

        try {
            userRepository.save(encodedPasswordUserEntity);
            return ResponseEntity.status(HttpStatus.CREATED).body(jwtService.generateToken(encodedPasswordUserEntity));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    public ResponseEntity<String> authenticate(AuthRequest authRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.email(), authRequest.password()));
        UserEntity userEntity = userRepository.findByEmail(authRequest.email());

        if(userEntity == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found or wrong credentials");
        }

        return ResponseEntity.status(HttpStatus.OK).body(jwtService.generateToken(userEntity));
    }

    public User getAuthenticatedUser() {
        return ((UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).transformToUser();
    }

    public UserEntity getAuthenticatedUserEntity() {
        return (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
