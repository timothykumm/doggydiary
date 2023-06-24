package de.unternehmenssoftware.doggydiary.web.service;

import de.unternehmenssoftware.doggydiary.web.controller.request.DocumentRequest;
import de.unternehmenssoftware.doggydiary.web.entity.DocumentEntity;
import de.unternehmenssoftware.doggydiary.web.entity.DogEntity;
import de.unternehmenssoftware.doggydiary.web.entity.UserEntity;
import de.unternehmenssoftware.doggydiary.web.entity.dto.Document;
import de.unternehmenssoftware.doggydiary.web.entity.dto.User;
import de.unternehmenssoftware.doggydiary.web.exception.DocumentCreateException;
import de.unternehmenssoftware.doggydiary.web.repository.DocumentRepository;
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
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class DocumentServiceTest {

    @Mock
    DogRepository dogRepository;

    @Mock
    DocumentRepository documentRepository;

    @Mock
    AuthService authService;

    @InjectMocks
    DocumentService documentService;

    @BeforeEach
    void setup() {
         documentService = new DocumentService(authService, documentRepository, dogRepository);
    }

    @Test
    void getAllDocumentsByDog() {
        final User user = new User("dasistder@gmail.com", "Reiner", "Wahnsinn", "");
        List<DocumentEntity> documentEntities = List.of(
                new DocumentEntity(Mockito.mock(Date.class), "Titel1", "Content1", Mockito.mock(DogEntity.class)),
                new DocumentEntity(Mockito.mock(Date.class), "Titel2", "Content2", Mockito.mock(DogEntity.class)));

        when(authService.getAuthenticatedUser()).thenReturn(user);
        when(documentRepository.getDocumentsByUserAndDogId(Mockito.anyString(), Mockito.anyLong())).thenReturn(documentEntities);

        List<Document> documents = documentService.getAllDocumentsByDog(12345L);

        assertEquals(documents, documentEntities.stream().map(DocumentEntity::transformToDocument).toList());
    }

    @Test
    void createDocumentSucceeds() {
        DocumentRequest documentRequest = new DocumentRequest("Title1", "Content1", 12345L);
        DogEntity dogEntity = new DogEntity("Fiffi", "Mops", Mockito.mock(Date.class) , "", Mockito.mock(UserEntity.class));
        DocumentEntity documentEntity = new DocumentEntity(Mockito.mock(Date.class), documentRequest.title(), documentRequest.content(), dogEntity);

        when(authService.getAuthenticatedUserEntity()).thenReturn(Mockito.mock(UserEntity.class));
        when(dogRepository.getDogEntityByIdAndUser(Mockito.anyLong(), Mockito.any(UserEntity.class))).thenReturn(Optional.of(dogEntity));
        when(documentRepository.save(Mockito.any(DocumentEntity.class))).thenReturn(documentEntity);

        Document actual = documentService.createDocument(documentRequest);
        assertEquals(actual.getId(), documentEntity.transformToDocument().getId());
        assertEquals(actual.getTitle(), documentEntity.transformToDocument().getTitle());
        assertEquals(actual.getContent(), documentEntity.transformToDocument().getContent());
    }

    @Test
    void createDocumentThrowsException() {
        DocumentRequest documentRequest = new DocumentRequest("Title1", "Content1", 12345L);
        DogEntity dogEntity = new DogEntity("Fiffi", "Mops", Mockito.mock(Date.class) , "", Mockito.mock(UserEntity.class));

        when(authService.getAuthenticatedUserEntity()).thenReturn(Mockito.mock(UserEntity.class));
        when(dogRepository.getDogEntityByIdAndUser(Mockito.anyLong(), Mockito.any(UserEntity.class))).thenReturn(Optional.of(dogEntity));
        when(documentRepository.save(Mockito.any(DocumentEntity.class))).thenThrow(IllegalArgumentException.class);

        assertThrows(DocumentCreateException.class, () -> documentService.createDocument(documentRequest));
    }
}