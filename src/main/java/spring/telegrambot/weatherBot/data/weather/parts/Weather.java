package spring.telegrambot.weatherBot.data.weather.parts;

public record Weather(
        int id,
        String main,
        String description,
        String icon
){ }
