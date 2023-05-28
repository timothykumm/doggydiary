package de.unternehmenssoftware.doggydiary.web.controller;

import de.unternehmenssoftware.doggydiary.web.controller.request.DocumentRequest;
import de.unternehmenssoftware.doggydiary.web.entity.dto.Document;
import de.unternehmenssoftware.doggydiary.web.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/documents")
public class DocumentController {

    private final DocumentService documentService;

    @GetMapping
    ResponseEntity<List<Document>> getDocuments(@RequestParam("id") String dogId) {
        Optional<List<Document>> documents = documentService.getAllDocumentsByDog(dogId);

        if(documents.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(documents.get());
    }

    @PostMapping
    ResponseEntity<Void> postDocument(@RequestBody DocumentRequest documentRequest) {
        Optional<Document> document = documentService.createDocument(documentRequest);

        if(document.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
