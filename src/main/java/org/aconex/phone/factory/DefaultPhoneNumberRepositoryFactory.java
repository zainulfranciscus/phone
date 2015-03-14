package org.aconex.phone.factory;

import org.aconex.phone.repository.PhoneNumberRepository;
import org.aconex.phone.repository.impl.PhoneNumberRepositoryImpl;

/**
 * Created by Lenovo on 7/03/2015.
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

        repository.associateLetterForNumber("A",PHONE_NUMBER_TWO);
        repository.associateLetterForNumber("B",PHONE_NUMBER_TWO);
        repository.associateLetterForNumber("C",PHONE_NUMBER_TWO);

        repository.associateLetterForNumber("D",PHONE_NUMBER_THREE);
        repository.associateLetterForNumber("E",PHONE_NUMBER_THREE);
        repository.associateLetterForNumber("F",PHONE_NUMBER_THREE);

        repository.associateLetterForNumber("G",PHONE_NUMBER_FOUR);
        repository.associateLetterForNumber("H",PHONE_NUMBER_FOUR);
        repository.associateLetterForNumber("I",PHONE_NUMBER_FOUR);

        repository.associateLetterForNumber("J",PHONE_NUMBER_FIVE);
        repository.associateLetterForNumber("K",PHONE_NUMBER_FIVE);
        repository.associateLetterForNumber("L",PHONE_NUMBER_FIVE);

        repository.associateLetterForNumber("M",PHONE_NUMBER_SIX);
        repository.associateLetterForNumber("N",PHONE_NUMBER_SIX);
        repository.associateLetterForNumber("O",PHONE_NUMBER_SIX);

        repository.associateLetterForNumber("P",PHONE_NUMBER_SEVEN);
        repository.associateLetterForNumber("Q",PHONE_NUMBER_SEVEN);
        repository.associateLetterForNumber("R",PHONE_NUMBER_SEVEN);
        repository.associateLetterForNumber("S",PHONE_NUMBER_SEVEN);

        repository.associateLetterForNumber("T",PHONE_NUMBER_EIGHT);
        repository.associateLetterForNumber("U",PHONE_NUMBER_EIGHT);
        repository.associateLetterForNumber("V",PHONE_NUMBER_EIGHT);


        repository.associateLetterForNumber("W",PHONE_NUMBER_NINE);
        repository.associateLetterForNumber("X",PHONE_NUMBER_NINE);
        repository.associateLetterForNumber("Y",PHONE_NUMBER_NINE);
        repository.associateLetterForNumber("Z",PHONE_NUMBER_NINE);


        return repository;

    }
}
