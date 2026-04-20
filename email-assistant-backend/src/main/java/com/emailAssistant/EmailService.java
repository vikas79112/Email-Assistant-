package com.emailAssistant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

@Service
public class EmailService {
    private final WebClient webClient;
    private final String apiKey;

    public EmailService(WebClient.Builder webClientBuilder,
                        @Value("${gemini.api.url}") String baseUrl,
                        @Value("${gemini.api.key}") String geminiApiKey ) {
        this.apiKey = geminiApiKey;
        this.webClient= webClientBuilder.baseUrl(baseUrl).build();
    }

    public String generateEmailReply(EmailRequest emailRequest) {
        String prompt=buildPrompt(emailRequest);
        String requestBody=String.format("""
                {
                    "contents": [
                      {
                        "parts": [
                          {
                            "text": "%s"
                          }
                        ]
                      }
                    ]
                  }""", prompt);
        String response=webClient.post().uri(uriBuilder -> uriBuilder.
                path("/v1/models/gemini-2.5-flash-lite:generateContent").
                build()).header("x-goog-api-key",apiKey).
                header("Content-Type","application/json").
                bodyValue(requestBody).retrieve().
                bodyToMono(String.class).block();

        return extractResponseContent(response);


    }

    private String extractResponseContent(String response) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response);

            return root.path("candidates")
                    .get(0)
                    .path("content")
                    .path("parts")
                    .get(0)
                    .path("text")
                    .asText();

        } catch (Exception e) {
            return "Error parsing AI response: " + e.getMessage();
        }
    }



    private String buildPrompt(EmailRequest emailRequest) {
        StringBuilder prompt=new StringBuilder();
        prompt.append("Generate a proffesional email reply for the following email:");
        if(emailRequest.getTone()!=null && !emailRequest.getTone().isEmpty() ){
            prompt.append("Use a").append(emailRequest.getTone()).append("tone");
        }
        prompt.append("Original Email: \n").append(emailRequest.getEmailContent());
        return prompt.toString();
    }
}
