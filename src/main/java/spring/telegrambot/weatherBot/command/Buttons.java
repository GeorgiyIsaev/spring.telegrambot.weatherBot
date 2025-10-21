package spring.telegrambot.weatherBot.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import spring.telegrambot.weatherBot.controler.OpenWeatherMapController;
import spring.telegrambot.weatherBot.data.district.DistrictEnum;

import java.util.Map;
import java.util.TreeMap;

@Component
public class Buttons {
    private final Map<String, Command> commands;

    public Buttons(
            OpenWeatherMapController openWeatherMapController) {
        this.commands = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        generateCommand(openWeatherMapController);
    }

    private void generateCommand(OpenWeatherMapController openWeatherMapController) {
        for (DistrictEnum district : DistrictEnum.values()) {
            addCommand(district, openWeatherMapController);
        }
    }

    public void addCommand(DistrictEnum districtEnum, OpenWeatherMapController openWeatherMapController) {
        DistrictWeatherInfo districtWeatherInfo = new DistrictWeatherInfo(districtEnum, openWeatherMapController);
        commands.put("button_index_" + districtEnum.getId(), districtWeatherInfo);
    }

    public Command getCommand(String command){
        return commands.get(command.toLowerCase());
    }


    public SendMessage startCommand(String command, String chatID){
        Command commandB = getCommand(command);
        if (commandB == null){
            String text = "Кнопка с неизвестными данными ("+ command + ") не найдена";
            return SendMessage.builder().chatId(chatID).text(text).build();
        }
        return commandB.runChat(chatID);
    }
}
