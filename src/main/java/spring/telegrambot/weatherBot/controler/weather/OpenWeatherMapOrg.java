package spring.telegrambot.weatherBot.controler.weather;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import spring.telegrambot.weatherBot.data.district.Coordinates;
import spring.telegrambot.weatherBot.data.weather.Weather_Root;
import spring.telegrambot.weatherBot.logger.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Component
public class OpenWeatherMapOrg {

    private final String keyOpenWeather; // Ключ доступа к сайту погоды
    private final Logger logger;

    public OpenWeatherMapOrg(
            Logger logger,
            @Value("${telegram.weather_token}")String keyOpenWeather) {
        this.keyOpenWeather = keyOpenWeather;
        this.logger = logger;
    }

    private URL getUrl(Coordinates coordinates) {
        URL url = null;
        try{
            url = new URL("https://api.openweathermap.org/data/2.5/weather?lat=" +
                    coordinates.latitude() + "&lon=" +
                    coordinates.longitude() + "&appid=" +
                    keyOpenWeather + "&lang=ru");
        }
        catch (MalformedURLException e){
            logger.logException("URL openweathermap.org not formed: ", e);
        }
        return url;
    }
    private String currentWeather(Coordinates coordinates){
        StringBuilder content = new StringBuilder();
        URL url = getUrl(coordinates);
        try {
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String input;
            while ((input = in.readLine()) != null)
            {
                content.append(input);
            }
            in.close();
            connection.disconnect();

        } catch (IOException e) {
            logger.logException("Not connection to openweathermap.org:", e);
        }
        return content.toString();
    }

    public String infoCurrentWeather(Coordinates coordinates){
        String weatherJson = this.currentWeather(coordinates);
        String text = "JSON conversion failed: ";
        try{
            Gson gsonHttp = new Gson();
            Weather_Root weather = gsonHttp.fromJson(weatherJson, Weather_Root.class);
            text = weather.toString();
        }
        catch (Exception e){
            logger.logException("JSON conversion failed: ", e);
        }
        return text;
    }
}
