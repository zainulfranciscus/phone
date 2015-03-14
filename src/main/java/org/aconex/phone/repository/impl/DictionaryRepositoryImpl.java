package org.aconex.phone.repository.impl;

import org.aconex.phone.reader.DictionaryIterator;
import org.aconex.phone.reader.DictionaryReader;
import org.aconex.phone.repository.DictionaryRepository;
import org.aconex.phone.repository.DictionaryWord;
import org.aconex.phone.repository.PhoneNumberRepository;


import java.util.*;

/**
 * Created by Lenovo on 7/03/2015.
 */
public class DictionaryRepositoryImpl implements DictionaryRepository {


    private DictionaryReader provider;
    private PhoneNumberRepository phoneNumberRepository;


    @Override
    public List<DictionaryWord> findWordThatMatchesPhoneNumber(String phoneNumber) throws Exception {
        DictionaryIterator iterator = provider.iterator();

        List<DictionaryWord> matchingWords = new ArrayList<DictionaryWord>();
        String word;

        while( (word = iterator.next()) != null) {

            String number = phoneNumberRepository.convertWordToNumber(word);

            DictionaryWord dw = new DictionaryWord(word, number);
            if(dw.hasMatchWith(phoneNumber)) {
                matchingWords.add(dw);
            }

        }
        iterator.close();
        return matchingWords;
    }

    @Override
    public void setDictionaryProvider(DictionaryReader dictionaryReader) {
        provider = dictionaryReader;

    }

    @Override
    public DictionaryReader getDictionaryProvider() {
        return provider;
    }

    @Override
    public void setPhoneNumberRepository(PhoneNumberRepository repository) {
        phoneNumberRepository = repository;
    }
}
