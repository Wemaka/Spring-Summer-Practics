package edu.rutmiit.demo.springai.services;

import edu.rutmiit.demo.springai.dtos.PredictionRequestDto;
import edu.rutmiit.demo.springai.dtos.PredictionResponseDto;

public interface PredictionService {
    PredictionResponseDto predict(PredictionRequestDto requestDto);
}
