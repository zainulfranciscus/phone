package org.aconex.phone.reader;

import org.aconex.phone.reader.impl.FileReader;
import org.aconex.phone.reader.iterator.AbstractIterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.aconex.phone.reader.UserInputReader.Switch.DICTIONARY;
import static org.aconex.phone.reader.UserInputReader.Switch.PHONE;

/**
 * Created by Zainul Franciscus on 18/03/2015.
 */
public class UserInputReader {

    public static final String SEPARATOR_FOR_PHONE_INPUT = ",";

    enum Switch {

        DICTIONARY("-d"),
        PHONE("-p");

        String commandLineSwitch;

        Switch(String str) {
            commandLineSwitch = str;
        }

        String getValue(){
            return commandLineSwitch;
        }

    }

    private String userInput;

    public UserInputReader(String input){
        userInput = input;
    }

    public List<String> phoneNumbers()throws Exception {

        List<String> phoneNumbers = new ArrayList<>();

        if (isEmpty(userInput)) {
            return phoneNumbers;
        }

        int indexOfDictionarySwitch = indexOfSwitch(userInput, DICTIONARY);
        int indexOfPhoneSwitch = indexOfSwitch(userInput, PHONE);

        int start =  (indexOfPhoneSwitch > -1) ? 2: 0;
        int end = (indexOfDictionarySwitch > -1) ? indexOfDictionarySwitch: userInput.length();

        phoneNumbers = Arrays.asList(userInput.substring(start, end).trim().split(SEPARATOR_FOR_PHONE_INPUT));

        if(indexOfPhoneSwitch > -1) {
            phoneNumbers = readPhoneNumberFiles(phoneNumbers);
        }

        return phoneNumbers;
    }

    private List<String> readPhoneNumberFiles(List<String> phoneNumberFileNames) throws Exception {

        List<String> phoneNumbers = new ArrayList<>();

        for(String fileName: phoneNumberFileNames) {


            FileReader reader = new FileReader();
            reader.sourceOfData(fileName);

            if (!reader.fileExist()) {
                return phoneNumbers;

            }

            String phoneNumber;
            AbstractIterator iterator = reader.iterator();

            while ((phoneNumber = iterator.next()) != null) {
                phoneNumbers.add(phoneNumber);
            }


        }

        return phoneNumbers;

    }

    public String dictionaryFileFromConsoleInput() {

        if (isEmpty(userInput)) {
            return null;
        }

        int indexOfSwitchForDictionary = indexOfSwitch(userInput, DICTIONARY);

        if (indexOfSwitchForDictionary == -1) {
            return null;
        }

        int indexWhereDictionaryNameStart = indexOfSwitchForDictionary + 2;
        return userInput.substring(indexWhereDictionaryNameStart, userInput.length()).trim();

    }

    protected int indexOfSwitch(String commandLineArgs, Switch commandLineSwitch) {
        return commandLineArgs.indexOf(commandLineSwitch.getValue());
    }


    protected boolean isEmpty(String str){
        return str == null || str.trim().length() == 0;

    }

}
