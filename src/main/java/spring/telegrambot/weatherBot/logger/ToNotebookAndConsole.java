package spring.telegrambot.weatherBot.logger;


import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import spring.telegrambot.weatherBot.data.request.Request;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
@ConditionalOnProperty(value="loggerEnable" ,havingValue="NotebookAndConsole",matchIfMissing = false)
public class ToNotebookAndConsole implements Logger{

    private final LofFormater lofFormater;
    private final Path pathLog;

    public ToNotebookAndConsole(Path pathLog,
                                LofFormater lofFormater) {
        this.lofFormater = lofFormater;
        this.pathLog = pathLog;
    }

    @Override
    public void logMessage(Update update){
        String text = lofFormater.logMessage(update);
        this.write(text);
        System.out.println(text);
    }

    @Override
    public void logCallbackQuery(Update update){
        String text = lofFormater.logCallbackQuery(update);
        this.write(text);
        System.out.println(text);
    }

    @Override
    public void logRequest(Request request){
        String text = lofFormater.logRequest(request);
        this.write(text);
        System.out.println(text);
    }

    @Override
    public void logException(String info, Exception e){
        String text = lofFormater.logException(info, e);
        this.write(text);
        System.out.println(text);
    }

    @Override
    public void logResponse(String response) {
        String text = lofFormater.logResponse(response);
        this.write(text);
        System.out.println(text);
    }


    private void write(String textLog){
        //Запись текста в файл
        createDirectoriesIfNotExists(pathLog);
        try(FileWriter writer = new FileWriter(pathLog.toFile(), true))
        {
            writer.write(textLog);
            writer.write("\n");
            writer.flush(); //запись из буфера в файл
        }
        catch(IOException e){
            this.logException("Невозможно осуществить сохранение лога в файл", e);
        }
    }

    private void createDirectoriesIfNotExists(Path userFile) {
        Path directory = userFile.getParent();
        if(directory != null && !Files.isDirectory(directory)){
            try {
                Files.createDirectories(directory);
            } catch (IOException e) {
                this.logException("Ошибка при создании каталога", e);
            }
        }
    }
}
