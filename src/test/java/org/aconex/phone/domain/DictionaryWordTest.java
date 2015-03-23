package org.aconex.phone.domain;


import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.aconex.phone.domain.DictionaryWord.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by Zainul Franciscus on 13/03/2015.
 */
public class DictionaryWordTest {

    private DictionaryWord dictionary;

    public static final String THE_WORD_GO = "GO";
    public static final PhoneNumber NUMBER_46 = new PhoneNumber("46");


    @Before
    public void setup() {
        dictionary = new DictionaryWord(THE_WORD_GO, NUMBER_46);
    }

    @Test
    public void shouldReturnGo() {
        assertEquals(THE_WORD_GO, dictionary.getWord());
    }

    @Test
    public void shouldReturn46() {
        assertEquals(NUMBER_46, dictionary.getPhoneNumber());
    }

    @Test
    public void fortySixShouldBeReplacedWithGoAndADash() {
        assertEquals("88" + DASH + THE_WORD_GO + DASH + "99", dictionary.encodePhoneNumberWithLetters(new PhoneNumber("884699")));
    }

    @Test
    public void shouldAppendDashAtTheEndAndBeginningOfTheString(){
        assertEquals(DASH +THE_WORD_GO + DASH, dictionary.surroundWithDash("GO"));
    }
    @Test
    public void shouldAppendDashAtTheEndOfTheString(){
        assertEquals(THE_WORD_GO + DASH, dictionary.appendDash("GO"));
    }
    @Test
    public void shouldAppendDashAtTheBeginningOfTheString() {
        assertEquals(DASH + THE_WORD_GO, dictionary.prependDash("GO"));
    }



}
