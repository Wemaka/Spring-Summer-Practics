package edu.rutmiit.demo.springai.dtos;

public class PredictionRequestDto {

	private String pClass;
	private String sex;
	private String embarked;

    private double age;
    private double fare;

    public PredictionRequestDto() {}

	public String getpClass() {
		return pClass;
	}

	public String getSex() {
		return sex;
	}

	public String getEmbarked() {
		return embarked;
	}

	public double getAge() {
		return age;
	}

	public double getFare() {
		return fare;
	}

	public void setpClass(String pClass) {
		this.pClass = pClass;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public void setEmbarked(String embarked) {
		this.embarked = embarked;
	}

	public void setAge(double age) {
		this.age = age;
	}

	public void setFare(double fare) {
		this.fare = fare;
	}

	@Override
	public String toString() {
		return "PredictionRequestDto{" +
				"sex='" + sex + '\'' +
				", embarked='" + embarked + '\'' +
				", pClass=" + pClass +
				", age=" + age +
				", fare=" + fare +
				'}';
	}
}