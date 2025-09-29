package spring.telegrambot.weatherBot.logger;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import spring.telegrambot.weatherBot.data.request.Request;


@Component
@ConditionalOnProperty(value="loggerEnable" ,havingValue="onlyConsole",matchIfMissing = false)
public class ToConsole implements Logger {

    private final LofFormater lofFormater;

    public ToConsole(LofFormater lofFormater) {
        this.lofFormater = lofFormater;
    }

    @Override
    public void logMessage(Update update){
        System.out.println(lofFormater.logMessage(update));
    }

    @Override
    public void logCallbackQuery(Update update){
        System.out.println(lofFormater.logCallbackQuery(update));
    }

    @Override
    public void logRequest(Request request){
        System.out.println(lofFormater.logRequest(request));
    }

    @Override
    public void logException(String info, Exception e){
        System.out.println(lofFormater.logException(info, e));
    }

    @Override
    public void logResponse(String response) {
        System.out.println(lofFormater.logResponse(response));
    }
}
