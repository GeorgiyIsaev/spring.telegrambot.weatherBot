package spring.telegrambot.weatherBot.logger;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import spring.telegrambot.weatherBot.data.request.Request;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@Component
public class LofFormater {

    private String logFormater(String type, String info){
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd_HH:mm:ss").format(Calendar.getInstance().getTime());
        return type.toUpperCase() + ": " + timeStamp + " {" + info + "}";
    }

    public String logMessage(Update update){
        String chatId = update.getMessage().getChatId().toString();
        String text = update.getMessage().getText();
        String updateID = update.getUpdateId().toString();
        String username = update.getMessage().getFrom().getUserName();

        String info= "text: " + text +
                "; chatId: " + chatId +
                "; username:" + username +
                "; updateID: " + updateID;
        return logFormater("Message", info);
    }

    public String logCallbackQuery(Update update){
        String chatId = update.getCallbackQuery().getMessage().getChatId().toString();
        String dataButton = update.getCallbackQuery().getData();

        String updateID = update.getUpdateId().toString();
        String username = update.getCallbackQuery().getFrom().getUserName();

        String info = "button: " + dataButton +
                "; chatId: " + chatId +
                "; username:" + username +
                "; updateID: " + updateID;
        return logFormater("CallbackQuery", info);
    }

    public String logRequest(Request request){
        return logFormater("Request", request.toString());
    }

    public String logException(String info, Exception e){
        return logFormater("Exception", info + e.getMessage());
    }

    public String logResponse(String response) {
        return logFormater("response", response);
    }
}
