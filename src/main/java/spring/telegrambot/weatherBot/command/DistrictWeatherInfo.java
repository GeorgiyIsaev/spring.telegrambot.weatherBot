package spring.telegrambot.weatherBot.command;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import spring.telegrambot.weatherBot.data.district.DistrictEnum;
import spring.telegrambot.weatherBot.controler.OpenWeatherMapController;

public class DistrictWeatherInfo implements Command {

    private final DistrictEnum districtEnum;
    private final OpenWeatherMapController openWeatherMapController;

    public DistrictWeatherInfo(DistrictEnum districtEnum, OpenWeatherMapController openWeatherMapController) {
        this.districtEnum = districtEnum;
        this.openWeatherMapController = openWeatherMapController;
    }

    @Override
    public SendMessage runChat(String chatId) {
        String weather = openWeatherMapController.infoCurrentWeather(districtEnum.getCoordinates());
        String text = "DistrictWeatherInfo";
        text = "Погода " + districtEnum.getNamePrepositional() + " районе:\n" + weather;
        return SendMessage.builder().chatId(chatId).text(text).build();
    }
}
