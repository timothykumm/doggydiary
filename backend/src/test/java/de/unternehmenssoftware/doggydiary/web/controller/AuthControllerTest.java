package de.unternehmenssoftware.doggydiary.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.unternehmenssoftware.doggydiary.web.TestDataInitializer;
import de.unternehmenssoftware.doggydiary.web.controller.request.AuthRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.MvcResult;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final String BASE_BATH = "/api/v1/auth";

    @Test
    void registrationSucceeds() throws Exception {

        AuthRequest authRequest = new AuthRequest("test@gmail.com", "Heinrich", "Müller", "geheim");
        String request = objectMapper.writeValueAsString(authRequest);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(BASE_BATH + "/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request)
                        .accept(MediaType.TEXT_PLAIN))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.CREATED.value()))
                .andReturn();

        String token = result.getResponse().getContentAsString();

        assertTrue(token.startsWith("ey"));
    }

    @Test
    void registrationFailsBecauseEmailAlreadyExists() throws Exception {

        AuthRequest authRequest = new AuthRequest("repo@testing.com", "Heinrich", "Müller", "geheim");
        String request = objectMapper.writeValueAsString(authRequest);

        mockMvc.perform(MockMvcRequestBuilders.post(BASE_BATH + "/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request)
                        .accept(MediaType.TEXT_PLAIN))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.CONFLICT.value()))
                .andReturn();
    }

    @Test
    void authenticationSucceeds() throws Exception {
        String request = objectMapper.writeValueAsString(TestDataInitializer.authRequest);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(BASE_BATH + "/authenticate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request)
                        .accept(MediaType.TEXT_PLAIN))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()))
                .andReturn();

        String token = result.getResponse().getContentAsString();

        assertTrue(token.startsWith("ey"));
    }
}