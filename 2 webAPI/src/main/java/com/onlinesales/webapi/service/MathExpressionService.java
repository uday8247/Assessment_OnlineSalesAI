package com.onlinesales.webapi.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class MathExpressionService {

    private final String apiUrl = "URL_OF_THE_MATH_API"; // Replace with the actual API URL

    public List<String> evaluateExpressions(List<String> expressions) {
        List<String> results = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();

        for (String expression : expressions) {
            String result = evaluateExpression(expression, restTemplate);
            results.add(expression + " => " + result);
        }

        return results;
    }

    private String evaluateExpression(String expression, RestTemplate restTemplate) {
        String url = apiUrl + "?expression=" + expression;
        return restTemplate.getForObject(url, String.class);
    }
}
