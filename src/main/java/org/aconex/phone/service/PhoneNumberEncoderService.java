package org.aconex.phone.service;

import org.aconex.phone.repository.DictionaryRepository;

import java.util.SortedSet;

/**
 * Created by Zainul Franciscus on 14/03/2015.
 */
public interface PhoneNumberEncoderService {

    void setDictionaryRepository(DictionaryRepository repository);

    SortedSet<String> encode(String phoneNumber) throws Exception;

}
