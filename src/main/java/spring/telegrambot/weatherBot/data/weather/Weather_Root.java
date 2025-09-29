package spring.telegrambot.weatherBot.data.weather;

import spring.telegrambot.weatherBot.data.weather.parts.*;

import java.text.DecimalFormat;
import java.util.ArrayList;

public record Weather_Root(
        Coord coord,
        ArrayList<Weather> weather,
        String base,
        Main main,
        int visibility,
        Wind wind,
        Clouds clouds,
        int dt,
        Sys sys,
        int timezone,
        int id,
        String name,
        int cod) {

    @Override
    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat(".#");
        return  "Температура: " + decimalFormat.format(main().temp() -275.15) + " °С\n" +
                "Ощущается как: " + decimalFormat.format(main().feels_like() -275.15) + " °С\n" +
                "Влажность: " + main().humidity() + "%\n" +
                "Атмосферное давление: " + main().pressure() + " гПа\n" +

                "Тип погоды: " + weather().getFirst().main() + " (" +weather().getFirst().description() +")\n" +
                "Облачность: " + clouds().all() + "%\n" +
                "Дальность видимости: " + visibility() + " км\n" +
                "Скорость ветра: " + decimalFormat.format(wind().speed()) +" метр/сек";
    }
}

