package spring.telegrambot.weatherBot.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import spring.telegrambot.weatherBot.controler.weather.OpenWeatherMapOrg;
import spring.telegrambot.weatherBot.data.district.DistrictEnum;

import java.util.Map;
import java.util.TreeMap;

@Component
public class Commands {
    private final Map<String, Command> commands;

    public Commands(
            OpenWeatherMapOrg openWeatherMapOrg) {
        this.commands = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        generateCommand(openWeatherMapOrg);
    }

    private void generateCommand(OpenWeatherMapOrg openWeatherMapOrg) {
        for (DistrictEnum district : DistrictEnum.values()) {
            addCommand(district, openWeatherMapOrg);
        }
        addButtonCreateCommand();
        addListDistricts();
        addHelp();
    }

    public void addCommand(DistrictEnum districtEnum, OpenWeatherMapOrg openWeatherMapOrg) {
        DistrictWeatherInfo districtWeatherInfo = new DistrictWeatherInfo(districtEnum, openWeatherMapOrg);

        commands.put(districtEnum.getId() + "", districtWeatherInfo);
        commands.put(districtEnum.getName(), districtWeatherInfo);
        commands.put(districtEnum.getNamePrepositional(), districtWeatherInfo);
        if (districtEnum.getName().length() > 4) {
            String abbreviation = districtEnum.getName().substring(0, 3);
            commands.put(abbreviation, districtWeatherInfo);
        }
    }

    public void addButtonCreateCommand(){
        DistrictsButton districtsButton = new DistrictsButton();
        commands.put("районы", districtsButton);
        commands.put("район",districtsButton);
        commands.put("кнопки",districtsButton);
        commands.put("/districts_button",districtsButton);
    }

    public void addListDistricts(){
        DistrictsList districtsList = new DistrictsList();
        commands.put("список",districtsList);
        commands.put("/districts",districtsList);
    }

    public void addHelp(){
        Help help =new Help();
        commands.put("/help",help);
        commands.put("хелп",help);
        commands.put("помощь",help);
    }

    public Command getCommand(String command){
        return commands.get(command.toLowerCase());
    }

    public SendMessage startCommand(String command, String chatID){
        Command commandB = getCommand(command);
        if (commandB == null){
            String text = "Команда "+ command + " не найдена";
            return SendMessage.builder().chatId(chatID).text(text).build();
        }
        return commandB.runChat(chatID);
    }
}
