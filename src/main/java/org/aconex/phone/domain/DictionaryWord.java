package org.aconex.phone.domain;

/**
 * Created by Zainul Franciscus on 13/03/2015.
 */
public class DictionaryWord {

    public static final String[] PUNCTUATIONS = new String[] {".","?","!",":",";","-","(",")","[","]","\'","\"","/",","};
    static final String DASH = "-";
    private PhoneNumber phoneNumber;
    private String word;


    public DictionaryWord(){

    }
    public DictionaryWord(String w, PhoneNumber number) {
        word = w;
        phoneNumber = number;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public String getWord() {
        return word;
    }

    public String encodePhoneNumberWithLetters(PhoneNumber phoneNumber) {

        phoneNumber = phoneNumber.removeWhiteSpace();
        String encodedPhoneNumber = phoneNumber.replaceFirst(this.getPhoneNumber().toString(), surroundWithDash(this.getWord()));
        return encodedPhoneNumber.toUpperCase();

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

    public void setPhoneNumber(PhoneNumber number) {
        this.phoneNumber = number;
    }
}
