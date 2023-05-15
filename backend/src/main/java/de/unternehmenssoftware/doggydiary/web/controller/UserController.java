package de.unternehmenssoftware.doggydiary.web.controller;

import de.unternehmenssoftware.doggydiary.web.entity.dto.User;
import de.unternehmenssoftware.doggydiary.web.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1")
public class UserController {

    private final UserService userService;

    @GetMapping(path = "/users")
    public ResponseEntity<String> validateUser(@RequestParam String email) {
        User user = userService.findByEmail(email);

        if(user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found or wrong credentials");
        }
        return ResponseEntity.ok(user.getEmail() + " " + user.getForename() + " " + user.getSurname());
    }
}