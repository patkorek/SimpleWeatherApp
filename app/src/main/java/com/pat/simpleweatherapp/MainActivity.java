package com.pat.simpleweatherapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pat.simpleweatherapp.events.ErrorEvent;
import com.pat.simpleweatherapp.events.WeatherEvent;
import com.pat.simpleweatherapp.model.Currently;
import com.pat.simpleweatherapp.services.WeatherServiceProvider;
import com.pat.simpleweatherapp.util.WeatherIconUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tempTextView)
    TextView tempTextView;

    @BindView(R.id.iconImageView)
    ImageView iconImageView;

    @BindView(R.id.summaryTextView)
    TextView summaryTextView;

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        requestCurrentWeather(37.8267,-122.4233);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onWeatherEvent(WeatherEvent weatherEvent) {
        Currently currently = weatherEvent.getWeather().getCurrently();
        tempTextView.setText(String.valueOf(currently.getTemperature()));
        summaryTextView.setText(currently.getSummary());

        iconImageView.setImageResource(WeatherIconUtil.ICONS.get(currently.getIcon()));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onErrorEvent(ErrorEvent errorEvent)
    {
        Toast.makeText(this, errorEvent.getErrorMsg(), Toast.LENGTH_SHORT).show();
    }
    private void requestCurrentWeather(double lat, double lng) {
        WeatherServiceProvider weatherServiceProvider = new WeatherServiceProvider();
        weatherServiceProvider.getWeather(lat, lng);
    }
}
