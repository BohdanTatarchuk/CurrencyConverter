package com.example.converter.service;

import com.example.converter.model.ConvertsInto;

import com.google.gson.Gson;


import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestService {

    @Value("${api.base.url}")
    private String apiUrl;

    @Value("${api.key}")
    private String apiKey;

    public HttpResponse<String> connect(String apiUrl) throws IOException, InterruptedException, URISyntaxException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(apiUrl))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response;
    }

    public boolean checkConnection(HttpResponse<String> response) {
        return response.statusCode() == 200;
    }

    public List<ConvertsInto> getResults(HttpResponse<String> response) throws IOException {
        List<ConvertsInto> result = new ArrayList<>();
        String data = response.body();

        FileWriter fileWriter = new FileWriter("data.json", true);
        fileWriter.write(data);
        fileWriter.close();

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        ConvertsInto convertsInto = gson.fromJson(data, ConvertsInto.class);

        return result;
    }


    public RestService() {

    }
}
