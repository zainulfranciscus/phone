package org.aconex.phone;

import org.aconex.phone.reader.DictionaryReader;
import org.aconex.phone.reader.impl.ClassLoaderDictionaryReader;
import org.aconex.phone.reader.impl.FileDictionaryReader;
import org.aconex.phone.factory.DefaultPhoneNumberRepositoryFactory;
import org.aconex.phone.repository.DictionaryRepository;
import org.aconex.phone.repository.impl.DictionaryRepositoryImpl;
import org.aconex.phone.service.PhoneNumberEncoderService;
import org.aconex.phone.service.impl.PhoneNumberEncoderServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.SortedSet;

/**
 * Created by Lenovo on 10/03/2015.
 */
public class Main {


    public static final String CMD_SWITCH_FOR_DICTIONARY = "-d";

    public static void main(String[] args) throws Exception {
        System.out.println("Enter a phone number followed by a -d to specify a file that contains words. E.g: 0411112222 -d C:\\dictionary.txt");

        Main main = new Main();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String userInput = main.readUserInput(br);

        String phoneNumber = main.phoneNumberFromCommandLineArgs(userInput);
        String dictionary = main.nameOfDictionaryFromCommandLineArgs(userInput);

        PhoneNumberEncoderService phoneNumberEncoderService = new PhoneNumberEncoderServiceImpl();
        phoneNumberEncoderService.setDictionaryRepository(main.getDictionaryRepository(dictionary));
        SortedSet<String> encodedPhoneNumbers = phoneNumberEncoderService.encode(phoneNumber);

        for(String encodedPhoneNumber: encodedPhoneNumbers){
            System.out.println(encodedPhoneNumber);
        }

    }

    public String nameOfDictionaryFromCommandLineArgs(String commandLineArgs) {

        if(commandLineArgs == null) {
            return null;
        }

        int indexWhereDSwitchIs = indexOfDSwitch(commandLineArgs);

        if(indexWhereDSwitchIs == -1){
            return null;
        }

        int indexWhereDictionaryNameStart = indexWhereDSwitchIs + 2;
        return commandLineArgs.substring(indexWhereDictionaryNameStart,commandLineArgs.length()).trim();

    }

    public String phoneNumberFromCommandLineArgs(String commandLineArgs) {

        if(commandLineArgs == null) {
            return null;
        }

        int indexOfDSwitch = indexOfDSwitch(commandLineArgs);

        if(indexOfDSwitch == -1){
            return commandLineArgs.trim();
        }
        return commandLineArgs.substring(0,indexOfDSwitch).trim();
    }



    private int indexOfDSwitch(String commandLineArgs){
        return commandLineArgs.indexOf(CMD_SWITCH_FOR_DICTIONARY);
    }

    public DictionaryReader dictionaryProvider(String dictionaryFile) {

        if(dictionaryFile != null) {
            FileDictionaryReader fileDictionaryProvider = new FileDictionaryReader();
            fileDictionaryProvider.sourceOfData(dictionaryFile);

            if (fileDictionaryProvider.fileExist()) {
                return fileDictionaryProvider;
            }
        }

        ClassLoaderDictionaryReader classLoaderDictionaryProvider = new ClassLoaderDictionaryReader();
        classLoaderDictionaryProvider.sourceOfData("words.txt");

        return classLoaderDictionaryProvider;
    }

    public String readUserInput(BufferedReader reader) throws IOException {
        return reader.readLine();
    }

    public DictionaryRepository getDictionaryRepository(String dictionaryFile) {

        DictionaryRepository dictionaryRepository = new DictionaryRepositoryImpl();
        dictionaryRepository.setDictionaryProvider(dictionaryProvider(dictionaryFile));
        dictionaryRepository.setPhoneNumberRepository(DefaultPhoneNumberRepositoryFactory.getInstance());

        return dictionaryRepository;
    }
}
