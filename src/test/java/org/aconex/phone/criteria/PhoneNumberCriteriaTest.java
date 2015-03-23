package org.aconex.phone.criteria;

import org.aconex.phone.domain.DictionaryWord;
import org.aconex.phone.domain.PhoneNumber;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Created by Zainul Franciscus on 22/03/2015.
 */
public class PhoneNumberCriteriaTest {

    private Criteria phoneNumberCriteria;

    @Before
    public void setup() {

    }



    @Test
    public void shouldMatchWithADictionaryWithEqualPhoneNumber() {
        PhoneNumber phoneNumber = new PhoneNumber("12345");

        DictionaryWord dictionaryWord = new DictionaryWord();
        dictionaryWord.setPhoneNumber(phoneNumber);
        phoneNumberCriteria = new PhoneNumberCriteria(phoneNumber);

        assertTrue(phoneNumberCriteria.match(dictionaryWord));


    }

    @Test
    public void shouldReturnWordsWithMatchingPhoneNumbers(){

        PhoneNumber expectedPhoneNumber = new PhoneNumber("041111");

        DictionaryWord wordThatShouldBeMatched = new DictionaryWord();
        wordThatShouldBeMatched.setPhoneNumber(expectedPhoneNumber);

        DictionaryWord word2 = new DictionaryWord();
        word2.setPhoneNumber(new PhoneNumber("045555"));

        List<DictionaryWord> dictionaryWords = new ArrayList<>();
        dictionaryWords.add(wordThatShouldBeMatched);
        dictionaryWords.add(word2);

        phoneNumberCriteria = new PhoneNumberCriteria(expectedPhoneNumber);
        List<DictionaryWord> matchingList = phoneNumberCriteria.matchList(dictionaryWords);
        assertEquals(1,matchingList.size());
        assertEquals(wordThatShouldBeMatched,matchingList.get(0));


    }


}
