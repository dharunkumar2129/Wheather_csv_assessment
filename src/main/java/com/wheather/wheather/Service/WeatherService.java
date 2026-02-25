package com.wheather.wheather.Service;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVReader;
import com.wheather.wheather.dto.WeatherResponseDTO;
import com.wheather.wheather.entity.WeatherData;
import com.wheather.wheather.filter.WheatherSpecification;
import com.wheather.wheather.repository.WeatherRepository;


@Service
public class WeatherService {

	@Autowired
	private WeatherRepository repository;


	public void uploadCsv(MultipartFile file) throws Exception {

		// OpenCSV reads the CSV into a List of String arrays
		CSVReader reader = new CSVReader(
				new InputStreamReader(file.getInputStream()));

		List<String[]> rows = reader.readAll(); 
		reader.close();

		List<WeatherData> list = new ArrayList<>();

		for (int i = 1; i < rows.size(); i++) {
			String[] row = rows.get(i);

			WeatherData wd = new WeatherData();
			wd.setDatetimeUtc(row[0].trim()); 
			wd.setConditions(row[1].trim()); 
			wd.setDewPoint(parseDouble(row[2])); 
			wd.setHumidity(parseDouble(row[6])); 
			wd.setPrecipitation(parseDouble(row[7]));
			wd.setPressure(parseDouble(row[8]));
			wd.setTemperature(parseDouble(row[11])); 
			wd.setVisibility(parseDouble(row[14]));
			wd.setWindDirDeg(parseInteger(row[15])); 
			wd.setWindDirStr(row[16].trim()); 
			wd.setWindSpeed(parseDouble(row[19]));
			list.add(wd);
		}

		
		repository.saveAll(list);
	}



	public Page<WeatherResponseDTO> getWeatherData(
			String conditions,
			Double minTemp,
			Double maxTemp,
			Double minHumidity,
			Double maxHumidity,
			Pageable pageable) {

		
		Specification<WeatherData> spec = WheatherSpecification.buildSpecification(
				conditions, minTemp, maxTemp, minHumidity, maxHumidity);

		
		Page<WeatherData> page = repository.findAll(spec, pageable);

		
		return page.map(this::toDto);
	}



	private WeatherResponseDTO toDto(WeatherData wd) {
		WeatherResponseDTO dto = new WeatherResponseDTO();
		dto.setId(wd.getId());
		dto.setDatetimeUtc(wd.getDatetimeUtc());
		dto.setConditions(wd.getConditions());
		dto.setTemperature(wd.getTemperature());
		dto.setHumidity(wd.getHumidity());
		dto.setWindSpeed(wd.getWindSpeed());
		dto.setPressure(wd.getPressure());
		dto.setVisibility(wd.getVisibility());
		return dto;
	}



	private Double parseDouble(String value) {
		if (value == null)
			return null;
		value = value.trim();
		if (value.isEmpty() || value.equalsIgnoreCase("N/A") || value.equals("-9999")) {
			return null;
		}
		try {
			return Double.parseDouble(value);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	private Integer parseInteger(String value) {
		if (value == null)
			return null;
		value = value.trim();
		if (value.isEmpty() || value.equalsIgnoreCase("N/A") || value.equals("-9999")) {
			return null;
		}
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			return null;
		}
	}
}
