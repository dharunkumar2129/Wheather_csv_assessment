package com.wheather.wheather.dto;


public class WeatherResponseDTO {

	private Long id;
	private String datetimeUtc;
	private String conditions;
	private Double temperature;
	private Double humidity;
	private Double windSpeed;
	private Double pressure;
	private Double visibility;

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDatetimeUtc() {
		return datetimeUtc;
	}

	public void setDatetimeUtc(String datetimeUtc) {
		this.datetimeUtc = datetimeUtc;
	}

	public String getConditions() {
		return conditions;
	}

	public void setConditions(String conditions) {
		this.conditions = conditions;
	}

	public Double getTemperature() {
		return temperature;
	}

	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}

	public Double getHumidity() {
		return humidity;
	}

	public void setHumidity(Double humidity) {
		this.humidity = humidity;
	}

	public Double getWindSpeed() {
		return windSpeed;
	}

	public void setWindSpeed(Double windSpeed) {
		this.windSpeed = windSpeed;
	}

	public Double getPressure() {
		return pressure;
	}

	public void setPressure(Double pressure) {
		this.pressure = pressure;
	}

	public Double getVisibility() {
		return visibility;
	}

	public void setVisibility(Double visibility) {
		this.visibility = visibility;
	}
}