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
    public SendMessage runChat(String chatId) {
        String weatherJson = openWeatherMapOrg.currentWeather(districtEnum.getCoordinates());
        String text = "DistrictWeatherInfo";
        try{
            Gson gsonHttp = new Gson();
            Weather_Root weather = gsonHttp.fromJson(weatherJson, Weather_Root.class);
            text = "Погода " + districtEnum.getNamePrepositional() + " районе:\n" + weather;
        }
        catch (Exception ex){
                System.out.println("Exception in DistrictWeatherInfo.class " + ex.getMessage());
        }

        return SendMessage.builder().chatId(chatId).text(text).build();
    }
}
