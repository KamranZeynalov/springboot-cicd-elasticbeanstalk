package com.company.cloudapp;

import com.company.cloudapp.entity.Ip;
import com.company.cloudapp.exception.IpNotFoundException;
import com.company.cloudapp.model.ip.IpCreationRequest;
import com.company.cloudapp.model.ip.IpUpdateRequest;
import com.company.cloudapp.service.IpService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Sql(scripts = "/sql/testIpControllerQuery.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@Transactional
public class IpControllerTest extends BaseTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private IpService ipService;

    @Test
    public void testGetIpById() throws Exception {
        mockMvc.perform(get("/api/v1/ip/{id}", IP_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(IP_ID))
                .andExpect(jsonPath("$.serviceName").value(IP_SERVICE_NAME))
                .andExpect(jsonPath("$.projectId").value(PROJECT_ID))
                .andExpect(jsonPath("$.env").value(IP_TEST_ENV.name()));
    }

    @Test
    public void testCreateIp() throws Exception {

        IpCreationRequest request = IpCreationRequest.builder()
                                                     .name(IP_SERVICE_NAME)
                                                     .projectId(PROJECT_ID)
                                                     .env(IP_TEST_ENV)
                                                     .build();

        mockMvc.perform(post("/api/v1/ip")
                        .content(mapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").value(2));

        Ip insertedIp = ipService.findIpById(2L);

        Assertions.assertNotNull(insertedIp);

        Assertions.assertEquals(insertedIp.getServiceName(), IP_SERVICE_NAME);
        Assertions.assertEquals(insertedIp.getProject().getId(), PROJECT_ID);
        Assertions.assertEquals(insertedIp.getEnv(), IP_TEST_ENV);
        Assertions.assertNotNull(insertedIp.getCreatedAt());
        Assertions.assertNotNull(insertedIp.getUpdatedAt());

    }

    @Test
    public void testUpdateIp() throws Exception {

        IpUpdateRequest request = IpUpdateRequest.builder()
                                                 .name(UPDATED_IP_SERVICE_NAME)
                                                 .env(IP_DEV_ENV)
                                                 .build();

        mockMvc.perform(put("/api/v1/ip/{id}", IP_ID)
                        .content(mapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(IP_ID))
                .andExpect(jsonPath("$.serviceName").value(UPDATED_IP_SERVICE_NAME))
                .andExpect(jsonPath("$.projectId").value(PROJECT_ID))
                .andExpect(jsonPath("$.env").value(IP_DEV_ENV.name()));

        Ip updatedIp = ipService.findIpById(IP_ID);

        Assertions.assertNotNull(updatedIp);

        Assertions.assertEquals(updatedIp.getServiceName(), UPDATED_IP_SERVICE_NAME);
        Assertions.assertEquals(updatedIp.getEnv(), IP_DEV_ENV);

    }

    @Test
    public void testDeleteIpById()  throws Exception {

        mockMvc.perform(delete("/api/v1/ip/{id}", IP_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Assertions.assertThrows(IpNotFoundException.class, () -> ipService.findIpById(IP_ID));

    }
}
