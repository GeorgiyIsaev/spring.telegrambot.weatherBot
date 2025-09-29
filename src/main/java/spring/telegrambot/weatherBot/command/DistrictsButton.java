package spring.telegrambot.weatherBot.command;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import spring.telegrambot.weatherBot.data.district.DistrictEnum;

import java.util.ArrayList;
import java.util.List;

public class DistrictsButton implements Command {

     @Override
    public SendMessage runChat(String chatId) {
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();

        int count = 0;
        List<InlineKeyboardButton> lineButtons = new ArrayList<>();
        for (DistrictEnum districtEnum : DistrictEnum.values()) {
            if(count > 1) {
                buttons.add(lineButtons);
                lineButtons = new ArrayList<>();
                count =0;
            }
            lineButtons.add(InlineKeyboardButton.builder()
                    .text(districtEnum.getName() + " район")
                    .callbackData("button_index_" + districtEnum.getId())
                    .build());
            count++;
        }
        if(!lineButtons.isEmpty()){
            buttons.add(lineButtons);
        }

        return SendMessage.builder()
                .chatId(chatId)
                .text("Выберете район СПБ:")
                .replyMarkup(InlineKeyboardMarkup.builder().keyboard(buttons).build())
                .build();
    }
}
