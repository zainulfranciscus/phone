package org.aconex.phone.service;

import org.aconex.phone.repository.DictionaryRepository;
import org.aconex.phone.repository.DictionaryWord;
import org.aconex.phone.repository.PhoneNumberRepository;

import java.util.List;
import java.util.Set;
import java.util.SortedSet;

/**
 * Created by Lenovo on 3/03/2015.
 */
public interface PhoneNumberConverterService {

    void setDictionaryRepository(DictionaryRepository repository);

    SortedSet<String> encode(String phoneNumber) throws Exception;

    boolean has2ConsecutiveDigits(String str);


}
