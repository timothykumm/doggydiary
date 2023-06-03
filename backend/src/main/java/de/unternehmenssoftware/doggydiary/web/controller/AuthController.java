package de.unternehmenssoftware.doggydiary.web.controller;

import de.unternehmenssoftware.doggydiary.web.controller.request.AuthRequest;
import de.unternehmenssoftware.doggydiary.web.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(path = "/register")
    public ResponseEntity<String> register(@Valid @RequestBody AuthRequest authRequest) {
        return authService.register(authRequest);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(path = "/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody AuthRequest authRequest) {
        return authService.authenticate(authRequest);
    }

}
