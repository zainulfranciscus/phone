import org.aconex.phone.factory.DefaultPhoneNumberRepositoryFactory;
import org.aconex.phone.reader.DictionaryReader;
import org.aconex.phone.reader.impl.ClassLoaderDictionaryReader;
import org.aconex.phone.reader.impl.FileDictionaryReader;
import org.aconex.phone.repository.DictionaryRepository;
import org.aconex.phone.repository.impl.DictionaryRepositoryImpl;
import org.aconex.phone.service.PhoneNumberEncoderService;
import org.aconex.phone.service.impl.PhoneNumberEncoderServiceImpl;

import java.util.SortedSet;

/**
 * Created by Zainul Franciscus on 16/03/2015.
 */
public class Main {

    public static final String SWITCH_FOR_DICTIONARY = "-d";

    public static void main(String[] args) throws Exception {
        System.out.println("Enter a phone number followed by a -d to specify a file that contains words. E.g: 0411112222 -d C:\\dictionary.txt");

        Main main = new Main();
        String userInput = System.console().readLine();

        String phoneNumber = main.phoneNumberFromConsoleInput(userInput);
        String dictionary = main.dictionaryFileFromConsoleInput(userInput);

        PhoneNumberEncoderService phoneNumberEncoderService = new PhoneNumberEncoderServiceImpl();
        phoneNumberEncoderService.setDictionaryRepository(main.getDictionaryRepository(dictionary));

        SortedSet<String> encodedPhoneNumbers = phoneNumberEncoderService.encode(phoneNumber);

        encodedPhoneNumbers.forEach(encodedPhoneNumber -> System.out.println(encodedPhoneNumber));


    }

    public String dictionaryFileFromConsoleInput(String commandLineArgs) {

        if (commandLineArgs == null) {
            return null;
        }

        int indexWhereDSwitchIs = indexOfDSwitch(commandLineArgs);

        if (indexWhereDSwitchIs == -1) {
            return null;
        }

        int indexWhereDictionaryNameStart = indexWhereDSwitchIs + 2;
        return commandLineArgs.substring(indexWhereDictionaryNameStart, commandLineArgs.length()).trim();

    }

    public String phoneNumberFromConsoleInput(String commandLineArgs) {

        if (commandLineArgs == null) {
            return null;
        }

        int indexOfDSwitch = indexOfDSwitch(commandLineArgs);

        if (indexOfDSwitch == -1) {
            return commandLineArgs.trim();
        }
        return commandLineArgs.substring(0, indexOfDSwitch).trim();
    }


    private int indexOfDSwitch(String commandLineArgs) {
        return commandLineArgs.indexOf(SWITCH_FOR_DICTIONARY);
    }

    public DictionaryReader dictionaryProvider(String dictionaryFile) {

        if (dictionaryFile != null) {
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

    public DictionaryRepository getDictionaryRepository(String dictionaryFile) {

        DictionaryRepository dictionaryRepository = new DictionaryRepositoryImpl();
        dictionaryRepository.setDictionaryProvider(dictionaryProvider(dictionaryFile));
        dictionaryRepository.setPhoneNumberRepository(DefaultPhoneNumberRepositoryFactory.getInstance());

        return dictionaryRepository;
    }
}
