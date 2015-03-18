package org.aconex.phone.domain;

import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * Created by Zainul Franciscus on 13/03/2015.
 */
public class DictionaryWord {

    public static final String[] PUNCTUATIONS = new String[] {".","?","!",":",";","-","(",")","[","]","\'","\"","/",","};

    protected static final String DASH = "-";


    private static final String REGEX_TO_MATCH_EMPTY_SPACES = "\\s+";
    private static final String DOUBLE_DASH = "--";
    private String phoneNumberRepresentation;
    private String word;


    public DictionaryWord(String w, String phoneNumber) {
        word = removeWhiteSpaces(w);
        phoneNumberRepresentation = phoneNumber;
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

    public String encodePhoneNumberWithLetters(String phoneNumber) {

        phoneNumber = removeWhiteSpaces(phoneNumber);
        String encodedPhoneNumber = phoneNumber.replaceFirst(this.getPhoneNumberRepresentation(), surroundWithDash(this.getWord()));
        encodedPhoneNumber = removeTrailingDashes(encodedPhoneNumber);

        return encodedPhoneNumber.toUpperCase();

    }


    public static String removePunctuationAndWhiteSpace(String phoneNumber) {

        phoneNumber = removePunctuationsFromPhoneNumber(phoneNumber);
        return removeWhiteSpaces(phoneNumber);

    }

    protected static String removePunctuationsFromPhoneNumber(String phoneNumber) {

        StringBuilder escapedPunctuationsForRegexMatching = new StringBuilder();

        Stream.of(PUNCTUATIONS).forEach(punctuation -> escapedPunctuationsForRegexMatching.append(Pattern.quote(punctuation)));

        return phoneNumber.replaceAll("[" + escapedPunctuationsForRegexMatching.toString()  + "]","");

    }

    protected static String removeWhiteSpaces(String str) {
        if(str.contains(" ")) {
            return str.replaceAll(REGEX_TO_MATCH_EMPTY_SPACES,"");
        }
        return str;
    }

    public static String removeTrailingDashes(String str){

        str = removeDashAtTheStartOfAString(str);
        str = removeDashAtTheEndOfString(str);
        return replaceDoubleDashWithSingleDash(str);

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
        return str.replace(DOUBLE_DASH, DASH);
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
