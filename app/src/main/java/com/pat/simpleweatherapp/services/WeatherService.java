package com.pat.simpleweatherapp.services;

import com.pat.simpleweatherapp.model.Weather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WeatherService {

    @GET("{lat}.{lon}")
        Call<Weather> getWeather(@Path("lat") double lat, @Path("lng") double lng);
}
