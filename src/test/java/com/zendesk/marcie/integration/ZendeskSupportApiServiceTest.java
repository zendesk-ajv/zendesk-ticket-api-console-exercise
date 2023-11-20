package com.zendesk.marcie.integration;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.test.StepVerifier;

import java.io.IOException;

@SpringBootTest
public class ZendeskSupportApiServiceTest {


    @InjectMocks
    private ZendeskSupportApiService zendeskSupportApiService;

    @Value("${zendesk.account.url}")
    private String url;

    @Value("${zendesk.account.username}")
    private String username;

    @Value("${zendesk.account.password}")
    private String password;

    private MockWebServer mockWebServer;
    
    @BeforeEach
    void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();

        String testUrl = String.format("http://localhost:%s",
                mockWebServer.getPort());

        zendeskSupportApiService = new ZendeskSupportApiService(WebClient.builder().baseUrl(testUrl).build());
    }

    @Test
    void getTicketTest() {
        int ticketId = 1;
        //String ticketJson = "{\"field\": \"value\"}"; // replace this with actual ticket data in JSON format

        mockWebServer.enqueue(
                new MockResponse()
                        .addHeader("Content-Type", "application/json; charset=utf-8")
                        //.setBody(ticketJson)
                        .setResponseCode(200)
        );

        StepVerifier.create(zendeskSupportApiService.getTicket(ticketId)).expectComplete();
                //.assertNext(null)
                //.verifyComplete();
    }


    @Test
    void getAllTicketTest() {
        //int ticketId = 1;
        //String ticketJson = "{\"field\": \"value\"}"; // replace this with actual ticket data in JSON format

        mockWebServer.enqueue(
                new MockResponse()
                        .addHeader("Content-Type", "application/json; charset=utf-8")
                        //.setBody(ticketJson)
                        .setResponseCode(200)
        );

        StepVerifier.create(zendeskSupportApiService.getAllTicket())//.expectNextCount(1)
                //.assertNext(null)
                .verifyComplete();
    }

    
    @AfterEach
    void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

}