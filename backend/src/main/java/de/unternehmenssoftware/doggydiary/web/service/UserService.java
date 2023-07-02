package de.unternehmenssoftware.doggydiary.web.service;

import de.unternehmenssoftware.doggydiary.web.entity.UserEntity;
import de.unternehmenssoftware.doggydiary.web.entity.dto.User;
import de.unternehmenssoftware.doggydiary.web.exception.ApikeyModifyException;
import de.unternehmenssoftware.doggydiary.web.exception.UserDeleteException;
import de.unternehmenssoftware.doggydiary.web.exception.UserNotFoundException;
import de.unternehmenssoftware.doggydiary.web.repository.DogRepository;
import de.unternehmenssoftware.doggydiary.web.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final AuthService authService;
    private final ProfilePictureService profilePictureService;
    private final UserRepository userRepository;
    private final DogRepository dogRepository;

    public User getUserDetails() {
        return authService.getAuthenticatedUser();
    }

    public void putUserApikey(String apikey) {
        UserEntity userEntity = authService.getAuthenticatedUserEntity();
        
        UserEntity modifiedUserEntity = userRepository.findByEmail(userEntity.getEmail())
                .orElseThrow(UserNotFoundException::new);
        modifiedUserEntity.setOpenai(apikey);

        try {
            userRepository.save(modifiedUserEntity);
        } catch (RuntimeException e) {
            throw new ApikeyModifyException();
        }
    }

    public void deleteUser() {
        UserEntity userEntity = authService.getAuthenticatedUserEntity();
        List<String> dogImages = dogRepository.getAllImagesByUser(userEntity.getEmail());
        profilePictureService.deleteImageFromMinio(dogImages);

        try {
            userRepository.deleteById(userEntity.getEmail());
        } catch (RuntimeException e) {
            throw new UserDeleteException();
        }
    }

}
