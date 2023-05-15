package de.unternehmenssoftware.doggydiary.web.controller;

import de.unternehmenssoftware.doggydiary.web.entity.dto.Dog;
import de.unternehmenssoftware.doggydiary.web.service.DogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1")
public class DogController {
    private final DogService dogService;

    @GetMapping(path = "/dogs")
    public ResponseEntity<String> getDogs() {
        return ResponseEntity.ok(dogService.getAllDogsByUser().get(0).getName());
    }

    @PostMapping(path = "/dogs")
    public ResponseEntity<Void> createDog(@RequestBody Dog dogRequest) {
        Dog dog = dogService.createDog(dogRequest);

        if(dog == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
