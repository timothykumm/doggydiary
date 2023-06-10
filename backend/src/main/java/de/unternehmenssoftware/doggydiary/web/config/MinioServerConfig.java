package de.unternehmenssoftware.doggydiary.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioServerConfig {

    @Value("${minio.bucket.name}")
    private String bucketName;

    @Value("${minio.bucket.url}")
    private String bucketUrl;

    public String getBucketName() {
        return bucketName;
    }

    public String getBucketUrl() {
        return bucketUrl;
    }
}
