package com.pat.simpleweatherapp.util;

import com.pat.simpleweatherapp.R;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class WeatherIconUtil {
    public static final Map<String, Integer> ICONS;
    static {
        Map<String, Integer> iconMap = new HashMap<>();
        iconMap.put("clear-day", R.drawable.clear_day);
        iconMap.put("clear-night", R.drawable.clear_night);
        iconMap.put("cloudy", R.drawable.cloudy);
        iconMap.put("fog", R.drawable.fog);
        iconMap.put("rain", R.drawable.rain);
        iconMap.put("snow", R.drawable.snow);
        iconMap.put("wind", R.drawable.wind);
        iconMap.put("partly-cloud-night", R.drawable.partly_cloud_night);
        iconMap.put("partly-cloud-day", R.drawable.partly_cloudy_day);
        iconMap.put("thunderstorm", R.drawable.thunderstorm);
        iconMap.put("sleet", R.drawable.sleet);




        ICONS = Collections.unmodifiableMap(iconMap);
    }
}
