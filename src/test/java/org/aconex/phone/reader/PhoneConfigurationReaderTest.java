package org.aconex.phone.reader;

import org.aconex.phone.reader.impl.ClassLoaderReader;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Properties;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by Zainul Franciscus on 24/03/2015.
 */
public class PhoneConfigurationReaderTest {

    private PhoneConfigurationReader phoneConfigurationReader;
    private Properties properties = new Properties();

    @Before
    public void setup() throws IOException {
        ClassLoaderReader classLoaderReader = new ClassLoaderReader();
        classLoaderReader.sourceOfData("PhoneConfiguration.properties");
        phoneConfigurationReader = PhoneConfigurationReader.getInstance("PhoneConfiguration.properties");
        properties.load(classLoaderReader.getInputStream());
    }

    @Test
    public void shouldReturnANumberForAToZ() {

        for(char c = 'A'; c <= 'Z'; c++){
            assertEquals(properties.getProperty(String.valueOf(c)), phoneConfigurationReader.find(String.valueOf(c)));
        }

    }


    @Test
    public void shouldReturnANumberForaToz() {

        for(char c = 'a'; c <= 'z'; c++){
            assertEquals(properties.getProperty(String.valueOf(c).toUpperCase()),phoneConfigurationReader.find(String.valueOf(c)));
        }

    }

    @Test
    public void shouldReturnNullWhenGivenNullString(){
        assertNull(phoneConfigurationReader.find(null));
    }

}
