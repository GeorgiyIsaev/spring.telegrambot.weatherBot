package spring.telegrambot.weatherBot.controler.weather;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import spring.telegrambot.weatherBot.data.district.Coordinates;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Component
public class OpenWeatherMapOrg {

    private final String keyOpenWeather; // Ключ доступа к сайту погоды

    public OpenWeatherMapOrg(
            @Value("${telegram.weather_token}")String keyOpenWeather) {
        this.keyOpenWeather = keyOpenWeather;
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
            System.out.println("ERROR: Неверно сформированная ссылка URL! " +
                    e.getMessage());
        }
        return url;
    }
    public String currentWeather(Coordinates coordinates){
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
            System.out.println("Exception connection to openweathermap.org: " +
                    e.getMessage());
        }
        return content.toString();
    }
}
