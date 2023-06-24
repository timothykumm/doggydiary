package de.unternehmenssoftware.doggydiary.web.controller;

import de.unternehmenssoftware.doggydiary.web.controller.request.DocumentRequest;
import de.unternehmenssoftware.doggydiary.web.entity.dto.Document;
import de.unternehmenssoftware.doggydiary.web.service.DocumentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/api/v1/documents")
public class DocumentController {

    private final DocumentService documentService;

    @GetMapping
    ResponseEntity<List<Document>> getDocuments(@RequestParam("dogId") Long dogId) {
        return ResponseEntity.ok(documentService.getAllDocumentsByDog(dogId));
    }

    @PostMapping
    ResponseEntity<String> postDocument(@Valid @RequestBody DocumentRequest documentRequest) {
        Document document = documentService.createDocument(documentRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(document.getId().toString());
    }

    @PutMapping
    ResponseEntity<Void> putDocument(@RequestParam(name = "documentId") Long documentId, @Valid @RequestBody DocumentRequest documentRequest) {
        documentService.putDocument(documentId, documentRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
