package org.aconex.phone.repository;

import org.aconex.phone.domain.DictionaryWord;
import org.aconex.phone.factory.DefaultPhoneNumberRepositoryFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Zainul Franciscus on 14/03/2015.
 */
public class PhoneNumberRepositoryTest {

    private PhoneNumberRepository repository;
    private int phoneNumber = 2;
    private static final int NUMBER_4 = 4;
    private static final int NUMBER_6 = 6;
    private static final String LETTER_G = "G";
    private static final String LETTER_O = "O";
    private static final String WORD_GO = LETTER_G + LETTER_O;
    private static final String NUMBER_46 = NUMBER_4 + "" + NUMBER_6;

    @Before
    public void setup(){
        repository = DefaultPhoneNumberRepositoryFactory.getInstance();
        repository.associateLetterForNumber(LETTER_G, NUMBER_4);
        repository.associateLetterForNumber(LETTER_O, NUMBER_6);
    }

    @Test
    public void shouldReturn6ForLetterO() {

        assertEquals(NUMBER_6,repository.findNumber(LETTER_O).intValue());
    }

    @Test
    public void goShouldBeReplacedTo46(){
        assertEquals(NUMBER_46, repository.convertWordToNumber(WORD_GO));
    }

    @Test
    public void goWithSpacesShouldBeReplacedTo46(){
        assertEquals(NUMBER_46, repository.convertWordToNumber(" G O "));
    }

    @Test
    public void shouldIgnorePunctuationsWhenEncodingNumbers(){

        assertEquals(NUMBER_46, repository.convertWordToNumber("G-O"));

    }

    @Test
    public void upperCaseAToZShouldBeConvertedToANumber() {

        for (char c = 'A'; c <= 'Z'; c++) {
            String letter = String.valueOf(c);
            String phoneNumber = repository.convertWordToNumber(letter);
            assertEquals(repository.findNumber(letter).toString(), phoneNumber);
        }
    }

    @Test
    public void lowerCaseAToZShouldBeConvertedToANumber() {

        for (char c = 'a'; c <= 'z'; c++) {

            String letter = String.valueOf(c);

            String phoneNumber = repository.convertWordToNumber(letter);
            assertEquals(repository.findNumber(letter).toString(), phoneNumber);
        }
    }

    @Test
    public void punctuationsShouldBeConvertedToEmptySpace() {
        for (String punctuation : DictionaryWord.PUNCTUATIONS) {
            assertEquals("", repository.convertWordToNumber(String.valueOf(punctuation)));
        }
    }

}
