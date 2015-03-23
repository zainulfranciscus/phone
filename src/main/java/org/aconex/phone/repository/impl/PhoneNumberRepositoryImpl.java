package org.aconex.phone.repository.impl;


import org.aconex.phone.domain.PhoneNumber;
import org.aconex.phone.repository.PhoneNumberRepository;

import java.util.HashMap;
import java.util.Map;

/*/*
 * Created by Zainul Franciscus on 14/03/2015.
 */
public class PhoneNumberRepositoryImpl implements PhoneNumberRepository {

    private Map<String, Integer> mapOfLettersToPhoneNumber = new HashMap<String, Integer>();

    @Override
    public void associateLetterWithNumber(String letter, int phoneNumber) {
        mapOfLettersToPhoneNumber.put(letter.toUpperCase(), phoneNumber);

    }

    @Override
    public Integer findNumber(String word) {
        return mapOfLettersToPhoneNumber.get(word.toUpperCase());
    }

    @Override
    public PhoneNumber convertWordToNumber(String word) {

        StringBuilder builder = new StringBuilder();

        for (Character c : word.toCharArray()) {

            Integer number = findNumber(String.valueOf(c));

            if(number == null){
                continue;
            }

            builder.append(number.intValue());

        }

        return new PhoneNumber(builder.toString());
    }
}
