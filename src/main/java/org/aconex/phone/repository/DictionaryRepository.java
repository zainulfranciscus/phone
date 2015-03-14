package org.aconex.phone.repository;

import org.aconex.phone.reader.DictionaryReader;

import java.util.List;

/**
 * Created by Lenovo on 7/03/2015.
 */
public interface DictionaryRepository {

    void setDictionaryProvider(DictionaryReader dictionaryReader);

    DictionaryReader getDictionaryProvider();

    List<DictionaryWord> findWordThatMatchesPhoneNumber(String phoneNumber) throws Exception;

    void setPhoneNumberRepository(PhoneNumberRepository phoneNumberRepository);
}
