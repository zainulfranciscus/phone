package org.aconex.phone.reader;

import org.aconex.phone.reader.impl.ClassLoaderReader;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * Created by Lenovo on 24/03/2015.
 */
public class PhoneConfigurationReader {

    private static PhoneConfigurationReader instance = null;

    public static PhoneConfigurationReader getInstance(String propertiesFileName) throws IOException {
        if (instance == null) {
            return new PhoneConfigurationReader(propertiesFileName);
        }

        return instance;
    }

    private Properties properties = new Properties();

    private PhoneConfigurationReader(String propertiesFileName) throws IOException {
        ClassLoaderReader reader = new ClassLoaderReader();
        reader.sourceOfData(propertiesFileName);
        properties.load(reader.getInputStream());
    }

    public String find(String letter) {
        if(letter == null){
            return null;
        }
        return properties.getProperty(letter.toUpperCase());
    }
}
