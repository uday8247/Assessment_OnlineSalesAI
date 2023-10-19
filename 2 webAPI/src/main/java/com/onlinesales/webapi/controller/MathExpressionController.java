package com.onlinesales.webapi.controller;

import com.onlinesales.webapi.service.MathExpressionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MathExpressionController {

    @Autowired
    private MathExpressionService mathExpressionService;

    @PostMapping("/evaluate")
    public ResponseEntity<?> evaluateExpressions(@RequestBody List<String> expressions) {
        List<String> results = mathExpressionService.evaluateExpressions(expressions);
        return ResponseEntity.ok(results);
    }
}
