package de.unternehmenssoftware.doggydiary.web.service;

import de.unternehmenssoftware.doggydiary.web.controller.request.DocumentRequest;
import de.unternehmenssoftware.doggydiary.web.entity.DocumentEntity;
import de.unternehmenssoftware.doggydiary.web.entity.DogEntity;
import de.unternehmenssoftware.doggydiary.web.entity.dto.Document;
import de.unternehmenssoftware.doggydiary.web.repository.DocumentRepository;
import de.unternehmenssoftware.doggydiary.web.repository.DogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final AuthService authService;
    private final DocumentRepository documentRepository;
    private final DogRepository dogRepository;

    public Optional<List<Document>> getAllDocumentsByDog(String dogId) {
        String email = authService.getAuthenticatedUser().getEmail();
        List<Document> documents = documentRepository.getDocumentsByUserAndDogId(email, dogId).stream().map(DocumentEntity::transformToDocument).toList();

        return Optional.of(documents);
    }

    public Optional<Document> createDocument(DocumentRequest documentRequest) {
        DogEntity dogEntity = dogRepository.getDogEntityById(documentRequest.dogId());
        DocumentEntity documentEntity = new DocumentEntity(documentRequest.title(), documentRequest.content(), dogEntity);

        try {
            documentRepository.save(documentEntity);
        }catch (IllegalArgumentException e) {
            return Optional.empty();
        }
        return Optional.of(documentEntity.transformToDocument());
    }

}
