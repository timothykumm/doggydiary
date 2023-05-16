package de.unternehmenssoftware.doggydiary.web.service;

import de.unternehmenssoftware.doggydiary.web.entity.dao.DogEntity;
import de.unternehmenssoftware.doggydiary.web.entity.dao.UserEntity;
import de.unternehmenssoftware.doggydiary.web.entity.dto.Dog;
import de.unternehmenssoftware.doggydiary.web.repository.DogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DogService {

    private final AuthService authService;
    private final DogRepository dogRepository;

    public List<Dog> getAllDogsByUser() {
        UserEntity userEntity = authService.getAuthenticatedUserEntity();
            return dogRepository.getAllByUser(userEntity).stream().map(DogEntity::transformToDog)
                .collect(Collectors.toList());
    }

    public Optional<Dog> createDog(Dog dog) {
        UserEntity userEntity = authService.getAuthenticatedUserEntity();
        DogEntity dogEntity = dog.transformToDogEntity(userEntity);

        try {
            dogRepository.save(dogEntity);
        }catch (Exception e) {
            return Optional.empty();
        }
        return Optional.of(dog);
    }

}
