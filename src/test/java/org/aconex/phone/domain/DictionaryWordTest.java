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
    public static final String NUMBER_46 = "46";


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
        assertEquals(NUMBER_46, dictionary.getPhoneNumberRepresentation());
    }


    @Test
    public void twoWordsAreNotEqualIfTheyHaveDifferentPhoneNumbers() {
        DictionaryWord word = new DictionaryWord("54","IT");
        assertFalse(word.equals(dictionary));
    }

    @Test
    public void thisWordShouldHaveAMatchWithThePhoneNumber() {
        assertTrue(dictionary.hasMatchWith(NUMBER_46));
    }

    @Test
    public void shouldMatchWithGoEvenWithSpacesIn46() {
        assertTrue(dictionary.hasMatchWith(" 4 6 "));
    }


    @Test
    public void thisWordShouldNotHaveAMatchWithThePhoneNumber() {
        String phoneNumber = "3334444";
        assertFalse(dictionary.hasMatchWith(phoneNumber));
    }


    @Test
    public void fortySixShouldBeReplacedWithGoAndADash() {
        assertEquals("88" + DASH + THE_WORD_GO + DASH + "99", dictionary.encodePhoneNumberWithLetters("884699"));
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

    @Test
    public void shouldRemoveDashAtTheStartOfAString(){
        assertEquals("34-GO", dictionary.removeDashAtTheStartOfAString("-34-GO"));
    }
    @Test
    public void shouldRemoveDashAtTheEndOfAString(){
        assertEquals("22-GO", dictionary.removeDashAtTheEndOfString("22-GO-"));
    }
    @Test
    public void shouldOnlyHave1DashBetweenWords() {
        String expectedResult = "22-GO-88";
        assertEquals(expectedResult, dictionary.replaceDoubleDashWithSingleDash("22--GO--88"));
    }

}
