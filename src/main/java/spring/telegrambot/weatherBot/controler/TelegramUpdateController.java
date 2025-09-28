package spring.telegrambot.weatherBot.controler;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import spring.telegrambot.weatherBot.client.SetWebhookRequest;
import spring.telegrambot.weatherBot.client.TelegramFeignClient;
import spring.telegrambot.weatherBot.command.Commands;


@RestController
@RequestMapping("/v1/api/telegram")
public class TelegramUpdateController {


    private final String urlServer;
    private final TelegramFeignClient telegramFeignClient;
    private final Commands commands;

    public TelegramUpdateController(
            TelegramFeignClient telegramFeignClient,
            Commands commands,
            @Value("${telegram.urlHostTunnel}") String urlServer){
        this.telegramFeignClient = telegramFeignClient;
        this.urlServer = urlServer;
        this.commands = commands;
    }


    @PostConstruct
    public void init(){
        SetWebhookRequest request = new SetWebhookRequest(urlServer);
        //SetWebhookRequest request = new SetWebhookRequest("");
        String response = telegramFeignClient.setWebhook(request);
        System.out.println("response: " + response);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handle(HttpMessageNotReadableException e) {
        System.out.println("{400 Bad Request from Telegram " + e.getMessage() + "}");
    }

    @PostMapping("/")
    public void postMethod(@RequestBody Update update) {
        System.out.println("Update print " + update);
        if(update.hasMessage()){
            System.out.println("TEXT");
            String chatId = update.getMessage().getChatId().toString();
            String text = commands.startCommand(update.getMessage().getText());

            SendMessage sendMessage = SendMessage.builder().chatId(chatId).text(text).build();
            String request = telegramFeignClient.sendMessage(sendMessage);
            System.out.println("request: " + request);
        }
        else{
            System.out.println("НЕ TEXT");
        }
    }
}