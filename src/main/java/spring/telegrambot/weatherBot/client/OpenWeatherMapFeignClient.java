package spring.telegrambot.weatherBot.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import spring.telegrambot.weatherBot.data.weather.Weather_Root;

@FeignClient(name = "openWeather", url = "https://api.openweathermap.org")
public interface OpenWeatherMapFeignClient {
    @GetMapping("/data/2.5/weather")
    String request(@RequestParam("lat") String lat,
                   @RequestParam("lon") String lon,
                   @RequestParam("appid") String appid,
                   @RequestParam("lang") String lang);


    @GetMapping("/data/2.5/weather")
    Weather_Root requestR(@RequestParam("lat") String lat,
                         @RequestParam("lon") String lon,
                         @RequestParam("appid") String appid,
                         @RequestParam("lang") String lang);

}
