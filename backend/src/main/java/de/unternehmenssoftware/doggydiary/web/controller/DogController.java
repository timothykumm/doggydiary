package de.unternehmenssoftware.doggydiary.web.controller;

import de.unternehmenssoftware.doggydiary.web.entity.dto.Dog;
import de.unternehmenssoftware.doggydiary.web.entity.dto.User;
import de.unternehmenssoftware.doggydiary.web.service.AuthService;
import de.unternehmenssoftware.doggydiary.web.service.DogService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping(path = "/api/v1")
public class DogController {

    AuthService authService;
    DogService dogService;

    public DogController(DogService dogService, AuthService authService) {
        this.dogService = dogService;
        this.authService = authService;
    }

    @GetMapping(path = "/dogs")
    public ResponseEntity<String> getDogs(HttpServletRequest request) {
        User user = authService.validateUser(request);

        if(user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(dogService.getAllDogsByUser(user).get(0).getName() + " " + dogService.getAllDogsByUser(user).get(1).getName());
    }

    @PostMapping(path = "/dogs")
    public ResponseEntity<Void> createDog(HttpServletRequest request, @RequestBody Dog dogRequest) {
        User user = authService.validateUser(request);

        if(user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Dog dog = dogService.createDog(user, dogRequest);

        if(dog == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
