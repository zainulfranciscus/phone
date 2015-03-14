package org.aconex.phone.service.impl;

import org.aconex.phone.domain.DictionaryWord;
import org.aconex.phone.repository.DictionaryRepository;

import org.aconex.phone.service.PhoneNumberEncoderService;



import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.aconex.phone.domain.DictionaryWord.removePunctuationsFromPhoneNumber;
import static org.aconex.phone.domain.DictionaryWord.removeWhiteSpaces;

/**
 * Created by Lenovo on 3/03/2015.
 */
public class PhoneNumberEncoderServiceImpl implements PhoneNumberEncoderService {

    public static final String REGEX_TO_FIND_2_OR_MORE_CONSECUTIVE_DIGITS = "\\d{2}";
    private DictionaryRepository dictionaryRepository;

    @Override
    public boolean has2ConsecutiveDigits(String str) {
        return Pattern.compile(REGEX_TO_FIND_2_OR_MORE_CONSECUTIVE_DIGITS).matcher(str).find();

    }

    @Override
    public SortedSet<String> encode(String phoneNumber) throws Exception {

        if ( phoneNumber == null || phoneNumber.trim().length() == 0) {
            return new TreeSet<String>();
        }

        String phoneNumberWithoutPunctuations = removePunctuationsFromPhoneNumber(phoneNumber);
        List<DictionaryWord> matchingWords = dictionaryRepository.findWordThatMatchesPhoneNumber(phoneNumberWithoutPunctuations);

        SortedSet<String> encodedNumbers = encode(phoneNumberWithoutPunctuations, matchingWords);

        if (encodedNumbers.size() == 0) {
            encodedNumbers.add(phoneNumber);
        }

        return encodedNumbers;

    }



    private SortedSet<String> encode(String phoneNumber, List<DictionaryWord> matchingWords) throws Exception {

        SortedSet<String> encodedPhoneNumbers = new TreeSet<>();

        if (matchingWords.size() == 0 && !has2ConsecutiveDigits(phoneNumber)) {
            encodedPhoneNumbers.add(removeWhiteSpaces(phoneNumber));

            return encodedPhoneNumbers;
        }

        for (DictionaryWord word : matchingWords) {

            String encodedPhoneNumber = word.replaceFirst(phoneNumber);

            List<DictionaryWord> words =  matchingWords.stream().filter(matchingWord -> matchingWord.hasMatchWith(encodedPhoneNumber)).collect(Collectors.toList());

            Set<String> encodedNumber = encode(encodedPhoneNumber, words);

            encodedPhoneNumbers.addAll(encodedNumber);
        }

        return encodedPhoneNumbers;
    }


    @Override
    public void setDictionaryRepository(DictionaryRepository d) {
        this.dictionaryRepository = d;

    }

}
