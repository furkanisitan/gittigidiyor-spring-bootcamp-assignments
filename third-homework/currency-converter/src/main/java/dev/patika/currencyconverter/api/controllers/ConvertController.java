package dev.patika.currencyconverter.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/convert")
public class ConvertController {

    @GetMapping
    public ResponseEntity<Object> convert(@RequestParam String from, @RequestParam String to, @RequestParam double amount) {

        if (from.equals("USD") && to.equals("TRY"))
            return ResponseEntity.ok(amount * 8);

        if (from.equals("TRY") && to.equals("UDS"))
            return ResponseEntity.ok(amount / 8);

        return ResponseEntity.badRequest().body("Invalid parameter(s).");
    }

}
