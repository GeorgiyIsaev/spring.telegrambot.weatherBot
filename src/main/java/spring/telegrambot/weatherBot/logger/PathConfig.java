package spring.telegrambot.weatherBot.logger;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.nio.file.Path;

@Configuration
public class PathConfig {
    private final String catalog;
    private final String file;

    public PathConfig(
            @Value("${logger.catalog}") String catalog,
            @Value("${logger.file}") String file) {
        this.catalog = catalog;
        this.file = file;
    }

    @Bean
    public Path pathLog(){
        return Path.of( catalog +  File.separator + file);
    }

}
