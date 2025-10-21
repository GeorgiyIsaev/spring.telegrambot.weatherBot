package spring.telegrambot.weatherBot.controler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import spring.telegrambot.weatherBot.client.OpenWeatherMapFeignClient;
import spring.telegrambot.weatherBot.data.district.Coordinates;
import spring.telegrambot.weatherBot.data.weather.Weather_Root;


@Component
public class OpenWeatherMapController {
    private final OpenWeatherMapFeignClient openWeatherMapFeignClient;
    private final String keyOpenWeather; // Ключ доступа к сайту погоды

    public OpenWeatherMapController(
            @Value("${telegram.weather_token}") String keyOpenWeather,
            OpenWeatherMapFeignClient openWeatherMapFeignClient) {
        this.openWeatherMapFeignClient = openWeatherMapFeignClient;
        this.keyOpenWeather = keyOpenWeather;
    }

    public String infoCurrentWeather(Coordinates coordinates){
        String lat = "" + coordinates.latitude();
        String lon = "" + coordinates.longitude();
        String language = "ru";
        Weather_Root weather = openWeatherMapFeignClient.requestR(lat, lon, keyOpenWeather, language);
        return weather.toString();
    }
}
