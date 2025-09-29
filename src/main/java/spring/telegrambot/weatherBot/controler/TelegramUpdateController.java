package spring.telegrambot.weatherBot.controler;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import spring.telegrambot.weatherBot.client.SetWebhookRequest;
import spring.telegrambot.weatherBot.client.TelegramFeignClient;
import spring.telegrambot.weatherBot.command.Buttons;
import spring.telegrambot.weatherBot.command.Commands;
import spring.telegrambot.weatherBot.data.request.Request;
import spring.telegrambot.weatherBot.logger.Logger;


@RestController
@RequestMapping("/v1/api/telegram")
public class TelegramUpdateController {

    private final String urlServer;
    private final TelegramFeignClient telegramFeignClient;
    private final Commands commands;
    private final Buttons buttons;
    private final Logger logger;

    public TelegramUpdateController(
            TelegramFeignClient telegramFeignClient,
            Commands commands,
            Buttons buttons,
            Logger logger,
            @Value("${telegram.urlHostTunnel}") String urlServer){
        this.telegramFeignClient = telegramFeignClient;
        this.urlServer = urlServer;
        this.commands = commands;
        this.buttons = buttons;
        this.logger = logger;
    }


    @PostConstruct
    public void init(){
        SetWebhookRequest request = new SetWebhookRequest(urlServer);
        //SetWebhookRequest request = new SetWebhookRequest("");
        String response = telegramFeignClient.setWebhook(request);
        logger.logResponse(response);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handle(HttpMessageNotReadableException e) {
        logger.logException("400 Bad Request from Telegram : " , e);

    }

    @PostMapping("/")
    public void postMethod(@RequestBody Update update) {
        if(update.hasMessage()){
            logger.logMessage(update);
            String chatId = update.getMessage().getChatId().toString();
            String command = update.getMessage().getText();
            SendMessage sendMessage = commands.startCommand(command, chatId);
            Request request = telegramFeignClient.sendMessage(sendMessage);
            logger.logRequest(request);
        }
        else if(update.hasCallbackQuery()){
            logger.logCallbackQuery(update);
            String chatId = update.getCallbackQuery().getMessage().getChatId().toString();
            String dataButton = update.getCallbackQuery().getData();
            System.out.println("КНОПКА: " + dataButton);
            SendMessage sendMessage = buttons.startCommand(dataButton,chatId);
            Request request = telegramFeignClient.sendMessage(sendMessage);
            logger.logRequest(request);
        }
    }
}