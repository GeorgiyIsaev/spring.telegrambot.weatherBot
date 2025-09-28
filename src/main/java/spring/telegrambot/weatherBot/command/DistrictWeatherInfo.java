package spring.telegrambot.weatherBot.command;

import com.google.gson.Gson;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import spring.telegrambot.weatherBot.data.district.DistrictEnum;
import spring.telegrambot.weatherBot.controler.weather.OpenWeatherMapOrg;
import spring.telegrambot.weatherBot.data.weather.Weather_Root;

public class DistrictWeatherInfo implements Command {

    private final DistrictEnum districtEnum;
    private final  OpenWeatherMapOrg openWeatherMapOrg;

    public DistrictWeatherInfo(DistrictEnum districtEnum, OpenWeatherMapOrg openWeatherMapOrg) {
        this.districtEnum = districtEnum;
        this.openWeatherMapOrg = openWeatherMapOrg;
    }

    @Override
    public SendMessage runSend(Update update) {
        return null;
    }

    @Override
    public SendMessage runChat(String chatId) {
        String weatherJson = openWeatherMapOrg.currentWeather(districtEnum.getCoordinates());
        Gson gsonHttp = new Gson();
        Weather_Root weather = gsonHttp.fromJson(weatherJson, Weather_Root.class);
        String text = "Погода " + districtEnum.getNamePrepositional() + " районе:\n" + weather;
        return SendMessage.builder().chatId(chatId).text(text).build();
    }

    @Override
    public String run() {
        String weatherJson = openWeatherMapOrg.currentWeather(districtEnum.getCoordinates());
        Gson gsonHttp = new Gson();
        Weather_Root weather = gsonHttp.fromJson(weatherJson, Weather_Root.class);
        return "Погода " + districtEnum.getNamePrepositional() + " районе:\n" + weather;
    }
}
