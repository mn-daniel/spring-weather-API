package com.springapi.weather.controller;

import com.springapi.weather.model.Weather;
import com.springapi.weather.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/weather")
public class WeatherApiRestController {

    @Autowired
    private WeatherRepository weatherDataRepository;

    @PostMapping
    public ResponseEntity<Weather> createWeatherData(@Valid @RequestBody Weather weather) {
        Weather createdWeather = weatherDataRepository.save(weather);
        return new ResponseEntity<Weather>(createdWeather, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Weather>> getWeatherData() {
        List<Weather> weatherData = weatherDataRepository.findAll();
        return new ResponseEntity<List<Weather>>(weatherData, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Weather> getWeatherDataById(@PathVariable(value = "id") Integer id) {
        Optional<Weather> weatherData = weatherDataRepository.findById(id);
        if (!weatherData.isPresent()) {
            return new ResponseEntity<Weather>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Weather>(weatherData.get(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Weather> deleteWeatherData(@PathVariable(value = "id") Integer id) {
        Optional<Weather> weatherData = weatherDataRepository.findById(id);
        if (!weatherData.isPresent()) {
            return new ResponseEntity<Weather>(HttpStatus.NOT_FOUND);
        }
        weatherDataRepository.delete(weatherData.get());
        return new ResponseEntity<Weather>(HttpStatus.NO_CONTENT);
    }
}
