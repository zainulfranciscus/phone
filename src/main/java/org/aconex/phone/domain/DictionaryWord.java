package org.aconex.phone.domain;

import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * Created by Zainul Franciscus on 13/03/2015.
 */
public class DictionaryWord {

    public static final String DASH = "-";
    public static final String[] PUNCTUATIONS = new String[] {".","?","!",":",";","-","(",")","[","]","\'","\"","/",","};

    private static final String REGEX_TO_MATCH_EMPTY_SPACES = "\\s+";
    private static final String DOUBLE_DASH = "--";
    private String phoneNumberRepresentation;
    private String word;

    public static String removePunctuationAndWhiteSpace(String phoneNumber) {

        phoneNumber = removePunctuationsFromPhoneNumber(phoneNumber);
        return removeWhiteSpaces(phoneNumber);

    }
    public static String removePunctuationsFromPhoneNumber(String phoneNumber) {

        StringBuilder escapedPunctuationsForRegexMatching = new StringBuilder();

        Stream.of(PUNCTUATIONS).forEach(punctuation -> escapedPunctuationsForRegexMatching.append(Pattern.quote(punctuation)));

        return phoneNumber.replaceAll("[" + escapedPunctuationsForRegexMatching.toString()  + "]","");
    }

    public DictionaryWord(String w, String phoneNumber) {
        phoneNumberRepresentation = phoneNumber;
        word = w;
    }

    public String getPhoneNumberRepresentation() {
        return phoneNumberRepresentation;
    }

    public String getWord() {
        return word;
    }


    public boolean hasMatchWith(String phoneNumber) {
        phoneNumber = removeWhiteSpaces(phoneNumber);
        return phoneNumber.contains(this.getPhoneNumberRepresentation());

    }

    public static String removeWhiteSpaces(String str) {
        return str.replaceAll(REGEX_TO_MATCH_EMPTY_SPACES,"");
    }


    public String replaceNumbersWithLetters(String phoneNumber) {

        phoneNumber = removeWhiteSpaces(phoneNumber);

        String encodedPhoneNumber = phoneNumber.replaceFirst(this.getPhoneNumberRepresentation(), surroundWithDash(this.getWord()));
        encodedPhoneNumber = removeDashAtTheEndOfString(encodedPhoneNumber);
        encodedPhoneNumber = removeDashAtTheStartOfAString(encodedPhoneNumber);
        encodedPhoneNumber = replaceDoubleDashWithSingleDash(encodedPhoneNumber);

        return encodedPhoneNumber.toUpperCase();

    }

    protected static String removeDashAtTheStartOfAString(String str){
        if(str.startsWith(DASH)) {
            str = str.replaceFirst(DASH,"");
        }
        return str;
    }

    protected static String removeDashAtTheEndOfString(String str){

        if(str.endsWith(DASH)) {
            str = str.substring(0,str.length() - 1 );
        }

        return str;
    }

    protected static String replaceDoubleDashWithSingleDash(String str){
        if(str.contains(DOUBLE_DASH)){
            str = str.replace(DOUBLE_DASH, DASH);
        }

        return str;
    }

    protected String surroundWithDash(String str) {
        str = appendDash(str);
        str = prependDash(str);
        return str;
    }

    protected String appendDash(String str) {
        return str + DASH;
    }

    protected String prependDash(String str) {
        return DASH + str;
    }

}
