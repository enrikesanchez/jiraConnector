package com.jira.connector.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jira.connector.valueobject.Issue;

@Service
public class JiraService {
    Logger logger = LoggerFactory.getLogger(JiraService.class);

    @Value("${jira.api.url}")
    private String jiraApiUrl;

    @Value("${jira.api.token}")
    private String jiraApiToken;

    @Autowired
    HttpClient client;


    public Issue getIssue(final String key) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(new URI(jiraApiUrl + key))
            .header(HttpHeaders.AUTHORIZATION, "Basic " + jiraApiToken)
            .GET()
            .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());
        logger.debug("Response Http Status {}", response.statusCode());
        logger.debug("Response Body {}", response.body());

        JsonObject issueJson = JsonParser.parseString(response.body())
    .getAsJsonObject();
        JsonObject fieldsJson = issueJson.getAsJsonObject("fields");
        JsonObject statusJson = fieldsJson.getAsJsonObject("status");

        Issue issue = new Issue();
        issue.setKey(issueJson.get("key").getAsString());
        issue.setStatus(statusJson.get("name").getAsString());
        issue.setDescription(fieldsJson.get("summary").getAsString());

        return issue;
    }
}
