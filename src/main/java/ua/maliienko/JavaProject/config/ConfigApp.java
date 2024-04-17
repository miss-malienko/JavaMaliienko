package ua.maliienko.lab5.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/** Клас для роботи з конфігурацією додатку*/
public class ConfigApp {
    private static final Properties prop = new Properties();

    private static ConfigApp INSTANCE;

    /**Конструктор який зчитує посилааня на базу даних в файлі app.properties*/
    private ConfigApp() {
        try (InputStream in = new FileInputStream("app.properties")) {
            prop.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static ConfigApp getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ConfigApp();
        }
        return INSTANCE;
    }
    public String getProperty(String key) {
        return prop.getProperty(key);
    }
}
