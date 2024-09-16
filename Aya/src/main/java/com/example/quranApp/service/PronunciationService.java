package com.example.quranApp.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


@Service
public class PronunciationService {


    private static String API_URL;

    @Value("${API_URL}")
    public void setApiUrl(String apiUrl) {
        API_URL = apiUrl;
    }

    private static String AUTH_TOKEN;

    @Value("${AUTH_TOKEN}")
    public void setToken(String token) {
        AUTH_TOKEN = token;
    }

    public static String sendAudioFile(byte[] audioBytes) throws IOException, InterruptedException {


        // Create an HttpClient
        HttpClient client = HttpClient.newHttpClient();

        // Create a POST request with the file data and headers
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .header("Authorization", AUTH_TOKEN)
                .header("Content-Type", "audio/mpeg")
                .POST(HttpRequest.BodyPublishers.ofByteArray(audioBytes)) // Send the file as byte array
                .build();

        // Send the request and get the response
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Return the response body as a string
        return response.body();
    }

    static String parse(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c <= 'ى' && c >= 'ا')
                sb.append(c);
        }
        return sb.toString();
    }

    public String checkPronunciation(byte[] audioBytes) throws IOException, InterruptedException {
        System.out.println("analyzing");
        return parse(sendAudioFile(audioBytes));
    }
}
