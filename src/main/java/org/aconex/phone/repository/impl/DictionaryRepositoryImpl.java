package org.aconex.phone.repository.impl;

import org.aconex.phone.domain.DictionaryWord;
import org.aconex.phone.reader.DictionaryIterator;
import org.aconex.phone.reader.DictionaryReader;
import org.aconex.phone.repository.DictionaryRepository;
import org.aconex.phone.repository.PhoneNumberRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zainul Franciscus on 15/03/2015.
 */
public class DictionaryRepositoryImpl implements DictionaryRepository {


    private DictionaryReader dictionaryReader;
    private PhoneNumberRepository phoneNumberRepository;


    @Override
    public List<DictionaryWord> findWordThatMatchesPhoneNumber(String phoneNumber) throws Exception {
        DictionaryIterator iterator = dictionaryReader.iterator();

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
    public void setDictionaryReader(DictionaryReader dictionaryReader) {
        this.dictionaryReader = dictionaryReader;

    }

    @Override
    public DictionaryReader getDictionaryReader() {
        return dictionaryReader;
    }

    @Override
    public void setPhoneNumberRepository(PhoneNumberRepository repository) {
        phoneNumberRepository = repository;
    }
}
