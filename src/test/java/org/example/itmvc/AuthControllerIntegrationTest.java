package org.example.itmvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.AuthenticationRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ActiveProfiles("test")
@SpringBootTest
public class AuthControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void authController_validateToken() throws Exception {
        AuthenticationRequest request = new AuthenticationRequest();
        request.email = "d@gmail.com";
        request.password = "password";

        MvcResult resultFailed = mockMvc
                .perform(MockMvcRequestBuilders.post("/auth/authenticate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isForbidden())
                .andReturn();

        String resultContent1 = resultFailed.getResponse().getContentAsString();
        assertNotNull(resultContent1);


        AuthenticationRequest requestTwo = new AuthenticationRequest();
        requestTwo.email = "dluis1651@gmail.com";
        requestTwo.password = "password";

        MvcResult resultSuccess = mockMvc
                .perform(MockMvcRequestBuilders.post("/auth/authenticate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestTwo))
                )
                .andExpect(status().isOk())
                .andReturn();

        String resultContent2 = resultSuccess.getResponse().getContentAsString();
        assertNotNull(resultContent2);



    }
}
