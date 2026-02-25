package com.wheather.wheather.repository;

import com.wheather.wheather.entity.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepository extends
                JpaRepository<WeatherData, Long>,
                JpaSpecificationExecutor<WeatherData> {
}
