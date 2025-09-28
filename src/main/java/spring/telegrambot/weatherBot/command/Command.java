package spring.telegrambot.weatherBot.command;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface Command {

    SendMessage runSend(Update update);
    SendMessage runChat(String chatId);
    String run();
}
