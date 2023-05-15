package de.unternehmenssoftware.doggydiary.web.service;

import de.unternehmenssoftware.doggydiary.web.config.WebSecurityConfig;
import de.unternehmenssoftware.doggydiary.web.entity.dao.UserEntity;
import de.unternehmenssoftware.doggydiary.web.entity.dto.User;
import de.unternehmenssoftware.doggydiary.web.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    UserRepository userRepository;
    WebSecurityConfig securityConfig;

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

}
