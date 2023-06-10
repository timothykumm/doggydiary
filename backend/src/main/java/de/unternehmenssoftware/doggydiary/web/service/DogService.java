package de.unternehmenssoftware.doggydiary.web.service;

import de.unternehmenssoftware.doggydiary.web.controller.request.DogRequest;
import de.unternehmenssoftware.doggydiary.web.entity.DogEntity;
import de.unternehmenssoftware.doggydiary.web.entity.UserEntity;
import de.unternehmenssoftware.doggydiary.web.entity.dto.Dog;
import de.unternehmenssoftware.doggydiary.web.exception.DogCreateException;
import de.unternehmenssoftware.doggydiary.web.exception.DogNotFoundException;
import de.unternehmenssoftware.doggydiary.web.exception.DogProfilePicUploadException;
import de.unternehmenssoftware.doggydiary.web.repository.DogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DogService {

    private final AuthService authService;
    private final ProfilePictureService profilePictureService;
    private final DogRepository dogRepository;

    public List<Dog> getAllDogsByUser() {
        UserEntity userEntity = authService.getAuthenticatedUserEntity();
        List<DogEntity> dogEntities = dogRepository.getAllByUser(userEntity);
            return dogEntities.stream().map(DogEntity::transformToDog)
                .collect(Collectors.toList());
    }

    public Optional<Dog> createDog(DogRequest dogRequest) {
        UserEntity userEntity = authService.getAuthenticatedUserEntity();
        DogEntity dogEntity = new DogEntity(dogRequest.name(), dogRequest.breed(), dogRequest.birthdate(), "", userEntity);

        try {
            dogRepository.save(dogEntity);
        }catch (IllegalArgumentException e) {
            throw new DogCreateException();
        }
        return Optional.of(dogEntity.transformToDog());
    }

    public Optional<Dog> createDogProfilePicture(Long dogId, MultipartFile file) {
        UserEntity authenticatedUser = authService.getAuthenticatedUserEntity();
        DogEntity dogEntity = dogRepository.getDogEntityByIdAndUser(dogId, authenticatedUser).orElseThrow(DogNotFoundException::new);
        String destinationFile;

        try {
            destinationFile = profilePictureService.uploadPictureToMinio(file);
        }catch (Exception e) {
            throw new DogProfilePicUploadException(e);
        }

        try {
            dogEntity.setImg(destinationFile);
            dogRepository.save(dogEntity);
        }catch (IllegalArgumentException e) {
            throw new DogCreateException();
        }
        return Optional.of(dogEntity.transformToDog());
    }

}
