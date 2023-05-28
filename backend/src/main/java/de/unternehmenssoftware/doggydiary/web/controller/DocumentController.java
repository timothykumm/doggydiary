package de.unternehmenssoftware.doggydiary.web.controller;

import de.unternehmenssoftware.doggydiary.web.controller.request.DocumentRequest;
import de.unternehmenssoftware.doggydiary.web.entity.dto.Document;
import de.unternehmenssoftware.doggydiary.web.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/documents")
public class DocumentController {

    private final DocumentService documentService;

    @GetMapping
    ResponseEntity<List<Document>> getDocuments(@RequestParam("id") String dogId) {
        return ResponseEntity.ok(documentService.getAllDocumentsByDog(dogId));
    }

    @PostMapping
    ResponseEntity<Void> postDocument(@RequestBody DocumentRequest documentRequest) {
        Document document = documentService.createDocument(documentRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
