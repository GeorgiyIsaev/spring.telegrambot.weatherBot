package spring.telegrambot.weatherBot.logger;

import org.telegram.telegrambots.meta.api.objects.Update;
import spring.telegrambot.weatherBot.data.request.Request;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public interface Logger  {
    public String logMessage(Update update);

    public String logCallbackQuery(Update update);



    public String logRequest(Request request);
    public String logException(String info, Exception e);
    public String logResponse(String response);
}
