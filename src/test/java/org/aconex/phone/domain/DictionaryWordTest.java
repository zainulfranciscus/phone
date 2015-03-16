package org.aconex.phone.domain;


import org.aconex.phone.domain.DictionaryWord;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Created by Zainul Franciscus on 13/03/2015.
 */
public class DictionaryWordTest {

    private DictionaryWord wordForGo;

    private static final String THE_WORD_GO = "THE_WORD_GO";
    private static final String NUMBER_46 = "46";


    @Before
    public void setup() {
        wordForGo = new DictionaryWord(THE_WORD_GO, NUMBER_46);
    }

    @Test
    public void shouldReturnGo() {
        assertEquals(THE_WORD_GO, wordForGo.getWord());
    }

    @Test
    public void shouldReturn46() {
        assertEquals(NUMBER_46, wordForGo.getPhoneNumberRepresentation());
    }



    @Test
    public void shouldBeReplacedWithGoEvenWhenThereIsSpacesIn46() {
        assertEquals(THE_WORD_GO, wordForGo.replaceFirst("4 6 "));

    }

    @Test
    public void twoWordsAreNotEqualIfTheyHaveDifferentPhoneNumbers() {
        DictionaryWord word = new DictionaryWord();
        word.setPhoneNumberRepresentation("54");
        assertFalse(word.equals(wordForGo));
    }

    @Test
    public void thisWordShouldHaveAMatchWithThePhoneNumber() {
        assertTrue(wordForGo.hasMatchWith(NUMBER_46));
    }

    @Test
    public void shouldMatchWithGoEvenWithSpacesIn46() {
        assertTrue(wordForGo.hasMatchWith(" 4 6 "));
    }


    @Test
    public void thisWordShouldNotHaveAMatchWithThePhoneNumber() {
        String phoneNumber = "3334444";
        assertFalse(wordForGo.hasMatchWith(phoneNumber));
    }


    @Test
    public void fortySixShouldBeReplacedWithGoAndADash() {
        assertEquals(THE_WORD_GO + DictionaryWord.SEPARATOR_FOR_WORDS + "99", wordForGo.replaceFirst("4699"));
    }

    @Test
    public void shouldHaveADashBeforeGo() {
        assertEquals("99-" + THE_WORD_GO, wordForGo.replaceFirst("9946"));
    }

    @Test
    public void shouldOnlyHave1DashBetweenWords() {
        assertEquals("AIR-" + THE_WORD_GO + "-88", wordForGo.replaceFirst("AIR-46-88"));
    }



}
