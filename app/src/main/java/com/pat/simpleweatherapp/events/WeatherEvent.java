package com.pat.simpleweatherapp.events;

import com.pat.simpleweatherapp.model.Weather;

public class WeatherEvent {
    private final Weather weather;

    public WeatherEvent(Weather weather) {
        this.weather = weather;
    }

    public Weather getWeather() {
        return weather;
    }

}
