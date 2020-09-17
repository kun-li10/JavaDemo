package com.jd.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigUtil {

    public ConfigUtil() {
    }

    private static Properties props = new Properties();

    static {
        try {
//            props.load(ConfigUtil.class.getClassLoader().getResourceAsStream("application.properties")); 相同得效果
            props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getValue(String key) {
        return props.getProperty(key);
    }

    public static void updateProperties(String key, String value) {
        props.setProperty(key, value);
    }
}
