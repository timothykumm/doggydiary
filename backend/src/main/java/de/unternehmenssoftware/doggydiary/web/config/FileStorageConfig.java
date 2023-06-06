package de.unternehmenssoftware.doggydiary.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileStorageConfig {

    @Value("${dog.images.url}")
    private String dogImagesUrl;

    public String getDogImagesUrl() {
        return dogImagesUrl;
    }

}
