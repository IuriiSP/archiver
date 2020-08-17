package Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropCache {
    private final Properties prop = new Properties();

    private PropCache() {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("config.properties");
        try {
            prop.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static class LazyHolder {
        private static final PropCache INSTANCE = new PropCache();
    }

    public static PropCache getInstance() {
        return LazyHolder.INSTANCE;
    }

    public String getProperty(String key) {
        return prop.getProperty(key);
    }
}

