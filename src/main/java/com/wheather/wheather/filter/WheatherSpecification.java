package com.wheather.wheather.filter;

import com.wheather.wheather.entity.WeatherData;
import org.springframework.data.jpa.domain.Specification;


public class WheatherSpecification {

   
    public static Specification<WeatherData> buildSpecification(
            String conditions,
            Double minTemp,
            Double maxTemp,
            Double minHumidity,
            Double maxHumidity) {

       
        Specification<WeatherData> spec = Specification.where(null);

   
        if (conditions != null && !conditions.isBlank()) {
            spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("conditions")),
                    "%" + conditions.toLowerCase() + "%"));
        }

        if (minTemp != null) {
            spec = spec.and((root, query, cb) -> cb.greaterThanOrEqualTo(root.get("temperature"), minTemp));
        }

        
        if (maxTemp != null) {
            spec = spec.and((root, query, cb) -> cb.lessThanOrEqualTo(root.get("temperature"), maxTemp));
        }

       
        if (minHumidity != null) {
            spec = spec.and((root, query, cb) -> cb.greaterThanOrEqualTo(root.get("humidity"), minHumidity));
        }

       
        if (maxHumidity != null) {
            spec = spec.and((root, query, cb) -> cb.lessThanOrEqualTo(root.get("humidity"), maxHumidity));
        }

        return spec;
    }
}
