package de.unternehmenssoftware.doggydiary.web.controller;

import de.unternehmenssoftware.doggydiary.web.entity.dto.User;
import de.unternehmenssoftware.doggydiary.web.service.AuthService;
import de.unternehmenssoftware.doggydiary.web.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping(path = "/api/v1")
public class UserController {

    UserService userService;
    AuthService authService;

    public UserController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    /*@GetMapping(path = "/users")
    public ResponseEntity<String> validateUser(@RequestParam String email, @RequestParam String password) {
        User user = userService.validateUser(email, password);

        if(user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found or wrong credentials");
        }
        return ResponseEntity.ok(user.getEmail() + " " + user.getPassword());
    }*/

    @PostMapping(path = "/users")
    public ResponseEntity<Void> createUser(@RequestBody User userRequest) {
        User user = userService.createUser(userRequest);

        if(user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}