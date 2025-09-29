package spring.telegrambot.weatherBot.command;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import spring.telegrambot.weatherBot.data.district.DistrictEnum;

public class DistrictsList implements Command {
    @Override
    public SendMessage runChat(String chatId) {
        StringBuilder text = new StringBuilder();
        text.append("Список доступных районов: \n");
        for(DistrictEnum districtEnum : DistrictEnum.values()){
            text.append(districtEnum.getId()).append(" ")
                    .append(districtEnum.getName()).append(" район\n");
        }
        return SendMessage.builder().chatId(chatId).text(text.toString()).build();
    }
}