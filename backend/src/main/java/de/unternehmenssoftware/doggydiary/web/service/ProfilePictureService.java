package de.unternehmenssoftware.doggydiary.web.service;

import de.unternehmenssoftware.doggydiary.web.config.FileStorageConfig;
import de.unternehmenssoftware.doggydiary.web.exception.DogProfilePicCreateException;
import de.unternehmenssoftware.doggydiary.web.exception.DogProfilePicUploadException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfilePictureService {

    private final FileStorageConfig fileStorageConfig;

    public String uploadPictureToServer(MultipartFile file) throws IOException {
        String randomNumber = UUID.randomUUID().toString();
        String endpoint = fileStorageConfig.getDogImagesUrl() + "/" + randomNumber + "." + getFileExtension(file.getOriginalFilename());
        String fileExtension = getFileExtension(file.getOriginalFilename());
        if(file.isEmpty()) throw new DogProfilePicCreateException("File is empty");
        if(!fileExtension.endsWith("jpg") && !fileExtension.endsWith("png")) throw new DogProfilePicCreateException("File is not an image");


        RestTemplate restTemplate = new RestTemplate();

        // Request-Header konfigurieren
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        // HTTP-Entity mit Daten und Header erstellen
        HttpEntity<byte[]> requestEntity = new HttpEntity<>(file.getBytes(), headers);

        // PUT-Anfrage senden
        ResponseEntity<String> responseEntity = restTemplate.exchange(endpoint, HttpMethod.PUT, requestEntity, String.class);

        // Antwort auswerten
        if (!responseEntity.getStatusCode().is2xxSuccessful()) throw new DogProfilePicUploadException();

        return endpoint;
    }

    private static String getFileExtension(String filename) {
        if (filename != null && !filename.isEmpty()) {
            int dotIndex = filename.lastIndexOf('.');
            if (dotIndex > 0 && dotIndex < filename.length() - 1) {
                return filename.substring(dotIndex + 1).toLowerCase();
            }
        }
        return "";
    }

}
