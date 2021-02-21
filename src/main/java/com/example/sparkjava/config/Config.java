/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sparkjava.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author Rojan Maharjan<rojan@moco.com.np>
 */
public class Config {

    private static final String CONFIG_FILE = "config.properties";
    private static Properties prop;

    /**
     * load app server configuration file. This method is called by other public
     * methods to load the configuration.
     */
    public void loadConfiguration() {
        try (InputStream input = Config.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            // load a properties file
            prop = new Properties();
            prop.load(input);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void setConfig(Properties props) {
        this.prop = props;
    }

    /**
     * Get configuration value of a specific key in app configuration
     *
     * @param key
     * @return value
     */
    public String getProperty(String key) {
        if (prop == null || prop.isEmpty()) {
            loadConfiguration();
        }

        return prop.getProperty(key);
    }

}
