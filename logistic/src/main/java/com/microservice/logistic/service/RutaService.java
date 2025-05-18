package com.microservice.logistic.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RutaService {

    @Value("${google.maps.api.key}")
    private String apiKey;

    private final String baseUrl = "https://maps.googleapis.com/maps/api/distancematrix/json";

    public String calcularRuta(String origen, String destino){
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format("%s?origins=%s&destinations=%s&key=%s",
                baseUrl,
                origen.replace(" ", "+"),
                destino.replace(" ", "+"),
                apiKey);

        return restTemplate.getForObject(url, String.class);
    }
}
