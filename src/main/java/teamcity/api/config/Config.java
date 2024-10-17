package teamcity.api.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private final static String CONFIG_PROPERTIES = "/Users/sezim/AquaProjects/demo/src/main/resources/config.properties";
    private static Config config;
    private Properties properties;

    private Config() {
        properties = new Properties();
        loadProperties(CONFIG_PROPERTIES);
    }



    private static Config getConfig() {
        if (config == null) {
            config = new Config();
        }
        return config;
    }

//    public void loadProperties(String fileName) {
//        try (InputStream stream = Config.class.getClassLoader().getResourceAsStream(fileName)) {
//            if (stream == null) {
//                System.err.println("File not found " + fileName);
//            }
//            properties.load(stream);
//        } catch (IOException e) {
//            System.err.println("Error during file reading " + fileName);
//            throw new RuntimeException(e);
//        }
//    }

    public void loadProperties(String fileName) {
        try (InputStream stream = Config.class.getClassLoader().getResourceAsStream(fileName)) {
            if (stream == null) {
                System.err.println("File not found " + fileName);
                return; // Прекратите выполнение метода, если файл не найден
            }
            properties.load(stream);
            System.out.println("Loaded properties from " + fileName); // Лог для проверки загрузки
        } catch (IOException e) {
            System.err.println("Error during file reading " + fileName);
            throw new RuntimeException(e);
        }
    }


//    public static String getProperty(String key) {
//        return getConfig().properties.getProperty(key);
//    }

    public static String getProperty(String key) {
        String value = getConfig().properties.getProperty(key);
        System.out.println("Property [" + key + "] = " + value); // Лог для проверки значений
        return value;
    }
}
