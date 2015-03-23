package org.aconex.phone.repository;

import org.aconex.phone.domain.PhoneNumber;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zainul Franciscus on 15/03/2015.
 */
public interface PhoneNumberRepository {

    void associateLetterWithNumber(String letter, int phoneNumber);

    Integer findNumber(String word);

    PhoneNumber convertWordToNumber(String word);
}
