package org.aconex.phone;

import org.aconex.phone.datasource.DictionaryProvider;
import org.aconex.phone.datasource.impl.ClassLoaderDictionaryProvider;
import org.aconex.phone.datasource.impl.FileDictionaryProvider;
import org.aconex.phone.repository.DictionaryRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import java.io.BufferedReader;
import java.io.IOException;

import static junit.framework.TestCase.*;
import static org.aconex.phone.Main.CMD_SWITCH_FOR_DICTIONARY;
import static org.aconex.phone.datasource.FileBasedDictionaryProviderTest.NON_EXISTING_FILE;
import static org.aconex.phone.datasource.FileDictionaryProviderTest.dictionaryForUnitTest;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MainTest {

    public static final String EMPTY_SPACE = " ";
    private Main main = new Main();
    public static final String EXPECTED_DICTIONARY_NAME = "dictionary.txt";
    public static final String EXPECTED_PHONE_NUMBER = "041112222";

    @Rule
    public TextFromStandardInputStream systemInMock = emptyStandardInputStream();

    @Test
    public void shouldReturnNameOfDictionary() {

        String nameOfDictionary = main.nameOfDictionaryFromCommandLineArgs(CMD_SWITCH_FOR_DICTIONARY + EXPECTED_DICTIONARY_NAME);
        assertEquals(EXPECTED_DICTIONARY_NAME, nameOfDictionary);

    }

    @Test
    public void shouldReturnNameOfDictionaryWithoutSpaces() {

        String nameOfDictionary = main.nameOfDictionaryFromCommandLineArgs(CMD_SWITCH_FOR_DICTIONARY + EMPTY_SPACE + EXPECTED_DICTIONARY_NAME);
        assertEquals(EXPECTED_DICTIONARY_NAME, nameOfDictionary);
    }

    @Test
    public void shouldReturnNullWhenNoDictionaryNameIsProvided() {

        String nameOfDictionary = main.nameOfDictionaryFromCommandLineArgs("0455551111");
        assertNull(nameOfDictionary);

    }

    @Test
    public void shouldReturnNullWhenCommandLineArgsIsNull(){
        String nameOfDictionary = main.nameOfDictionaryFromCommandLineArgs(null);
        assertNull(nameOfDictionary);
    }

    @Test
    public void shouldReturnPhoneNumber() {
        String phoneNumber = main.phoneNumberFromCommandLineArgs(EXPECTED_PHONE_NUMBER);
        assertEquals(EXPECTED_PHONE_NUMBER, phoneNumber);
    }

    @Test
    public void shouldReturnPhoneNumberWithoutSpaces() {
        String phoneNumber = main.phoneNumberFromCommandLineArgs(EXPECTED_PHONE_NUMBER + EMPTY_SPACE);
        assertEquals(EXPECTED_PHONE_NUMBER, phoneNumber);
    }

    @Test
    public void shouldReturnNullWhenPhoneNumberIsNull(){
        String phoneNumber = main.phoneNumberFromCommandLineArgs(null);
        assertNull(phoneNumber);
    }

    @Test
    public void shouldReturnEmptyStringWhenNumberIsNotSpecified() {
        String phoneNumber = main.phoneNumberFromCommandLineArgs(EMPTY_SPACE);
        assertEquals("", phoneNumber);
    }

    @Test
    public void shouldReturnPhoneNumberWhenThereIsADSwitch() {
        assertEquals(EXPECTED_PHONE_NUMBER, main.phoneNumberFromCommandLineArgs(EXPECTED_PHONE_NUMBER + EMPTY_SPACE + CMD_SWITCH_FOR_DICTIONARY + EXPECTED_DICTIONARY_NAME));
    }

    @Test
    public void shouldReturnAClassLoaderProviderWhenFileDoesNotExist() {
        DictionaryProvider dictionaryProvider = main.dictionaryProvider(NON_EXISTING_FILE);
        assertTrue(dictionaryProvider instanceof ClassLoaderDictionaryProvider);
    }

    @Test
    public void shouldReturnFileDictionaryProviderWhenFileExist() {
        DictionaryProvider dictionaryProvider = main.dictionaryProvider(dictionaryForUnitTest());
        assertTrue(dictionaryProvider instanceof FileDictionaryProvider);
    }

    @Test
    public void shouldReturnUserInput() throws IOException {
        BufferedReader mockReader = mock(BufferedReader.class);
        String expectedResult = "user input";
        when(mockReader.readLine()).thenReturn(expectedResult);

        assertEquals(expectedResult,main.readUserInput(mockReader));
    }

    @Test
    public void shouldReturnADictionaryRepository() {
        DictionaryRepository dictionaryRepository = main.getDictionaryRepository(dictionaryForUnitTest());
        assertNotNull(dictionaryRepository);
        assertNotNull(dictionaryRepository.getDictionaryProvider());

    }

    @Test
    public void mainShouldNotThrownAnExceptionWhenPhoneNumberIsProvided(){

        systemInMock.provideText("0411112222");
        checkThatExceptionIsNotRaised();
    }

    @Test
    public void mainShouldNotThrownAnExceptionWhenPhoneNumberAndDictionaryFileIsProvided(){

        systemInMock.provideText("4466 -d words.txt");
        checkThatExceptionIsNotRaised();
    }

    @Test
    public void mainShouldNotThrownAnExceptionWhenNoInputIsEntered(){

        systemInMock.provideText("");
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





}
