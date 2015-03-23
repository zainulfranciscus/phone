package org.aconex.phone.repository.impl;

import org.aconex.phone.criteria.Criteria;
import org.aconex.phone.domain.DictionaryWord;
import org.aconex.phone.domain.PhoneNumber;
import org.aconex.phone.reader.iterator.AbstractIterator;
import org.aconex.phone.reader.Reader;
import org.aconex.phone.repository.DictionaryRepository;
import org.aconex.phone.repository.PhoneNumberRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zainul Franciscus on 15/03/2015.
 */
public class DictionaryRepositoryImpl implements DictionaryRepository {

    private Reader reader;
    private PhoneNumberRepository phoneNumberRepository;


    @Override
    public List<DictionaryWord> findWordThatMatchesPhoneNumber(Criteria criteria) throws Exception {
        AbstractIterator iterator = reader.iterator();

        List<DictionaryWord> matchingWords = new ArrayList<DictionaryWord>();
        String word;

        while( (word = iterator.next()) != null) {

            PhoneNumber number = phoneNumberRepository.convertWordToNumber(word);
            DictionaryWord dw = new DictionaryWord(word,number);

            if(criteria.match(dw)) {
                matchingWords.add(dw);
            }

        }
        iterator.close();
        return matchingWords;
    }

    @Override
    public void setReader(Reader reader) {
        this.reader = reader;

    }

    @Override
    public Reader getReader() {
        return reader;
    }

    @Override
    public void setPhoneNumberRepository(PhoneNumberRepository repository) {
        phoneNumberRepository = repository;
    }
}
