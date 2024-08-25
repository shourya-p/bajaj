package com.example.backend.controller;

import com.example.backend.model.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bfhl")
public class BfhlController {

    @PostMapping
    public ResponseEntity<Response> handlePost(@RequestBody Map<String, Object> request) {
        List<String> data = (List<String>) request.get("data");
        List<String> numbers = new ArrayList<>();
        List<String> alphabets = new ArrayList<>();
        List<String> highestLowercase = new ArrayList<>();

        for (String item : data) {
            if (item.matches("\\d+")) {
                numbers.add(item);
            } else if (item.matches("[a-zA-Z]")) {
                alphabets.add(item);
            }
        }

        String maxLowercase = alphabets.stream()
                .filter(s -> s.chars().allMatch(Character::isLowerCase))
                .sorted((a, b) -> b.compareTo(a))
                .findFirst().orElse("");

        if (!maxLowercase.isEmpty()) {
            highestLowercase.add(maxLowercase);
        }

        Response response = new Response(
            true,
            "john_doe_17091999",
            "john@xyz.com",
            "ABCD123",
            numbers,
            alphabets,
            highestLowercase
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Map<String, Integer>> handleGet() {
        return ResponseEntity.ok(Map.of("operation_code", 1));
    }
}
