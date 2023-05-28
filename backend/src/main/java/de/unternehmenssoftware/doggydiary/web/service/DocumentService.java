package de.unternehmenssoftware.doggydiary.web.service;

import de.unternehmenssoftware.doggydiary.web.controller.request.DocumentRequest;
import de.unternehmenssoftware.doggydiary.web.entity.DocumentEntity;
import de.unternehmenssoftware.doggydiary.web.entity.DogEntity;
import de.unternehmenssoftware.doggydiary.web.entity.dto.Document;
import de.unternehmenssoftware.doggydiary.web.exception.DocumentCreateException;
import de.unternehmenssoftware.doggydiary.web.exception.DogNotFoundException;
import de.unternehmenssoftware.doggydiary.web.repository.DocumentRepository;
import de.unternehmenssoftware.doggydiary.web.repository.DogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final AuthService authService;
    private final DocumentRepository documentRepository;
    private final DogRepository dogRepository;

    public List<Document> getAllDocumentsByDog(String dogId) {
        String email = authService.getAuthenticatedUser().getEmail();

        return documentRepository.getDocumentsByUserAndDogId(email, dogId).stream().map(DocumentEntity::transformToDocument).toList();
    }

    public Document createDocument(DocumentRequest documentRequest) {
        DogEntity dogEntity = dogRepository.getDogEntityById(documentRequest.dogId()).orElseThrow(DogNotFoundException::new);

        DocumentEntity documentEntity = new DocumentEntity(documentRequest.title(), documentRequest.content(), dogEntity);

        try {
            documentRepository.save(documentEntity);
        }catch (IllegalArgumentException e) {
            throw new DocumentCreateException();
        }
        return documentEntity.transformToDocument();
    }

}