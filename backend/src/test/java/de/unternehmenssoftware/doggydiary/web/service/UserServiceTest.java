package de.unternehmenssoftware.doggydiary.web.service;

import de.unternehmenssoftware.doggydiary.web.entity.dto.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UserServiceTest {

    @Mock
    static AuthService authService = Mockito.mock(AuthService.class);

    @InjectMocks
    static UserService userService;

    @BeforeEach
    void setup() {
        userService = new UserService(authService);
    }

    @Test
    void getUserDetails() {
        User user = new User("gertet@gmai.com", "Hans", "Heinrich", "");
        when(authService.getAuthenticatedUser()).thenReturn(user);
        assertEquals("gertet@gmai.com", userService.getUserDetails().getEmail());
    }
}