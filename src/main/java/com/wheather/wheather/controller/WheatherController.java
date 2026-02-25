package com.wheather.wheather.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.wheather.wheather.Service.WeatherService;
import com.wheather.wheather.dto.WeatherResponseDTO;


@RestController
@RequestMapping("/weather")
public class WheatherController {

	@Autowired
	private WeatherService service;


	@PostMapping("/upload")
	public ResponseEntity<String> upload(
			@RequestParam("file") MultipartFile file) throws Exception {

		service.uploadCsv(file);
		return ResponseEntity.ok("File Uploaded Successfully");
	}


	@GetMapping
	public Page<WeatherResponseDTO> getWeather(
			@RequestParam(required = false) String conditions,
			@RequestParam(required = false) Double minTemp,
			@RequestParam(required = false) Double maxTemp,
			@RequestParam(required = false) Double minHumidity,
			@RequestParam(required = false) Double maxHumidity,
			@PageableDefault(size = 20) Pageable pageable) {

		return service.getWeatherData(
				conditions, minTemp, maxTemp,
				minHumidity, maxHumidity, pageable);
	}
}
