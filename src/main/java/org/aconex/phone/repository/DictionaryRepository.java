package org.aconex.phone.repository;

import org.aconex.phone.criteria.Criteria;
import org.aconex.phone.domain.DictionaryWord;
import org.aconex.phone.reader.Reader;

import java.util.List;

/**
 * Created by Zainul Franciscus on 14/03/2015.
 */
public interface DictionaryRepository {

    void setReader(Reader reader);

    Reader getReader();

    List<DictionaryWord> findWordThatMatchesPhoneNumber(Criteria criteria) throws Exception;

    void setPhoneNumberRepository(PhoneNumberRepository phoneNumberRepository);
}
