package com.project.fxgames.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.project.fxgames.entity.Record;
import com.project.fxgames.entity.RecordId;
import com.project.fxgames.service.FXGamesService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.net.URI;
import java.time.LocalDateTime;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(FXGamesController.class)
public class FXGamesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FXGamesService fxGamesService;

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8080);

    @Before
    public void setUp() {
        String userId = "user1";
        String recordId = "123";
        String message = "New Record";
        Record record = Record.builder()
                .recordId(RecordId.builder().id(recordId).userId(userId).build())
                .message(message)
                .build();
        when(fxGamesService.getRecord("user1", "123")).thenReturn(record);
        when(fxGamesService.changeRecord("user1", "123", "New Record")).thenReturn(record);
    }

    @Test
    public void testGetRecord() throws Exception {
        String url = "/fxgames/get/user1/123";
        String userId = "user1";
        String recordId = "123";
        String message = "New Record";
        Record record = Record.builder()
                .recordId(RecordId.builder().id(recordId).userId(userId).build())
                .message(message)
                .build();

        String expectedResponse = new ObjectMapper().writeValueAsString(record);

        wireMockRule.stubFor(get(urlEqualTo(url))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(expectedResponse)));

        mockMvc.perform(get(URI.create(url)))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResponse));
    }

    @Test
    public void testSetRecord() throws Exception {
        String url = "/fxgames/set/user1/123";
        String userId = "user1";
        String recordId = "123";
        String message = "New Record";
        Record record = Record.builder()
                .recordId(RecordId.builder().id(recordId).userId(userId).build())
                .message(message)
                .build();

        String expectedResponse = new ObjectMapper().writeValueAsString(record);

        wireMockRule.stubFor(post(urlEqualTo(url))
                .withRequestBody(WireMock.equalToJson(expectedResponse))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(expectedResponse)));

        mockMvc.perform(post(URI.create(url))
                        .contentType("application/json")
                        .content(message))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResponse));

    }
}
