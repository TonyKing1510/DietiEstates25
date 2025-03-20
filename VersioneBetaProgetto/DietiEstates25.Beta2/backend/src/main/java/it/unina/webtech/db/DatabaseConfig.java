package it.unina.webtech.db;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseConfig {

    private DatabaseConfig(){}

    public static String getDbUrl() {
        try {
            InputStream inputStream = DatabaseConfig.class.getClassLoader().getResourceAsStream("config.properties");
            if (inputStream == null) {
                return "";
            }
            Properties properties = new Properties();
            properties.load(inputStream);
            return properties.getProperty("db.url");
        }catch (Exception e){
            return "";
        }
    }
}
