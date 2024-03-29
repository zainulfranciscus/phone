import org.aconex.phone.reader.Reader;
import org.aconex.phone.reader.UserInputReader;
import org.aconex.phone.reader.impl.ClassLoaderReader;
import org.aconex.phone.reader.impl.FileReader;
import org.aconex.phone.repository.DictionaryRepository;
import org.aconex.phone.repository.impl.DictionaryRepositoryImpl;
import org.aconex.phone.repository.impl.PhoneNumberRepositoryImpl;
import org.aconex.phone.service.PhoneNumberEncoderService;
import org.aconex.phone.service.impl.PhoneNumberEncoderServiceImpl;

import java.util.List;
import java.util.SortedSet;

/**
 * Created by Zainul Franciscus on 16/03/2015.
 */
public class Main {


    public static void main(String[] args) throws Exception {
        System.out.println("Enter a phone number followed by a -d to specify a file that contains words. E.g: 0411112222 -d C:\\dictionary.txt");

        String userInput = System.console().readLine();
        UserInputReader userInputReader = new UserInputReader(userInput);

        List<String> phoneNumbers =  userInputReader.phoneNumbers();
        if(phoneNumbers.size() == 0) {
            System.out.println("No phone numbers specified. Program will exit");
            return;
        }

        String dictionary = userInputReader.dictionary();

        Main main = new Main();

        PhoneNumberEncoderService phoneNumberEncoderService = new PhoneNumberEncoderServiceImpl();
        phoneNumberEncoderService.setDictionaryRepository(main.getDictionaryRepository(dictionary));

        for(String phoneNumber: phoneNumbers) {
            SortedSet<String> encodedPhoneNumbers = phoneNumberEncoderService.encode(phoneNumber);
            System.out.println(phoneNumber.trim() + " can be encoded to : ");
            encodedPhoneNumbers.forEach(encodedPhoneNumber -> System.out.println(encodedPhoneNumber));
        }

    }

    public Reader dictionaryReader(String dictionaryFile) {

        if (dictionaryFile != null) {
            FileReader fileDictionaryProvider = new FileReader();
            fileDictionaryProvider.sourceOfData(dictionaryFile);

            if (fileDictionaryProvider.fileExist()) {
                return fileDictionaryProvider;
            }
        }

        ClassLoaderReader classLoaderDictionaryProvider = new ClassLoaderReader();
        classLoaderDictionaryProvider.sourceOfData("words.txt");

        return classLoaderDictionaryProvider;
    }

    public DictionaryRepository getDictionaryRepository(String dictionaryFile) {

        DictionaryRepository dictionaryRepository = new DictionaryRepositoryImpl();
        dictionaryRepository.setReader(dictionaryReader(dictionaryFile));
        dictionaryRepository.setPhoneNumberRepository(new PhoneNumberRepositoryImpl());

        return dictionaryRepository;
    }

}
