package de.unternehmenssoftware.doggydiary.web.controller;

import de.unternehmenssoftware.doggydiary.web.entity.dto.User;
import de.unternehmenssoftware.doggydiary.web.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/api/v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<User> userDetails() {
        User user = userService.getUserDetails();
        return ResponseEntity.ok(user);
    }

    @PutMapping
    public ResponseEntity<Void> putUser(@RequestParam(name = "apikey") String apikey) {
        userService.putUserApikey(apikey);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUser() {
        userService.deleteUser();
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}