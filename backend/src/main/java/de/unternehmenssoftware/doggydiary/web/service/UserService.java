package de.unternehmenssoftware.doggydiary.web.service;

import de.unternehmenssoftware.doggydiary.web.entity.dao.UserEntity;
import de.unternehmenssoftware.doggydiary.web.entity.dto.User;
import de.unternehmenssoftware.doggydiary.web.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByEmail(String email) {
        UserEntity user = userRepository.findByEmail(email);

        if(user == null) {
            return null;
        }
        return new User(user.getEmail(), user.getPassword(), user.getDogs());
    }

    public User createUser(User user) {
        UserEntity userEntity = new UserEntity(user.getEmail(), user.getPassword(), user.getDogs());
        try {
            userRepository.save(userEntity);
        }catch (Exception e) {
            return null;
        }
        return user;
    }

}
