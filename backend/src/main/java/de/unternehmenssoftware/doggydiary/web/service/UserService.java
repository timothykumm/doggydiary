package de.unternehmenssoftware.doggydiary.web.service;

import de.unternehmenssoftware.doggydiary.web.entity.dao.UserEntity;
import de.unternehmenssoftware.doggydiary.web.entity.dto.User;
import de.unternehmenssoftware.doggydiary.web.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final AuthService authService;
    private final UserRepository userRepository;

    public User getUserDetails() {
        return authService.getAuthenticatedUser();
    }
}
