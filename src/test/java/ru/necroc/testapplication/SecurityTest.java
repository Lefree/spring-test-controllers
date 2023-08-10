package ru.necroc.testapplication;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


@SpringBootTest
@AutoConfigureMockMvc
public class SecurityTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "user", roles = "USER", password = "password")
    public void testUserAccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/securityTest"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN", password = "admin_password")
    public void testAdminAccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/securityTest").accept(MediaType.ALL))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Ok")));
    }
}
