import org.aconex.phone.reader.DictionaryReader;
import org.aconex.phone.reader.impl.ClassLoaderDictionaryReader;
import org.aconex.phone.reader.impl.FileReader;
import org.aconex.phone.repository.DictionaryRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.Console;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.TestCase.*;
import static org.aconex.phone.reader.FileBasedDictionaryProviderTest.NON_EXISTING_FILE;
import static org.aconex.phone.reader.FileReaderTest.dictionaryForUnitTest;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import static org.powermock.api.mockito.PowerMockito.*;


/**
 * Created by Zainul Franciscus on 16/03/2015.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({Main.class})
public class MainTest {

    private Main main = new Main();
    private Console mockConsole;

    @Before
    public void setup(){
        PowerMockito.mockStatic(System.class);
        mockConsole = mock(Console.class);
        Mockito.when(System.console()).thenReturn(mockConsole);

    }



    @Test
    public void shouldReturnAClassLoaderProviderWhenFileDoesNotExist() {
        DictionaryReader dictionaryReader = main.dictionaryReader(NON_EXISTING_FILE);
        assertTrue(dictionaryReader instanceof ClassLoaderDictionaryReader);
    }

    @Test
    public void shouldReturnFileDictionaryProviderWhenFileExist() {
        DictionaryReader dictionaryReader = main.dictionaryReader(dictionaryForUnitTest());
        assertTrue(dictionaryReader instanceof FileReader);
    }



    @Test
    public void shouldReturnADictionaryRepository() {
        DictionaryRepository dictionaryRepository = main.getDictionaryRepository(dictionaryForUnitTest());
        assertNotNull(dictionaryRepository);
        assertNotNull(dictionaryRepository.getDictionaryReader());

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
