package edu.rutmiit.demo.springai.controllers;

import edu.rutmiit.demo.springai.dtos.PredictionRequestDto;
import edu.rutmiit.demo.springai.dtos.PredictionResponseDto;
import edu.rutmiit.demo.springai.services.PredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PredictionController {

    private final PredictionService predictionService;

    @Autowired
    public PredictionController(PredictionService predictionService) {
        this.predictionService = predictionService;
    }

    @PostMapping("/predict")
    public PredictionResponseDto getPrediction(@RequestBody PredictionRequestDto requestDto) {
        return predictionService.predict(requestDto);
    }
}
