package de.unternehmenssoftware.doggydiary.web.service;

import de.unternehmenssoftware.doggydiary.web.config.SecurityConfig;
import de.unternehmenssoftware.doggydiary.web.entity.dao.UserEntity;
import de.unternehmenssoftware.doggydiary.web.entity.dto.User;
import de.unternehmenssoftware.doggydiary.web.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    UserRepository userRepository;
    SecurityConfig securityConfig;

    public UserService(UserRepository userRepository, SecurityConfig securityConfig) {
        this.userRepository = userRepository;
        this.securityConfig = securityConfig;
    }

    public User findByEmail(String email) {
        UserEntity user = userRepository.findByEmail(email);

        if(user == null) {
            return null;
        }
        return new User(user.getEmail(), user.getPassword(), user.getDogs());
    }

    public User createUser(User user) {
        String encodedPassword = securityConfig.passwordEncoder().encode(user.getPassword());
        UserEntity userEntity = new UserEntity(user.getEmail(), encodedPassword);

        try {
            userRepository.save(userEntity);
        }catch (Exception e) {
            return null;
        }
        return user;
    }

    public User validateUser(String email, String password) {
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

}
