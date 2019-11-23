/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import db.DBConstants;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author student1
 */
public class SettingsLoader {

    private static SettingsLoader instance;
    private Properties properties;

    private SettingsLoader() {
        try {
            properties = new Properties();
            properties.load(new FileInputStream("db.config"));
        } catch (Exception ex) {
            Logger.getLogger(SettingsLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static SettingsLoader getInstance() {
        if (instance == null) {
            instance = new SettingsLoader();
        }
        return instance;
    }

    public String getValue(String key) {
        return properties.getProperty(key);
    }

}
