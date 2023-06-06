package de.unternehmenssoftware.doggydiary.web.controller;

import de.unternehmenssoftware.doggydiary.web.controller.request.AuthRequest;
import de.unternehmenssoftware.doggydiary.web.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping(path = "/register")
    public ResponseEntity<String> register(@Valid @RequestBody AuthRequest authRequest) {
        return authService.register(authRequest);
    }

    @PostMapping(path = "/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody AuthRequest authRequest) {
        return authService.authenticate(authRequest);
    }

}
