package de.unternehmenssoftware.doggydiary.web.repository;

import de.unternehmenssoftware.doggydiary.web.entity.dao.DogEntity;
import de.unternehmenssoftware.doggydiary.web.entity.dao.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class DogRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DogRepository dogRepository;

    @Test
    void getAllByUser() {
        //arrange
        UserEntity userEntity = new UserEntity("dasistder@gmail.com", "Reiner", "Wahnsinn", "geheim");
        DogEntity expected = new DogEntity("Mopsi", "Schäferhund", 3, userEntity);
        userRepository.save(userEntity);
        dogRepository.save(expected);

        //act
        List<DogEntity> dogEntities = dogRepository.getAllByUser(userEntity);

        //assert
        DogEntity actual = dogEntities.get(0);
        assertTrue(expected.equals(actual));
    }
}