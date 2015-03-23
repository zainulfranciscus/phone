package org.aconex.phone.service.impl;

import org.aconex.phone.criteria.Criteria;
import org.aconex.phone.criteria.PhoneNumberCriteria;
import org.aconex.phone.domain.DictionaryWord;
import org.aconex.phone.domain.PhoneNumber;
import org.aconex.phone.repository.DictionaryRepository;

import org.aconex.phone.service.PhoneNumberEncoderService;


import java.util.*;
import java.util.stream.Collectors;

import static java.util.regex.Pattern.*;
import static org.aconex.phone.domain.DictionaryWord.*;

/**
 * Created by Zainul Franciscus on 14/03/2015.
 */
public class PhoneNumberEncoderServiceImpl implements PhoneNumberEncoderService {


    private DictionaryRepository dictionaryRepository;


    @Override
    public SortedSet<String> encode(String phoneNumber) throws Exception {

        if (phoneNumber == null || phoneNumber.trim().length() == 0) {
            return new TreeSet<String>();
        }

        PhoneNumber number = new PhoneNumber(phoneNumber);
        Criteria phoneNumberCriteria = new PhoneNumberCriteria(number);
        List<DictionaryWord> matchingWords = dictionaryRepository.findWordThatMatchesPhoneNumber(phoneNumberCriteria);

        SortedSet<String> encodedNumbers = encode(number.removePunctuations().removeWhiteSpace(), matchingWords);

        if (encodedNumbers.size() == 0) {
            encodedNumbers.add(phoneNumber);
        }

        return encodedNumbers;

    }

    private SortedSet<String> encode(PhoneNumber phoneNumber, List<DictionaryWord> matchingWords) throws Exception {

        SortedSet<String> encodedPhoneNumbers = new TreeSet<>();

        if (matchingWords.size() == 0 && !phoneNumber.has2ConsecutiveDigits()) {

            encodedPhoneNumbers.add(phoneNumber.removeWhiteSpace().removeDashAtTheEndOfString().removeDashAtTheStartOfAString().replaceDoubleDashWithSingleDash().toString());

            return encodedPhoneNumbers;
        }

        for (DictionaryWord word : matchingWords) {


            String encodedPhoneNumber = word.encodePhoneNumberWithLetters(phoneNumber);

            Criteria phoneNumberCriteria = new PhoneNumberCriteria(new PhoneNumber(encodedPhoneNumber));
            List<DictionaryWord> words = phoneNumberCriteria.matchList(matchingWords);

            Set<String> encodedNumber = encode(new PhoneNumber(encodedPhoneNumber), words);

            encodedPhoneNumbers.addAll(encodedNumber);
        }

        return encodedPhoneNumbers;
    }


    @Override
    public void setDictionaryRepository(DictionaryRepository d) {
        this.dictionaryRepository = d;

    }

}
