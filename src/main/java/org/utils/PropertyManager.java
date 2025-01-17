package org.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyManager {

    String filePath = "src/main/resources/SwagLabs.properties";

    public String readFromProperty(String propertyName){
        Properties prop = new Properties();
        try (FileInputStream input = new FileInputStream(filePath)) {
            prop.load(input);
            String propertyVlaue = prop.getProperty(propertyName);
            System.out.println("Read property: "+propertyName);
            return propertyVlaue;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return "";
    }

}
