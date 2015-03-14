package org.aconex.phone.repository;

import org.aconex.phone.datasource.DictionaryProvider;
import org.aconex.phone.datasource.impl.ClassLoaderDictionaryProvider;

import java.io.File;
import java.util.Collection;
import java.util.List;

/**
 * Created by Lenovo on 7/03/2015.
 */
public interface DictionaryRepository {

    void setDictionaryProvider(DictionaryProvider dictionaryProvider);

    DictionaryProvider getDictionaryProvider();

    List<DictionaryWord> findWordThatMatchesPhoneNumber(String phoneNumber) throws Exception;

    void setPhoneNumberRepository(PhoneNumberRepository phoneNumberRepository);
}
