package musalasofttests.utility;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {
    private final static String CONFIG_FILE = "config.properties";

    private static Properties props;

    static {
        props = new Properties();
        InputStream input = PropertiesLoader.class.getClassLoader().getResourceAsStream(CONFIG_FILE);
        try {
            props.load(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getURL() {
        return props.getProperty("url");
    }

    public static String getBrowser() {
        return props.getProperty("browser");
    }
}
