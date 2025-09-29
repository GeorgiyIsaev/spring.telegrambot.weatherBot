package spring.telegrambot.weatherBot.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import spring.telegrambot.weatherBot.data.request.Request;

@FeignClient(name = "telegram", url = "${telegram.urlToken}")
public interface TelegramFeignClient {

        @PostMapping("/setWebhook")
        String setWebhook(SetWebhookRequest request);


        @PostMapping("/sendMessage")
        Request sendMessage(SendMessage request);
}

