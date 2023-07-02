package de.unternehmenssoftware.doggydiary.web.service;

import de.unternehmenssoftware.doggydiary.web.config.MinioServerConfig;
import de.unternehmenssoftware.doggydiary.web.exception.DogProfilePicCreateException;
import io.minio.*;
import io.minio.errors.*;
import io.minio.messages.DeleteError;
import io.minio.messages.DeleteObject;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfilePictureService {

    private final MinioServerConfig fileStorageConfig;
    private MinioClient minioClient;
    private String endpoint, bucketName;

    @PostConstruct
    public void onConstruct() throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        bucketName = fileStorageConfig.getBucketName();
        endpoint = fileStorageConfig.getBucketUrl();

        minioClient = MinioClient.builder()
                .endpoint(endpoint)
                .build();

        //create bucket if not exists
        createBucketIfNotExist();
    }

    public String uploadPictureToMinio(MultipartFile file) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {

        final String randomNumber = UUID.randomUUID().toString();
        final String fileExtension = getFileExtension(file.getOriginalFilename());
        final String fileName = randomNumber + "." + fileExtension;
        final String destinationUrl = endpoint + "/" + bucketName + "/" + fileName;

        if (file.isEmpty()) throw new DogProfilePicCreateException("File is empty");
        if (!fileExtension.endsWith("jpg") && !fileExtension.endsWith("png"))
            throw new DogProfilePicCreateException("File is not an image");

        //upload image
        createImage(fileName, file);

        return destinationUrl;
    }

    private void createBucketIfNotExist() throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        //Does not work because of missing access key
        boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        if (!found) {
            // Make a new bucket
            minioClient.makeBucket(MakeBucketArgs.builder()
                    .bucket(bucketName)
                    .build());
        }
    }

    private void createImage(String fileName, MultipartFile file) {
        final InputStream fileInputStream;

        try {
            fileInputStream = file.getInputStream();

            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(fileName)
                    .stream(fileInputStream, file.getSize(), -1)
                    .contentType("image/jpeg")
                    .build());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteImageFromMinio(List<String> imageNames) {

        List<DeleteObject> deleteObjects = new LinkedList<>();

        for (String img : imageNames) {
            String[] splitImageName = img.split("/");
            deleteObjects.add(new DeleteObject(splitImageName[splitImageName.length - 1]));
        }

        try {
            Iterable<Result<DeleteError>> results =
                    minioClient.removeObjects(
                            RemoveObjectsArgs.builder().bucket(bucketName).objects(deleteObjects).build());
            for (Result<DeleteError> result : results) {
                DeleteError error = result.get();
                System.out.println(
                        "Error in deleting object " + error.objectName() + "; " + error.message());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
