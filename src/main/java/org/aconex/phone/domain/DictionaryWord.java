package org.aconex.phone.domain;

import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * Created by Zainul Franciscus on 13/03/2015.
 */
public class DictionaryWord {

    public static final String SEPARATOR_FOR_WORDS = "-";
    public static final String[] PUNCTUATIONS = new String[] {".","?","!",":",";","-","(",")","[","]","\'","\"","/",","};
    
    private static final String REGEX_TO_MATCH_EMPTY_SPACES = "\\s+";
    private static final String DOUBLE_DASH = "--";
    private String phoneNumberRepresentation;
    private String word;

    public DictionaryWord(){}

    public DictionaryWord(String w, String phoneNumber) {
        phoneNumberRepresentation = phoneNumber;
        word = w;
    }

    public String getPhoneNumberRepresentation() {
        return phoneNumberRepresentation;
    }

    public void setPhoneNumberRepresentation(String phone) {
        this.phoneNumberRepresentation = phone;
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


    public String replaceFirst(String phoneNumber) {

        phoneNumber = removeWhiteSpaces(phoneNumber);
        String str = phoneNumber.replaceFirst(this.getPhoneNumberRepresentation(), SEPARATOR_FOR_WORDS + this.getWord() + SEPARATOR_FOR_WORDS);


        if(str.startsWith(SEPARATOR_FOR_WORDS)) {
            str = str.replaceFirst(SEPARATOR_FOR_WORDS,"");
        }

        if(str.endsWith(SEPARATOR_FOR_WORDS)) {
            str = str.substring(0,str.length() - 1 );
        }

        if(str.contains(DOUBLE_DASH)){
            str = str.replace(DOUBLE_DASH,SEPARATOR_FOR_WORDS);
        }

        return str.toUpperCase();

    }

    public static String removePunctuationAndWhiteSpace(String phoneNumber) {

        phoneNumber = removePunctuationsFromPhoneNumber(phoneNumber);
        return removeWhiteSpaces(phoneNumber);

    }
    public static String removePunctuationsFromPhoneNumber(String phoneNumber) {

        StringBuilder escapedPunctuationsForRegexMatching = new StringBuilder();

        Stream.of(PUNCTUATIONS).forEach(punctuation -> escapedPunctuationsForRegexMatching.append(Pattern.quote(punctuation)));

        return phoneNumber.replaceAll("[" + escapedPunctuationsForRegexMatching.toString()  + "]","");
    }


}
