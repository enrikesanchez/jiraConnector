package com.jira.connector.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/test-jira")
    public ResponseEntity<String> testJira() {
        HttpClient client = HttpClient.newHttpClient();
        
        try {
            HttpRequest request = HttpRequest.newBuilder()
            .uri(new URI("https://postman-echo.com/get"))
            .GET()
            .build();

            HttpResponse<Void> response = client.send(request,
                    HttpResponse.BodyHandlers.discarding());
            System.out.println(response.statusCode());
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return new ResponseEntity<>("Test", HttpStatus.OK);
    }
}
