package de.unternehmenssoftware.doggydiary.web;

import de.unternehmenssoftware.doggydiary.web.config.ApplicationConfig;
import de.unternehmenssoftware.doggydiary.web.controller.request.AuthRequest;
import de.unternehmenssoftware.doggydiary.web.entity.CustomUserCredentials;
import de.unternehmenssoftware.doggydiary.web.entity.DocumentEntity;
import de.unternehmenssoftware.doggydiary.web.entity.DogEntity;
import de.unternehmenssoftware.doggydiary.web.entity.UserEntity;
import de.unternehmenssoftware.doggydiary.web.repository.DocumentRepository;
import de.unternehmenssoftware.doggydiary.web.repository.DogRepository;
import de.unternehmenssoftware.doggydiary.web.repository.UserRepository;
import de.unternehmenssoftware.doggydiary.web.service.JwtService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TestDataInitializer {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DogRepository dogRepository;

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    JwtService jwtService;

    @Autowired
    ApplicationConfig applicationConfig;

    public static AuthRequest authRequest;

    public static UserEntity user;

    public static DogEntity dog;

    public static DocumentEntity document;

    public static String authToken;

    @PostConstruct
    public void initTestData() {

        //encodes user entities password
        String encodedPassword = applicationConfig.passwordEncoder().encode("geheim");

        //generates global accessible authRequest
        authRequest = new AuthRequest("repo@testing.com", "Repos", "testen", "geheim");

        //initializes global accessible entities
        user = new UserEntity("repo@testing.com", "Repos", "testen", encodedPassword);
        dog = new DogEntity("Wiener", "Wuerstchen", new Date(), "", user);
        document = new DocumentEntity("Repotest", "Dies existiert um die Post und get Requests zu testen", dog);

        //generates global accessible jwtToken
        authToken = jwtService.generateToken(new CustomUserCredentials(user));

        //saves entities in database
        userRepository.save(user);
        dogRepository.save(dog);
        documentRepository.save(document);
    }
}
