package JDBC;

import java.io.InputStream;
import java.util.Properties;

public class DBConfig {

    public static Properties load() {
        try {
            Properties props = new Properties();
            InputStream is = DBConfig.class
                    .getClassLoader()
                    .getResourceAsStream("application.properties");

            props.load(is);
            return props;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
