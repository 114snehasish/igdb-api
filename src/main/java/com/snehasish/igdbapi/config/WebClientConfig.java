package com.snehasish.igdbapi.config;

import com.snehasish.igdbapi.config.model.ApiConfiguration;
import com.snehasish.igdbapi.config.model.ApiCredentials;
import com.snehasish.igdbapi.config.repository.ApiConfigurationRepository;
import com.snehasish.igdbapi.config.repository.ApiCredentialsRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient(ApiCredentialsRepository apiCredentialsRepository, ApiConfigurationRepository apiConfigurationRepository) {
        ApiCredentials credentials = apiCredentialsRepository.findById(1)
                .orElseThrow(() -> new RuntimeException("Credentials Not found."));
        ApiConfiguration configuration = apiConfigurationRepository.findById(1)
                .orElseThrow(() -> new RuntimeException("Configuration Not found"));
        return WebClient.builder()
                .baseUrl(configuration.getBaseUrl())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader("Client-ID", credentials.getClientId())
                .defaultHeader("Authorization", "Bearer " + credentials.getBearerToken())
                .defaultUriVariables(Collections.singletonMap("url", configuration.getBaseUrl()))
                .build();
    }

}
