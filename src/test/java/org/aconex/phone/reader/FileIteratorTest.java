package org.aconex.phone.reader;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;

import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Zainul Franciscus on 14/03/2015.
 */
public class FileIteratorTest {

    private FileIterator dictionaryIterator;
    private BufferedReader mockBufferedReader;

    @Before
    public void setup() {
        dictionaryIterator = new FileIterator();
        mockBufferedReader = mock(BufferedReader.class);
        dictionaryIterator.setReader(mockBufferedReader);
    }

    @Test
    public void closeShouldNotThrowException() {

        boolean exceptionRaised = false;
        try {
            dictionaryIterator.close();
        } catch (Exception e) {
            e.printStackTrace();
            exceptionRaised = true;
        }

        assertFalse(exceptionRaised);

    }

    @Test
    public void nextShouldReturnAString() throws Exception {

        String expectedString = "word";
        when(mockBufferedReader.readLine()).thenReturn(expectedString);

        assertEquals(expectedString, dictionaryIterator.next());
    }


}
