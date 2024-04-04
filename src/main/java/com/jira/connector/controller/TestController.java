package com.jira.connector.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    Logger logger = LoggerFactory.getLogger(TestController.class);

    @GetMapping("/test-jira")
    public ResponseEntity<String> testJira() {
        HttpClient client = HttpClient.newHttpClient();
        
        try {
            HttpRequest request = HttpRequest.newBuilder()
            .uri(new URI("https://project.atlassian.net/rest/api/2/issue/KEY"))
            .header(HttpHeaders.AUTHORIZATION, "Basic BASE64")
            .GET()
            .build();

            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
            logger.debug("Response Http Status {}", response.statusCode());
            logger.debug("Response Body {}", response.body());
        } catch (final URISyntaxException use) {
            logger.error("Error reading the url", use);
        } catch (final IOException ioe) {
            logger.error("Communication Error", ioe);
        } catch (final InterruptedException ie) {
            logger.error("Request has been cancelled", ie);
        }

        return new ResponseEntity<>("Test", HttpStatus.OK);
    }
}
