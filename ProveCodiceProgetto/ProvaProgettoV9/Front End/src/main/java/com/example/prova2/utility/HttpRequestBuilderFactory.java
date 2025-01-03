package com.example.prova2.utility;

import java.net.URI;
import java.net.http.HttpRequest;

public class HttpRequestBuilderFactory {
    private HttpRequestBuilderFactory() {}

    public static HttpRequest buildPOST(final String BASE_URL, final String jsonInputString) {
        return HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .headers("Content-type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonInputString))
                .build();
    }
}
