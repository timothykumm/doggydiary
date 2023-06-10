package de.unternehmenssoftware.doggydiary.web.service;

import de.unternehmenssoftware.doggydiary.web.config.MinioServerConfig;
import de.unternehmenssoftware.doggydiary.web.exception.DogProfilePicCreateException;
import io.minio.*;
import io.minio.errors.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfilePictureService {

    private final MinioServerConfig fileStorageConfig;

    public String uploadPictureToMinio(MultipartFile file) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        final String endpoint = fileStorageConfig.getBucketUrl();
        final String bucketName = fileStorageConfig.getBucketName();

        final String randomNumber = UUID.randomUUID().toString();
        final String fileExtension = getFileExtension(file.getOriginalFilename());
        final String fileName = randomNumber + "." + fileExtension;
        final String destinationUrl = endpoint + "/" + bucketName + "/" + fileName;

        if (file.isEmpty()) throw new DogProfilePicCreateException("File is empty");
        if (!fileExtension.endsWith("jpg") && !fileExtension.endsWith("png"))
            throw new DogProfilePicCreateException("File is not an image");


        MinioClient minioClient = MinioClient.builder()
                .endpoint(endpoint)
                .build();

        //create bucket
        createBucketIfNotExist(minioClient, bucketName);

        //upload image
        createImage(minioClient, bucketName, fileName, file);

        return destinationUrl;
    }

    private void createBucketIfNotExist(MinioClient minioClient, String bucketName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        if (!found) {
            // Make a new bucket
            minioClient.makeBucket(MakeBucketArgs.builder()
                    .bucket(bucketName)
                    .build());
        } else {
            System.out.println("Bucket " + bucketName + " already exists.");
        }
    }

    private void createImage(MinioClient minioClient, String bucketName, String fileName, MultipartFile file) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        final InputStream fileInputStream = file.getInputStream();

        minioClient.putObject(PutObjectArgs.builder()
                .bucket(bucketName)
                .object(fileName)
                .stream(fileInputStream, file.getSize(), -1)
                .contentType("image/jpeg")
                .build());
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
