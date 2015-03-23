package org.aconex.phone.domain;

import java.util.regex.Pattern;
import java.util.stream.Stream;

import static java.util.regex.Pattern.compile;
import static org.aconex.phone.domain.DictionaryWord.DASH;

/**
 * Created by Lenovo on 23/03/2015.
 */
public class PhoneNumber {

    private static final String REGEX_TO_MATCH_EMPTY_SPACES = "\\s+";
    private static final String REGEX_TO_FIND_2_OR_MORE_CONSECUTIVE_DIGITS = "\\d{2}";
    private static final String DOUBLE_DASH = "--";

    static final String[] PUNCTUATIONS = new String[]{".", "?", "!", ":", ";", "-", "(", ")", "[", "]", "\'", "\"", "/", ","};

    private String phoneNumber;

    public PhoneNumber(String number) {
        phoneNumber = number;
    }

    public PhoneNumber removeWhiteSpace() {
        return new PhoneNumber(phoneNumber.replaceAll(REGEX_TO_MATCH_EMPTY_SPACES, ""));
    }

    public PhoneNumber removePunctuations() {

        StringBuilder escapedPunctuationsForRegexMatching = new StringBuilder();

        Stream.of(PUNCTUATIONS).forEach(punctuation -> escapedPunctuationsForRegexMatching.append(Pattern.quote(punctuation)));

        return new PhoneNumber(phoneNumber.replaceAll("[" + escapedPunctuationsForRegexMatching.toString() + "]", ""));

    }

    public String replaceFirst(String numbers, String replacement) {
        return phoneNumber.replaceFirst(numbers, replacement);
    }

    public boolean has2ConsecutiveDigits() {
        return compile(REGEX_TO_FIND_2_OR_MORE_CONSECUTIVE_DIGITS).matcher(phoneNumber).find();
    }

    public String toString() {
        return phoneNumber;
    }


    public PhoneNumber removeDashAtTheStartOfAString() {
        if(String.valueOf(phoneNumber.charAt(0)).equals(DASH)){
            return new PhoneNumber(phoneNumber.replaceFirst(DASH, ""));
        }
        return this;
    }

    public PhoneNumber removeDashAtTheEndOfString() {

        if(phoneNumber.endsWith(DASH)) {
            return new PhoneNumber(phoneNumber.substring(0,phoneNumber.length() - 1 ));
        }

        return this;

    }

    public PhoneNumber replaceDoubleDashWithSingleDash() {
        return new PhoneNumber(phoneNumber.replace(DOUBLE_DASH, DASH));
    }

}
