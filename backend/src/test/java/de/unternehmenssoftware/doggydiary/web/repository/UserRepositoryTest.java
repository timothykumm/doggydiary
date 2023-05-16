package de.unternehmenssoftware.doggydiary.web.repository;

import de.unternehmenssoftware.doggydiary.web.entity.dao.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void findByEmail() {
        //arrange
        String mail = "dasistder@gmail.com";
        UserEntity expected = new UserEntity(mail, "Reiner", "Wahnsinn", "geheim");
        userRepository.save(expected);

        //act
        UserEntity actual = userRepository.findByEmail(mail);

        //assert
        assertTrue(expected.equals(actual));
    }

    @Test
    void existsUserEntityByEmail() {
        //arrange
        String mail = "dasistder@gmail.com";
        UserEntity expected = new UserEntity(mail, "Reiner", "Wahnsinn", "geheim");
        userRepository.save(expected);

        //act
        boolean actual = userRepository.existsUserEntityByEmail(mail);

        //assert
        assertTrue(actual);
    }

    @Test
    void existsUserEntityByFakeEmail() {
        //arrange
        String mail = "dasgibtesgarnicht@gmail.com";

        //act
        boolean actual = userRepository.existsUserEntityByEmail(mail);

        //assert
        assertFalse(actual);
    }
}