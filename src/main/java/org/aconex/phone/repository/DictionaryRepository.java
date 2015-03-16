package org.aconex.phone.repository;

import org.aconex.phone.domain.DictionaryWord;
import org.aconex.phone.reader.DictionaryReader;

import java.util.List;

/**
 * Created by Zainul Franciscus on 14/03/2015.
 */
public interface DictionaryRepository {

    void setDictionaryProvider(DictionaryReader dictionaryReader);

    DictionaryReader getDictionaryProvider();

    List<DictionaryWord> findWordThatMatchesPhoneNumber(String phoneNumber) throws Exception;

    void setPhoneNumberRepository(PhoneNumberRepository phoneNumberRepository);
}
