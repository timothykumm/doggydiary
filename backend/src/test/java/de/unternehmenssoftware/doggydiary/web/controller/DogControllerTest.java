package de.unternehmenssoftware.doggydiary.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.unternehmenssoftware.doggydiary.web.TestDataInitializer;
import de.unternehmenssoftware.doggydiary.web.controller.request.DogRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class DogControllerTest {

    @Autowired
    MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final String BASE_BATH = "/api/v1/dogs";

    @Test
    void getDogs() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(BASE_BATH)
                        .accept(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + TestDataInitializer.authToken))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(TestDataInitializer.dog.getId().toString()));
    }

    @Test
    void createDog() throws Exception {
        DogRequest dogRequest = new DogRequest("Bello", "Yorkier Terrier", 8);
        String request = objectMapper.writeValueAsString(dogRequest);

        mockMvc.perform(MockMvcRequestBuilders.post(BASE_BATH)
                        .header("Authorization", "Bearer " + TestDataInitializer.authToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.CREATED.value()));
    }
}