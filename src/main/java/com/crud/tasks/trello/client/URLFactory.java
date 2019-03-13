package com.crud.tasks.trello.client;

import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

public class URLFactory {
    private final String trelloApiEndpoint;
    private final String trelloUsername;
    private final String trelloAppKey;
    private final String trelloToken;

    private URI url;

    public URLFactory(String trelloApiEndpoint, String trelloUsername, String trelloAppKey, String trelloToken) {
        this.trelloApiEndpoint = trelloApiEndpoint;
        this.trelloUsername = trelloUsername;
        this.trelloAppKey = trelloAppKey;
        this.trelloToken = trelloToken;
    }

    private URI makingURL() {
        url = UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "/members/" + trelloUsername + "/boards")
                .queryParam("key", trelloAppKey)
                .queryParam("token", trelloToken)
                .queryParam("fields", "name,id").build().encode().toUri();

        return url;
    }

    public URI getUrl() {
        makingURL();
        return url;
    }
}
