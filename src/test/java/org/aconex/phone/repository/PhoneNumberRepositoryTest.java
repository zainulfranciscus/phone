package org.aconex.phone.repository;

import org.aconex.phone.domain.DictionaryWord;
import org.aconex.phone.domain.PhoneNumber;
import org.aconex.phone.factory.DefaultPhoneNumberRepositoryFactory;
import org.junit.BeforeClass;
import org.junit.Test;

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
        repository = DefaultPhoneNumberRepositoryFactory.getInstance();
        repository.associateLetterWithNumber(LETTER_G, NUMBER_4);
        repository.associateLetterWithNumber(LETTER_O, NUMBER_6);
    }

    @Test
    public void shouldReturn6ForLetterO() {
        assertEquals(NUMBER_6,repository.findNumber(LETTER_O).intValue());
    }

    @Test
    public void goShouldBeReplacedTo46(){
        assertEquals(NUMBER_46.toString(), repository.convertWordToNumber(THE_WORD_GO).toString());
    }

    @Test
    public void goWithSpacesShouldBeReplacedTo46(){
        assertEquals(NUMBER_46.toString(), repository.convertWordToNumber(" G O ").toString());
    }

    @Test
    public void shouldIgnorePunctuationsWhenEncodingNumbers(){
        assertEquals(NUMBER_46.toString(), repository.convertWordToNumber("G-O").toString());
    }

    @Test
    public void upperCaseAToZShouldBeConvertedToANumber() {

        for (char c = 'A'; c <= 'Z'; c++) {
            String letter = String.valueOf(c);
            PhoneNumber phoneNumber = repository.convertWordToNumber(letter);
            assertEquals(repository.findNumber(letter).toString(), phoneNumber.toString());
        }
    }

    @Test
    public void lowerCaseAToZShouldBeConvertedToANumber() {

        for (char c = 'a'; c <= 'z'; c++) {

            String letter = String.valueOf(c);

            PhoneNumber phoneNumber = repository.convertWordToNumber(letter);
            assertEquals(repository.findNumber(letter).toString(), phoneNumber.toString());
        }
    }

    @Test
    public void punctuationsShouldBeConvertedToEmptySpace() {
        for (String punctuation : DictionaryWord.PUNCTUATIONS) {
            assertEquals("", repository.convertWordToNumber(String.valueOf(punctuation)).toString());
        }
    }

}
