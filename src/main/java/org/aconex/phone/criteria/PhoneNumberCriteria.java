package org.aconex.phone.criteria;

import org.aconex.phone.domain.DictionaryWord;
import org.aconex.phone.domain.PhoneNumber;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Lenovo on 22/03/2015.
 */
public class PhoneNumberCriteria implements Criteria{

    private PhoneNumber phoneNumber;

    public PhoneNumberCriteria(PhoneNumber number){
        phoneNumber = number;
    }

    @Override
    public boolean match(DictionaryWord dictionaryWord) {
        return phoneNumber.removePunctuations().removeWhiteSpace().toString().contains(dictionaryWord.getPhoneNumber().toString());
    }


    @Override
    public List<DictionaryWord> matchList(List<DictionaryWord> dictionaryWords) {
        List<DictionaryWord> words =  dictionaryWords.stream().filter(matchingWord -> match(matchingWord)).collect(Collectors.toList());
        return words;
    }
}
