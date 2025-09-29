package spring.telegrambot.weatherBot.logger;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import spring.telegrambot.weatherBot.data.request.Request;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@Component
public class ToConsole implements Logger {

    public String logMessage(Update update){
        String chatId = update.getMessage().getChatId().toString();
        String text = update.getMessage().getText();
        String updateID = update.getUpdateId().toString();
        String username = update.getMessage().getFrom().getUserName();
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd_HH:mm:ss").format(Calendar.getInstance().getTime());

        String result= "TIME: " + timeStamp +
                " MESSAGE: " + text +
                " chatId: " + chatId +
                " username:" + username +
                " updateID: " + updateID;
        System.out.println(result);
        return result;

    }
    public String logCallbackQuery(Update update){
        String chatId = update.getCallbackQuery().getMessage().getChatId().toString();
        String dataButton = update.getCallbackQuery().getData();

        String updateID = update.getUpdateId().toString();
        String username = update.getCallbackQuery().getFrom().getUserName();
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd_HH:mm:ss").format(Calendar.getInstance().getTime());

        String result= "TIME: " + timeStamp +
                " CallbackQuery: " + dataButton +
                " chatId: " + chatId +
                " username:" + username +
                " updateID: " + updateID;
        System.out.println(result);
        return result;
    }

    public String logRequest(Request request){
        System.out.println("Request: " + request);
        return "";
    }

    public String logException(String info, Exception e){
        String result = info + e.getMessage();
        System.out.println(result);
        return result;
    }

    @Override
    public String logResponse(String response) {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd_HH:mm:ss").format(Calendar.getInstance().getTime());
        String result = "Connect to telegram "+ timeStamp +": " + response;
        System.out.println(result);
        return result;
    }


}
