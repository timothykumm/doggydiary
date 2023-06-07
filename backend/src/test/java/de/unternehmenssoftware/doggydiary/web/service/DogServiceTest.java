package de.unternehmenssoftware.doggydiary.web.service;

import de.unternehmenssoftware.doggydiary.web.controller.request.DogRequest;
import de.unternehmenssoftware.doggydiary.web.entity.DogEntity;
import de.unternehmenssoftware.doggydiary.web.entity.UserEntity;
import de.unternehmenssoftware.doggydiary.web.entity.dto.Dog;
import de.unternehmenssoftware.doggydiary.web.exception.DogCreateException;
import de.unternehmenssoftware.doggydiary.web.repository.DogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class DogServiceTest {
    @Mock
    private AuthService authService;

    @Mock
    private ProfilePictureService profilePictureService;

    @Mock
    private DogRepository dogRepository;

    @InjectMocks
    private DogService dogService;

    final UserEntity userEntity = new UserEntity("dasistder@gmail.com", "Reiner", "Wahnsinn", "geheim");
    final List<DogEntity> dogEntities = List.of(
            new DogEntity("Sabine", "Mops", new Date(), "", null),
            new DogEntity("Tomas", "Schaeferhund", new Date(), "", null)
    );

    @BeforeEach
    public void setup() {
        dogService = new DogService(authService,  profilePictureService, dogRepository);
        when(authService.getAuthenticatedUserEntity()).thenReturn(userEntity);
    }

    @Test
    public void testGetAllDogsByUser() {

        //act
        when(dogRepository.getAllByUser(userEntity)).thenReturn(dogEntities);

        List<Dog> actualResult = dogService.getAllDogsByUser();
        List<Dog> expectedResult = List.of(dogEntities.get(0).transformToDog(), dogEntities.get(1).transformToDog());

        //assert
        assertEquals(expectedResult.size(), actualResult.size());
        assertEquals(expectedResult.get(0).getName(), actualResult.get(0).getName());

        verify(authService, times(1)).getAuthenticatedUserEntity();
        verify(dogRepository, times(1)).getAllByUser(userEntity);
    }


    @Test
    void createDogSucceeds() {

        //arrange
        DogEntity expected = dogEntities.get(0);

        DogRequest dogRequest = new DogRequest(
                expected.getName(),
                expected.getBreed(),
                expected.getBirthdate());

        //act
        when(dogRepository.save(Mockito.any(DogEntity.class))).thenReturn(expected);
        Optional<Dog> actual = dogService.createDog(dogRequest);

        //assert
        assertEquals(expected.getName(), actual.get().getName());
    }

    @Test
    void createDogThrowsException() {

        //arrange
        DogRequest dogRequest = new DogRequest(
                null,
                null,
                new Date());

        //act
        when(dogRepository.save(Mockito.any(DogEntity.class))).thenThrow(DogCreateException.class);

        //assert
        assertThrows(DogCreateException.class, () -> dogService.createDog(dogRequest));
    }
}