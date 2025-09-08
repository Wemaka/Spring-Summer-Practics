package edu.rutmiit.demo.springai.dtos;

public class PredictionResponseDto {

    private double isSurvived;

    public PredictionResponseDto(double isSurvived) {
        this.isSurvived = isSurvived;
    }

    public PredictionResponseDto() {}

    public double getIsSurvived() {
        return isSurvived;
    }

    public void setIsSurvived(double isSurvived) {
        this.isSurvived = isSurvived;
    }
}
