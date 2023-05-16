package de.unternehmenssoftware.doggydiary.web.controller;

import de.unternehmenssoftware.doggydiary.web.controller.request.DogRequest;
import de.unternehmenssoftware.doggydiary.web.entity.dto.Dog;
import de.unternehmenssoftware.doggydiary.web.service.DogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/dogs")
public class DogController {
    private final DogService dogService;

    @GetMapping()
    public ResponseEntity<List<Dog>> getDogs() {
        return ResponseEntity.ok(dogService.getAllDogsByUser());
    }

    @PostMapping()
    public ResponseEntity<Void> createDog(@RequestBody DogRequest dogRequest) {
        Optional<Dog> dog = dogService.createDog(dogRequest);

        if(dog.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
