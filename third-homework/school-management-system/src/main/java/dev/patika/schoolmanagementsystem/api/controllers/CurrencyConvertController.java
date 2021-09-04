package dev.patika.schoolmanagementsystem.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/currency-convert")
public class CurrencyConvertController {

    private static final String API_URL = "http://localhost:8081/api/convert";

    private final RestTemplate restTemplate;

    @Autowired
    public CurrencyConvertController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public ResponseEntity<Object> convert(@RequestParam String from, @RequestParam String to, @RequestParam double amount) {

        Object res = restTemplate.getForObject(String.format("%s?from=%s&to=%s&amount=%s", API_URL, from, to, amount), Object.class);

        return ResponseEntity.ok(res);
    }
}
