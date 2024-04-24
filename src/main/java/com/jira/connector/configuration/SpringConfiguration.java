package com.jira.connector.configuration;

import java.net.http.HttpClient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfiguration {
    @Bean
    public HttpClient httpClient() {
      return HttpClient.newHttpClient();
    }
  
}
