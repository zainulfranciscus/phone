package org.aconex.phone.repository;

import org.aconex.phone.domain.DictionaryWord;
import org.aconex.phone.repository.impl.PhoneNumberRepositoryImpl;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static org.aconex.phone.domain.DictionaryWordTest.NUMBER_46;
import static org.aconex.phone.domain.DictionaryWordTest.THE_WORD_GO;
import static org.junit.Assert.assertEquals;

/**
 * Created by Zainul Franciscus on 14/03/2015.
 */
public class PhoneNumberRepositoryTest {

    private static PhoneNumberRepository repository;

    private static final int NUMBER_4 = 4;
    private static final int NUMBER_6 = 6;
    private static final String LETTER_G = "G";
    private static final String LETTER_O = "O";

    @BeforeClass
    public static void setup(){
        repository = new PhoneNumberRepositoryImpl();
    }


    @Test
    public void goShouldBeReplacedTo46() throws IOException {
        assertEquals(NUMBER_46.toString(), repository.convertWordToNumber(THE_WORD_GO).toString());
    }

    @Test
    public void goWithSpacesShouldBeReplacedTo46() throws IOException {
        assertEquals(NUMBER_46.toString(), repository.convertWordToNumber(" G O ").toString());
    }

    @Test
    public void shouldIgnorePunctuationsWhenEncodingNumbers() throws IOException {
        assertEquals(NUMBER_46.toString(), repository.convertWordToNumber("G-O").toString());
    }

    @Test
    public void punctuationsShouldBeConvertedToEmptySpace() throws IOException {
        for (String punctuation : DictionaryWord.PUNCTUATIONS) {
            assertEquals("", repository.convertWordToNumber(String.valueOf(punctuation)).toString());
        }
    }

}
