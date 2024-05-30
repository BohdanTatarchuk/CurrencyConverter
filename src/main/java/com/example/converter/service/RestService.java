package com.example.converter.service;

import com.example.converter.model.ConvertsInto;

import com.google.gson.Gson;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Service;

import java.io.IOException;


import java.net.ConnectException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RestService {

    public HttpResponse<String> connect(String apiKey, String apiUrl) throws IOException, InterruptedException, URISyntaxException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(apiUrl + apiKey))
                .GET()
                .build();
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public boolean checkConnection(HttpResponse<String> response) {
        return response.statusCode() == 200;
    }

    public List<ConvertsInto> getResult(HttpResponse<String> response) throws IOException {
        if (!checkConnection(response)) {
            throw new ConnectException();
        }

        List<ConvertsInto> result = new ArrayList<>();
        String dataString = response.body();

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        JsonObject jsonObject = gson.fromJson(dataString, JsonObject.class);

        JsonObject data = jsonObject.getAsJsonObject("data");

        for (Map.Entry<String, JsonElement> entry : data.entrySet()) {
            result.add(new ConvertsInto(
                    "USD",
                    entry.getKey(),
                    entry.getValue().getAsDouble()
                    )
            );
        }

        return result;
    }
}
