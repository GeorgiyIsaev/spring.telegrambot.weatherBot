package spring.telegrambot.weatherBot.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import spring.telegrambot.weatherBot.command.district.Admiralteiskii;
import spring.telegrambot.weatherBot.controler.weather.OpenWeatherMapOrg;
import spring.telegrambot.weatherBot.data.district.DistrictEnum;

import java.util.LinkedHashMap;
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
        addCommand(DistrictEnum.ADMIRALTEISKII, openWeatherMapOrg);
        addCommand(DistrictEnum.FRUNZENSKII, openWeatherMapOrg);
    }

    public void addCommand(DistrictEnum districtEnum, OpenWeatherMapOrg openWeatherMapOrg){
        Admiralteiskii admiralteiskii = new Admiralteiskii(DistrictEnum.ADMIRALTEISKII, openWeatherMapOrg);

        commands.put(districtEnum.getId() + "", admiralteiskii);
        commands.put(districtEnum.getName(), admiralteiskii);
        commands.put(districtEnum.getNamePrepositional(), admiralteiskii);
    }

    public Command getCommand(String command){
        return commands.get(command.toLowerCase());
    }

    public String startCommand(String command){
        Command commandB = getCommand(command);
        if (commandB == null){
            return "Команда "+ command + " не найдена";
        }
        return commandB.run();
    }
}
