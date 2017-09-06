package uk.tommyt.zkatomic;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import org.apache.log4j.Logger;

class Config {
    private final Logger log = Logger.getLogger(Main.class.getName());
    private final Properties props = new Properties();

    Config(String propFile) throws java.io.IOException {
        InputStream input = new FileInputStream(propFile);
        props.load(input);
    }

    String get(String key) {
        String retVal = props.getProperty(key);
        if (retVal == null) {
            log.warn(String.format("Config property %s was not found, returning null", key));
        }
        return retVal;
    }
}
