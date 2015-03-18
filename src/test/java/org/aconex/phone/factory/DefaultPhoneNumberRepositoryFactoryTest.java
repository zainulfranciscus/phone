package org.aconex.phone.factory;

import org.aconex.phone.domain.DictionaryWord;
import org.aconex.phone.repository.PhoneNumberRepository;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Lenovo on 18/03/2015.
 */
public class DefaultPhoneNumberRepositoryFactoryTest {

    private static PhoneNumberRepository repository;

    @BeforeClass
    public static void setup(){
        repository = DefaultPhoneNumberRepositoryFactory.getInstance();

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
