package edu.rutmiit.demo.springai.services.impl;

import ai.catboost.CatBoostError;
import ai.catboost.CatBoostModel;
import ai.catboost.CatBoostPredictions;
import edu.rutmiit.demo.springai.dtos.PredictionRequestDto;
import edu.rutmiit.demo.springai.dtos.PredictionResponseDto;
import edu.rutmiit.demo.springai.services.PredictionService;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CatBoostPredictionServiceImpl implements PredictionService {

    private static final Logger logger = LoggerFactory.getLogger(CatBoostPredictionServiceImpl.class);
    private CatBoostModel model;
    private final String modelPath = "/catboost.cbm";

    @PostConstruct
    public void init() {
        try {
            model = CatBoostModel.loadModel(getClass().getResourceAsStream(modelPath));
        } catch (IOException e) {
            throw new RuntimeException("Could not load CatBoost model from " + modelPath, e);
        } catch (CatBoostError e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public PredictionResponseDto predict(PredictionRequestDto requestDto) {
        String[] catFeatures = new String[] {
                requestDto.getpClass(),
                requestDto.getSex(),
		        requestDto.getEmbarked()
        };

        float[] numFeatures = new float[] {
                (float) requestDto.getAge(),
                (float) requestDto.getFare()
        };

	    logger.info("requestDto: {}", requestDto);

	    try {
            CatBoostPredictions prediction = model.predict(numFeatures, catFeatures);
            double predictedValue = prediction.get(0, 0);
            logger.info("Model returned prediction: {}", predictedValue);

		    // Преобразуем логит в вероятность
		    double probability = 1.0 / (1.0 + Math.exp(-predictedValue));
		    // Применяем порог для получения класса (0 или 1)
		    int survivedClass = (probability > 0.5) ? 1 : 0;
		    logger.info("Logit: {}, Probability: {}, Survived: {}", predictedValue,
				    probability, survivedClass);

            return new PredictionResponseDto(survivedClass);
        } catch (Exception e) {
            throw new RuntimeException("Failed to get prediction", e);
        }
    }
}
