package org.aconex.phone.repository.impl;


import org.aconex.phone.repository.DictionaryWord;
import org.aconex.phone.repository.PhoneNumberRepository;

import java.util.HashMap;
import java.util.Map;

import static org.aconex.phone.repository.DictionaryWord.removePunctuationsFromPhoneNumber;
import static org.aconex.phone.repository.DictionaryWord.removeWhiteSpaces;

/**
 * Created by Lenovo on 6/03/2015.
 */
public class PhoneNumberRepositoryImpl implements PhoneNumberRepository {


    private Map<String, Integer> mapOfLettersToPhoneNumber = new HashMap<String, Integer>();

    @Override
    public void associateLetterForNumber(String letter, int phoneNumber) {
        mapOfLettersToPhoneNumber.put(letter.toUpperCase(), phoneNumber);

    }

    @Override
    public Integer findNumber(String word) {
        return mapOfLettersToPhoneNumber.get(word.toUpperCase());
    }

    @Override
    public String convertWordToNumber(String word) {

        word = removePunctuationsFromPhoneNumber(word);
        word = removeWhiteSpaces(word);

        StringBuilder builder = new StringBuilder();

        for (Character c : word.toCharArray()) {

            builder.append(findNumber(String.valueOf(c)));
        }

        return builder.toString();
    }
}
