package de.unternehmenssoftware.doggydiary.web.service;

import de.unternehmenssoftware.doggydiary.web.config.ApplicationConfig;
import de.unternehmenssoftware.doggydiary.web.controller.request.AuthRequest;
import de.unternehmenssoftware.doggydiary.web.controller.response.AuthResponse;
import de.unternehmenssoftware.doggydiary.web.entity.CustomUserCredentials;
import de.unternehmenssoftware.doggydiary.web.entity.UserEntity;
import de.unternehmenssoftware.doggydiary.web.entity.dto.User;
import de.unternehmenssoftware.doggydiary.web.exception.UserCredentialsException;
import de.unternehmenssoftware.doggydiary.web.exception.UserMailAlreadyExistsException;
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

    public ResponseEntity<AuthResponse> register(AuthRequest authRequest) {

        if(userRepository.existsUserEntityByEmail(authRequest.email())) throw new UserMailAlreadyExistsException();

        String encodedPassword = applicationConfig.passwordEncoder().encode(authRequest.password());
        UserEntity encodedPasswordUserEntity = new UserEntity(authRequest.email(), authRequest.forename(), authRequest.surname(), encodedPassword, authRequest.openai());
        CustomUserCredentials userCredential = new CustomUserCredentials(encodedPasswordUserEntity);

        try {
            userRepository.save(encodedPasswordUserEntity);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new AuthResponse(jwtService.generateToken(userCredential), encodedPasswordUserEntity.getOpenai()));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    public ResponseEntity<AuthResponse> authenticate(AuthRequest authRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.email(), authRequest.password()));
        CustomUserCredentials userCredential = userRepository.findByEmail(authRequest.email())
                .map(CustomUserCredentials::new).orElseThrow(UserCredentialsException::new);

        return ResponseEntity.status(HttpStatus.OK).body(new AuthResponse(jwtService.generateToken(userCredential), userCredential.getOpenai()));
    }

    public User getAuthenticatedUser() {
        return ((UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).transformToUser();
    }

    public UserEntity getAuthenticatedUserEntity() {
        return (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
