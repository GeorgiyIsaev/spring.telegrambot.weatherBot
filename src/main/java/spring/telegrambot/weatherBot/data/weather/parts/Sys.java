package spring.telegrambot.weatherBot.data.weather.parts;

public record Sys(
         int type,
         int id,
         String country,
         int sunrise,
         int sunset){
}
