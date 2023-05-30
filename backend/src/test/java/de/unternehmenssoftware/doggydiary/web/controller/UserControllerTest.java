package de.unternehmenssoftware.doggydiary.web.controller;

import de.unternehmenssoftware.doggydiary.web.TestDataInitializer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    private final String BASE_BATH = "/api/v1/users";

    @Test
    void getUserDetails() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(BASE_BATH)
                        .accept(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + TestDataInitializer.authToken))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(TestDataInitializer.user.getEmail()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.forename").value(TestDataInitializer.user.getForename()));
    }
}