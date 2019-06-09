package com.vladislavk.testcrud.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * @author Vladislav Klochkov
 * @project testcrud
 * @date 2019-06-09
 */

public class PropertyUtil {
    private static Logger logger = LoggerFactory.getLogger(PropertyUtil.class);
    private Properties properties = new Properties();

    public PropertyUtil() {
        loadProp();
    }

    private void loadProp() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("application.properties");
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8")) {
            properties.load(inputStreamReader);
        } catch (IOException e) {
            logger.error("Error load application.properties: {}", e.getMessage());
        }
    }

    public String getProperty(String name) {
        return properties.getProperty(name);
    }
}
