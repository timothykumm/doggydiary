package de.unternehmenssoftware.doggydiary.web.controller;

import de.unternehmenssoftware.doggydiary.web.controller.request.AuthRequest;
import de.unternehmenssoftware.doggydiary.web.entity.dto.User;
import de.unternehmenssoftware.doggydiary.web.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping(path = "/register")
    public ResponseEntity<String> register(@RequestBody AuthRequest authRequest) {
        String webToken = authService.register(authRequest);

        if(webToken == null || !webToken.startsWith("ey")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(webToken);
    }

    @PostMapping(path = "/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody AuthRequest authRequest) {
        String webToken = authService.authenticate(authRequest);

        if(webToken == null || !webToken.startsWith("ey")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found or wrong credentials");
        }
        return ResponseEntity.ok(webToken);
    }

}
