package org.aconex.phone.factory;

import org.aconex.phone.repository.PhoneNumberRepository;
import org.aconex.phone.repository.impl.PhoneNumberRepositoryImpl;

/**
 * Created by Zainul Franciscus on 13/03/2015.
 */
public class DefaultPhoneNumberRepositoryFactory {

    static final int PHONE_NUMBER_TWO = 2;
    static final int PHONE_NUMBER_THREE = 3;
    static final int PHONE_NUMBER_FOUR = 4;
    static final int PHONE_NUMBER_FIVE = 5;
    static final int PHONE_NUMBER_SIX = 6;
    static final int PHONE_NUMBER_SEVEN = 7;
    static final int PHONE_NUMBER_EIGHT = 8;
    static final int PHONE_NUMBER_NINE = 9;

    private static PhoneNumberRepository repository = new PhoneNumberRepositoryImpl();

    private DefaultPhoneNumberRepositoryFactory(){}

    public static PhoneNumberRepository getInstance(){

        repository.associateLetterWithNumber("A", PHONE_NUMBER_TWO);
        repository.associateLetterWithNumber("B", PHONE_NUMBER_TWO);
        repository.associateLetterWithNumber("C", PHONE_NUMBER_TWO);

        repository.associateLetterWithNumber("D", PHONE_NUMBER_THREE);
        repository.associateLetterWithNumber("E", PHONE_NUMBER_THREE);
        repository.associateLetterWithNumber("F", PHONE_NUMBER_THREE);

        repository.associateLetterWithNumber("G", PHONE_NUMBER_FOUR);
        repository.associateLetterWithNumber("H", PHONE_NUMBER_FOUR);
        repository.associateLetterWithNumber("I", PHONE_NUMBER_FOUR);

        repository.associateLetterWithNumber("J", PHONE_NUMBER_FIVE);
        repository.associateLetterWithNumber("K", PHONE_NUMBER_FIVE);
        repository.associateLetterWithNumber("L", PHONE_NUMBER_FIVE);

        repository.associateLetterWithNumber("M", PHONE_NUMBER_SIX);
        repository.associateLetterWithNumber("N", PHONE_NUMBER_SIX);
        repository.associateLetterWithNumber("O", PHONE_NUMBER_SIX);

        repository.associateLetterWithNumber("P", PHONE_NUMBER_SEVEN);
        repository.associateLetterWithNumber("Q", PHONE_NUMBER_SEVEN);
        repository.associateLetterWithNumber("R", PHONE_NUMBER_SEVEN);
        repository.associateLetterWithNumber("S", PHONE_NUMBER_SEVEN);

        repository.associateLetterWithNumber("T", PHONE_NUMBER_EIGHT);
        repository.associateLetterWithNumber("U", PHONE_NUMBER_EIGHT);
        repository.associateLetterWithNumber("V", PHONE_NUMBER_EIGHT);


        repository.associateLetterWithNumber("W", PHONE_NUMBER_NINE);
        repository.associateLetterWithNumber("X", PHONE_NUMBER_NINE);
        repository.associateLetterWithNumber("Y", PHONE_NUMBER_NINE);
        repository.associateLetterWithNumber("Z", PHONE_NUMBER_NINE);


        return repository;

    }
}
