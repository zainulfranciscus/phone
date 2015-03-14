package org.aconex.phone.repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lenovo on 6/03/2015.
 */
public interface PhoneNumberRepository {

    void associateLetterForNumber(String letter, int phoneNumber);

    Integer findNumber(String word);

    String convertWordToNumber(String word);
}
