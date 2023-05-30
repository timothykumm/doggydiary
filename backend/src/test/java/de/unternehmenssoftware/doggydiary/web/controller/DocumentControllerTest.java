package de.unternehmenssoftware.doggydiary.web.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import de.unternehmenssoftware.doggydiary.web.TestDataInitializer;
import de.unternehmenssoftware.doggydiary.web.controller.request.DocumentRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@AutoConfigureMockMvc
class DocumentControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final String BASE_BATH = "/api/v1/documents";

    @Autowired
    MockMvc mockMvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Test
    void getDocuments() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get(BASE_BATH + "?dogId=" + TestDataInitializer.dog.getId().toString())
                .accept(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + TestDataInitializer.authToken))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(TestDataInitializer.document.getId().toString()));
    }

    @Test
    void postDocument() throws Exception {

        DocumentRequest documentRequest = new DocumentRequest("NeuesDokument", "Zum testen", TestDataInitializer.dog.getId());
        String request = objectMapper.writeValueAsString(documentRequest);

        mockMvc.perform(MockMvcRequestBuilders.post(BASE_BATH)
                        .header("Authorization", "Bearer " + TestDataInitializer.authToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.CREATED.value()));
    }

}