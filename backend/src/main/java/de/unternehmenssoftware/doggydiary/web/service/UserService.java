package de.unternehmenssoftware.doggydiary.web.service;

import de.unternehmenssoftware.doggydiary.web.entity.dto.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final AuthService authService;

    public User getUserDetails() {
        return authService.getAuthenticatedUser();
    }
}
