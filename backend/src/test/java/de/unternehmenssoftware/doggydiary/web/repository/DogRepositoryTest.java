package de.unternehmenssoftware.doggydiary.web.repository;

import de.unternehmenssoftware.doggydiary.web.entity.DogEntity;
import de.unternehmenssoftware.doggydiary.web.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DogRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DogRepository dogRepository;

    @Test
    void getAllByUser() {
        //arrange
        final UserEntity userEntity = new UserEntity("dasistder@gmail.com", "Reiner", "Wahnsinn", "geheim", "");
        final DogEntity expected = new DogEntity("Mopsi", "Schäferhund", new Date(), "", userEntity);
        userRepository.save(userEntity);
        dogRepository.save(expected);

        //act
        List<DogEntity> dogEntities = dogRepository.getAllByUser(userEntity);

        //assert
        DogEntity actual = dogEntities.get(0);
        assertEquals(expected.transformToDog(), actual.transformToDog());
    }
}