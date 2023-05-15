package de.unternehmenssoftware.doggydiary.web.service;

import de.unternehmenssoftware.doggydiary.web.entity.dao.DogEntity;
import de.unternehmenssoftware.doggydiary.web.entity.dao.UserEntity;
import de.unternehmenssoftware.doggydiary.web.entity.dto.Dog;
import de.unternehmenssoftware.doggydiary.web.entity.dto.User;
import de.unternehmenssoftware.doggydiary.web.repository.DogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DogService {

    private DogRepository dogRepository;

    public List<Dog> getAllDogsByUser(User user) {
        UserEntity userEntity = new UserEntity(user.getEmail(), user.getPassword());

        return dogRepository.getAllByUser(userEntity).stream().map(dogEntity ->
                new Dog(dogEntity.getName(), dogEntity.getBreed(), dogEntity.getAge(), dogEntity.getUser()))
                .collect(Collectors.toList());
    }

    public Dog createDog(User user, Dog dog) {
        UserEntity userEntity = new UserEntity(user.getEmail(), user.getPassword());
        DogEntity dogEntity = new DogEntity(dog.getName(), dog.getBreed(), dog.getAge(), userEntity);

        try {
            dogRepository.save(dogEntity);
        }catch (Exception e) {
            return null;
        }
        return dog;
    }

}
