package de.unternehmenssoftware.doggydiary.web.service;

import de.unternehmenssoftware.doggydiary.web.config.ApplicationConfig;
import de.unternehmenssoftware.doggydiary.web.entity.dao.UserEntity;
import de.unternehmenssoftware.doggydiary.web.entity.dto.User;
import de.unternehmenssoftware.doggydiary.web.repository.UserRepository;
import lombok.RequiredArgsConstructor;
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

    public String register(User user) {
        String encodedPassword = applicationConfig.passwordEncoder().encode(user.getPassword());

        User encodedPasswordUser = new User(user.getEmail(), user.getForename(), user.getSurname(), encodedPassword);
        UserEntity encodedPasswordUserEntity = encodedPasswordUser.transformToUserEntity();

        try {
            userRepository.save(encodedPasswordUserEntity);
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

        User encodedPasswordUser = new User(userEntity.getEmail(), userEntity.getForename(), userEntity.getSurname(), encodedPassword);
        return jwtService.generateToken(encodedPasswordUser);
    }

    public User getAuthenticatedUser() {
        return ((UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).transformToUser();
    }

    public UserEntity getAuthenticatedUserEntity() {
        return (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
