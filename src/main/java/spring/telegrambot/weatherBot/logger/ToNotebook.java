package spring.telegrambot.weatherBot.logger;


import org.telegram.telegrambots.meta.api.objects.Update;
import spring.telegrambot.weatherBot.data.request.Request;

public class ToNotebook implements Logger{


    @Override
    public void logMessage(Update update) {

    }

    @Override
    public void logCallbackQuery(Update update) {

    }

    @Override
    public void logRequest(Request request) {

    }

    @Override
    public void logException(String info, Exception e) {

    }

    @Override
    public void logResponse(String response) {

    }
}
