package spring.telegrambot.weatherBot.command;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class Help implements Command {
    @Override
    public SendMessage runChat(String chatId) {
        String text =       "/help (хелп, помощь) - отобразить инструкцию для пользователя\n" +
                "/districts (список) - Показать список районов\n" +
                "/districts_button (районы, район, кнопки) - вызвать кнопки с районам\n" +
                "\n" +
                "Вы также можете ввести в чат название райна, его индекс или первый три буквы " +
                "из названия района, что бы получить информацию погоды.";
        return SendMessage.builder().chatId(chatId).text(text).build();

    }
}
