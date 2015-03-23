package org.aconex.phone.reader;

import org.aconex.phone.reader.impl.ClassLoaderReader;
import org.aconex.phone.reader.iterator.AbstractIterator;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by Zainul Franciscus on 18/03/2015.
 */
public class UserInputReaderTest {

    private static final String EXPECTED_DICTIONARY_NAME = "dictionary.txt";
    private static final String FILE_THAT_CONTAINS_PHONE_NUMBERS = "phone.txt";
    private static final String EMPTY_SPACE = " ";
    private static final String EXPECTED_PHONE_NUMBER = "041112222";

    private String switchForPhone;
    private String switchForDictionary;

    private UserInputReader reader;

    @Before
    public void setup(){
        switchForDictionary = UserInputReader.Switch.DICTIONARY_SWITCH.getValue();
        switchForPhone = UserInputReader.Switch.PHONE_SWITCH.getValue();
    }

    @Test
    public void shouldReturnNameOfDictionary() {
        reader = new UserInputReader(switchForDictionary+ EXPECTED_DICTIONARY_NAME);
        String nameOfDictionary = reader.dictionary();
        assertEquals(EXPECTED_DICTIONARY_NAME, nameOfDictionary);
    }

    @Test
    public void shouldReturnNameOfDictionaryWithoutSpaces() {
        reader = new UserInputReader(switchForDictionary + EMPTY_SPACE + EXPECTED_DICTIONARY_NAME);
        String nameOfDictionary = reader.dictionary();
        assertEquals(EXPECTED_DICTIONARY_NAME, nameOfDictionary);
    }

    @Test
    public void shouldReturnNullWhenNoDictionaryNameIsProvided() {

        reader = new UserInputReader("0455551111");
        String nameOfDictionary = reader.dictionary();
        assertNull(nameOfDictionary);

    }

    @Test
    public void shouldReturnNullWhenCommandLineArgsIsNull(){
        reader = new UserInputReader(null);
        String nameOfDictionary = reader.dictionary();
        assertNull(nameOfDictionary);
    }


    @Test
    public void shouldReturnPhoneNumber() throws Exception {
        reader = new UserInputReader(EXPECTED_PHONE_NUMBER);

        List<String> phoneNumbers = reader.phoneNumbers();
        assertEquals(1,phoneNumbers.size());
        assertEquals(EXPECTED_PHONE_NUMBER, phoneNumbers.get(0));
    }

    @Test
    public void shouldReturnNullWhenPhoneNumberIsNull() throws Exception {

        reader = new UserInputReader(null);

        List<String> phoneNumbers = reader.phoneNumbers();
        assertEquals(0,phoneNumbers.size());
    }

    @Test
    public void shouldReturnEmptyStringWhenNumberIsNotSpecified() throws Exception {

        reader = new UserInputReader(UserInputReaderTest.EMPTY_SPACE);
        List<String> phoneNumbers = reader.phoneNumbers();
        assertEquals(0, phoneNumbers.size());
    }

    @Test
    public void shouldReturnPhoneNumberWhenThereIsADSwitch() throws Exception {
        reader = new UserInputReader(EXPECTED_PHONE_NUMBER + UserInputReaderTest.EMPTY_SPACE + switchForDictionary + EXPECTED_DICTIONARY_NAME);
        assertEquals(EXPECTED_PHONE_NUMBER, reader.phoneNumbers().get(0));
    }

    @Test
    public void shouldReturnContentOfPhone1Txt() throws Exception {

        ClassLoaderReader classLoaderDictionaryReader = new ClassLoaderReader();
        classLoaderDictionaryReader.sourceOfData(FILE_THAT_CONTAINS_PHONE_NUMBERS);

        AbstractIterator iterator = classLoaderDictionaryReader.iterator();

        String phoneTxtFile = ClassLoader.getSystemClassLoader().getSystemResource(FILE_THAT_CONTAINS_PHONE_NUMBERS).getPath();
        reader = new UserInputReader(switchForPhone + phoneTxtFile);
        List<String> phoneNumbers = reader.phoneNumbers();

        assertNotNull(phoneNumbers);

        String phoneNumber;

        int index = 0;
        while ((phoneNumber = iterator.next()) != null) {
            assertEquals(phoneNumber, phoneNumbers.get(index));
            index += 1;
        }
    }

    @Test
    public void shouldReturnAnEmptyListOfPhoneNumbers() throws Exception {
        reader = new UserInputReader(switchForPhone + "non-existing-file.txt");
        List<String> phoneNumbers = reader.phoneNumbers();
        assertEquals(0,phoneNumbers.size());

    }

}
