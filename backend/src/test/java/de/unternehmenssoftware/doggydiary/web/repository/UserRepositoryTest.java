package de.unternehmenssoftware.doggydiary.web.repository;

import de.unternehmenssoftware.doggydiary.web.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    final String savedUserMail = "saveduser@gmail.com", unsavedUserMail = "unsaveduser@gmail.com";
    final UserEntity expected = new UserEntity(savedUserMail, "Reiner", "Wahnsinn", "geheim");

    @Test
    void findByEmail() {
        userRepository.save(expected);

        //act
        UserEntity actual = userRepository.findByEmail(savedUserMail);

        //assert
        assertEquals(expected.transformToUser(), actual.transformToUser());
    }

    @Test
    void userEntityDoesExist() {
        userRepository.save(expected);

        //act
        boolean actual = userRepository.existsUserEntityByEmail(savedUserMail);

        //assert
        assertTrue(actual);
    }

    @Test
    void userEntityDoesNotExist() {
        //act
        boolean actual = userRepository.existsUserEntityByEmail(unsavedUserMail);

        //assert
        assertFalse(actual);
    }
}