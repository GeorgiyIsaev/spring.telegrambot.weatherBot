package spring.telegrambot.weatherBot.command.district;

import com.google.gson.Gson;
import spring.telegrambot.weatherBot.command.Command;
import spring.telegrambot.weatherBot.data.district.DistrictEnum;
import spring.telegrambot.weatherBot.controler.weather.OpenWeatherMapOrg;
import spring.telegrambot.weatherBot.data.weather.Weather_Root;

public class Admiralteiskii implements Command {

    private final DistrictEnum districtEnum;
    private final  OpenWeatherMapOrg openWeatherMapOrg;

    public Admiralteiskii(DistrictEnum districtEnum, OpenWeatherMapOrg openWeatherMapOrg) {
        this.districtEnum = districtEnum;
        this.openWeatherMapOrg = openWeatherMapOrg;
    }

    @Override
    public String run() {
        String weatherJson = openWeatherMapOrg.currentWeather(districtEnum.getCoordinates());
        Gson gsonHttp = new Gson();
        Weather_Root weather = gsonHttp.fromJson(weatherJson, Weather_Root.class);
        return "Погода " + districtEnum.getNamePrepositional() + " районе:\n" + weather;
    }
}
