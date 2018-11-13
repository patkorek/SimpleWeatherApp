package com.pat.simpleweatherapp.services;

import android.util.Log;
import android.widget.Toast;

import com.pat.simpleweatherapp.events.ErrorEvent;
import com.pat.simpleweatherapp.events.WeatherEvent;
import com.pat.simpleweatherapp.model.Currently;
import com.pat.simpleweatherapp.model.Weather;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherServiceProvider {
    private static final String TAG = WeatherServiceProvider.class.getSimpleName();
    private static final String BASE_URL = "https://api.darksky.net/forecast/52ddb479eee3e6bbfdf760e0e9d28329/";
    private Retrofit retrofit;

    Retrofit getRetrofit() {
        if (this.retrofit == null) {
            this.retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return this.retrofit;
    }

    public void getWeather(double lat, double lng) {

        WeatherService weatherService = retrofit.create(WeatherService.class);
        Call<Weather> weatherData = weatherService.getWeather(lat, lng);

        weatherData.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                Weather weather = response.body();
                if (weather != null) {
                    Currently currently = weather.getCurrently();
                    EventBus.getDefault().post(new WeatherEvent(weather));
                } else {
                    Log.e(TAG, "No response: check secret key");
                    EventBus.getDefault().post(new ErrorEvent("No weather data avaible"));
                }
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                EventBus.getDefault().post(new ErrorEvent("Unable to connect weather server"));
            }
        });
    }
}
