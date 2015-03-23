package org.aconex.phone.domain;

import org.junit.Test;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

/**
 * Created by Zainul Franciscus on 23/03/2015.
 */
public class PhoneNumberTest {

    private PhoneNumber phoneNumber;

    @Test
    public void whiteSpacesShouldBeRemoved() {
        phoneNumber = new PhoneNumber("04 56 22");
        String output = phoneNumber.removeWhiteSpace().toString();
        String expectedOutput = "045622";
        assertEquals(expectedOutput, output);

    }

    @Test
    public void punctuationsShouldBeRemoved() {

        String strWithPunctuation = "";
        String expectedOutputIsEmptySpace = "";

        Stream.of(PhoneNumber.PUNCTUATIONS).forEach(punctuation -> strWithPunctuation.concat(punctuation));
        phoneNumber = new PhoneNumber(strWithPunctuation);
        assertEquals(expectedOutputIsEmptySpace, phoneNumber.removePunctuations().toString());

    }

    @Test
    public void twelveShouldBeReplacedWithTO(){
        phoneNumber = new PhoneNumber("1234");
        assertEquals("TO34",phoneNumber.replaceFirst("12","TO"));
    }

    @Test
    public void shouldRemoveDashAtTheStartOfAString(){
        phoneNumber = new PhoneNumber("-34-GO");
        assertEquals("34-GO", phoneNumber.removeDashAtTheStartOfAString().toString());
    }
    @Test
    public void shouldRemoveDashAtTheEndOfAString(){
        phoneNumber = new PhoneNumber("22-GO-");
        assertEquals("22-GO", phoneNumber.removeDashAtTheEndOfString().toString());
    }
    @Test
    public void shouldOnlyHave1DashBetweenWords() {
        phoneNumber = new PhoneNumber("22--GO--88");
        assertEquals("22-GO-88", phoneNumber.replaceDoubleDashWithSingleDash().toString());
    }
}
