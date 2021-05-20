package service;
import utils.Loggable;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;

public class LoggingService <T extends Loggable> {

    private String className;

    private FileWriter fileWriter;

    public LoggingService(Class<T> typeClass)
    {
        className = typeClass.getSimpleName();
    }

    public void logAction(String actionName, LocalDateTime timestamp)
    {
        try {
            fileWriter = new FileWriter("log/" + className + ".csv", true);
            fileWriter
                    .append(actionName)
                    .append(String.valueOf(','))
                    .append(timestamp.toString())
                    .append(String.valueOf('\n'));

            fileWriter.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

}
