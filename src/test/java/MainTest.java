import org.aconex.phone.reader.DictionaryReader;
import org.aconex.phone.reader.impl.ClassLoaderDictionaryReader;
import org.aconex.phone.reader.impl.FileDictionaryReader;
import org.aconex.phone.repository.DictionaryRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.Console;

import static junit.framework.TestCase.*;
import static org.aconex.phone.reader.FileBasedDictionaryProviderTest.NON_EXISTING_FILE;
import static org.aconex.phone.reader.FileDictionaryReaderTest.dictionaryForUnitTest;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import static org.powermock.api.mockito.PowerMockito.*;


/**
 * Created by Zainul Franciscus on 16/03/2015.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({Main.class})
public class MainTest {

    public static final String EMPTY_SPACE = " ";
    private Main main = new Main();
    public static final String EXPECTED_DICTIONARY_NAME = "dictionary.txt";
    public static final String EXPECTED_PHONE_NUMBER = "041112222";
    private Console mockConsole;

    @Before
    public void setup(){
        PowerMockito.mockStatic(System.class);
        mockConsole = mock(Console.class);

        Mockito.when(System.console()).thenReturn(mockConsole);
    }

    @Test
    public void shouldReturnNameOfDictionary() {

        String nameOfDictionary = main.dictionaryFileFromConsoleInput(Main.CMD_SWITCH_FOR_DICTIONARY + EXPECTED_DICTIONARY_NAME);
        assertEquals(EXPECTED_DICTIONARY_NAME, nameOfDictionary);

    }

    @Test
    public void shouldReturnNameOfDictionaryWithoutSpaces() {

        String nameOfDictionary = main.dictionaryFileFromConsoleInput(Main.CMD_SWITCH_FOR_DICTIONARY + EMPTY_SPACE + EXPECTED_DICTIONARY_NAME);
        assertEquals(EXPECTED_DICTIONARY_NAME, nameOfDictionary);
    }

    @Test
    public void shouldReturnNullWhenNoDictionaryNameIsProvided() {

        String nameOfDictionary = main.dictionaryFileFromConsoleInput("0455551111");
        assertNull(nameOfDictionary);

    }

    @Test
    public void shouldReturnNullWhenCommandLineArgsIsNull(){
        String nameOfDictionary = main.dictionaryFileFromConsoleInput(null);
        assertNull(nameOfDictionary);
    }

    @Test
    public void shouldReturnPhoneNumber() {
        String phoneNumber = main.phoneNumberFromConsoleInput(EXPECTED_PHONE_NUMBER);
        assertEquals(EXPECTED_PHONE_NUMBER, phoneNumber);
    }

    @Test
    public void shouldReturnPhoneNumberWithoutSpaces() {
        String phoneNumber = main.phoneNumberFromConsoleInput(EXPECTED_PHONE_NUMBER + EMPTY_SPACE);
        assertEquals(EXPECTED_PHONE_NUMBER, phoneNumber);
    }

    @Test
    public void shouldReturnNullWhenPhoneNumberIsNull(){
        String phoneNumber = main.phoneNumberFromConsoleInput(null);
        assertNull(phoneNumber);
    }

    @Test
    public void shouldReturnEmptyStringWhenNumberIsNotSpecified() {
        String phoneNumber = main.phoneNumberFromConsoleInput(EMPTY_SPACE);
        assertEquals("", phoneNumber);
    }

    @Test
    public void shouldReturnPhoneNumberWhenThereIsADSwitch() {
        assertEquals(EXPECTED_PHONE_NUMBER, main.phoneNumberFromConsoleInput(EXPECTED_PHONE_NUMBER + EMPTY_SPACE + Main.CMD_SWITCH_FOR_DICTIONARY + EXPECTED_DICTIONARY_NAME));
    }

    @Test
    public void shouldReturnAClassLoaderProviderWhenFileDoesNotExist() {
        DictionaryReader dictionaryReader = main.dictionaryProvider(NON_EXISTING_FILE);
        assertTrue(dictionaryReader instanceof ClassLoaderDictionaryReader);
    }

    @Test
    public void shouldReturnFileDictionaryProviderWhenFileExist() {
        DictionaryReader dictionaryReader = main.dictionaryProvider(dictionaryForUnitTest());
        assertTrue(dictionaryReader instanceof FileDictionaryReader);
    }



    @Test
    public void shouldReturnADictionaryRepository() {
        DictionaryRepository dictionaryRepository = main.getDictionaryRepository(dictionaryForUnitTest());
        assertNotNull(dictionaryRepository);
        assertNotNull(dictionaryRepository.getDictionaryProvider());

    }

    @Test
    public void mainShouldNotThrowExceptionWhenOnlyPhoneNumberIsProvided(){

        mockConsoleReturnValue("0411112222");
        checkThatExceptionIsNotRaised();
    }

    @Test
    public void mainShouldNotThrowExceptionWhenPhoneNumberAndDictionaryFileIsProvided(){

        mockConsoleReturnValue("4466 -d words.txt");
        checkThatExceptionIsNotRaised();
    }

    @Test
    public void mainShouldNotThrowExceptionWhenNoInputIsEntered(){

        mockConsoleReturnValue("");
        checkThatExceptionIsNotRaised();
    }

    private void checkThatExceptionIsNotRaised(){
        boolean exceptionRaised = false;
        try {
            Main.main(new String[]{});
        }catch (Exception ex) {
            exceptionRaised = true;
        }

        assertFalse(exceptionRaised);
    }

    private void mockConsoleReturnValue(String value) {
        when(mockConsole.readLine()).thenReturn(value);
    }





}
