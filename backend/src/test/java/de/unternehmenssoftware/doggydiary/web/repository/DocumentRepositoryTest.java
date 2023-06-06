package de.unternehmenssoftware.doggydiary.web.repository;

import de.unternehmenssoftware.doggydiary.web.entity.DocumentEntity;
import de.unternehmenssoftware.doggydiary.web.entity.DogEntity;
import de.unternehmenssoftware.doggydiary.web.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DocumentRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    DogRepository dogRepository;

    @Autowired
    DocumentRepository documentRepository;

    @Test
    void getDocumentsByUserAndDogId() {
        final UserEntity user = new UserEntity("saveduser@gmail.com", "Reiner", "Wahnsinn", "geheim");
        final DogEntity dog = new DogEntity("Mopsi", "Schäferhund", 3, "", user);
        final DocumentEntity document = new DocumentEntity("Arztbesuch", "Dem Hund geht es gut", dog);

        userRepository.save(user);
        DogEntity savedDog = dogRepository.save(dog);
        documentRepository.save(document);

        List<DocumentEntity> documents = documentRepository.getDocumentsByUserAndDogId(user.getEmail(), savedDog.getId().toString());

        assertEquals(documents.size(), 1);
    }
}