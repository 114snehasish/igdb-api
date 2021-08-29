package com.snehasish.igdbapi.controller;

import com.snehasish.igdbapi.utils.Endpoints;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * This controller contains all the endpoints that our UI need to load games. This might include thumbnails to
 * game details page.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/games")
public class GamesController {

    private final WebClient webClient;

    @GetMapping(produces = "application/json")
    public String getGames(@RequestBody String body) {
        WebClient.RequestBodySpec bodySpec = configureAndGetSpec(Endpoints.GAMES);
        WebClient.RequestHeadersSpec<?> headersSpec = bodySpec.bodyValue(body);
        return executeAndGetResponse(headersSpec);
    }

    /**
     * Executes the webclient to fetch the response and return as a string from IGDB api.
     *
     * @param headersSpec the spec it needs to execute
     * @return the response body as string
     */
    private String executeAndGetResponse(WebClient.RequestHeadersSpec<?> headersSpec) {
        Mono<String> res = headersSpec.exchangeToMono(response -> {
            if (response.statusCode()
                    .equals(HttpStatus.OK)) {
                return response.bodyToMono(String.class);
            } else if (response.statusCode()
                    .is4xxClientError()) {
                return response.bodyToMono(String.class);
            } else {
                return response.createException()
                        .flatMap(Mono::error);
            }
        });
        return res.block();
    }

    /**
     * Configures the webclient instance with HTTP POST method and the IGDB endpoint
     *
     * @param endpoint The IGDB endpoint the <code>webClient</code> should call to.
     * @return <code>webClient</code>'s RequestBodySpec. Fully rigged client ready to get executed.
     */
    private WebClient.RequestBodySpec configureAndGetSpec(String endpoint) {
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = webClient.method(HttpMethod.POST);
        return uriSpec.uri(endpoint);
    }
}
