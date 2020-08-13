package Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PathService {
    public String getPath() {
        FileInputStream fis = null;
        Properties props = new Properties();
        try {
            String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
            String appConfigPath = rootPath + "config.properties";
            fis = new FileInputStream(appConfigPath);
            props.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null)
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return props.getProperty("catalog");
    }
}
