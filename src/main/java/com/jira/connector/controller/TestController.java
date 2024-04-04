package com.jira.connector.controller;

import java.io.IOException;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jira.connector.service.JiraService;
import com.jira.connector.valueobject.Issue;

@RestController
public class TestController {
    Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    JiraService jiraService;

    @GetMapping("/test-jira")
    public ResponseEntity<Issue> testJira() {
        Issue issue;
        try {
            issue = jiraService.getIssue("SCRUM-1");
        } catch (URISyntaxException | IOException | InterruptedException e) {
            logger.error("JIRA API failed", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);    
        }

        return new ResponseEntity<>(issue, HttpStatus.OK);
    }
}
