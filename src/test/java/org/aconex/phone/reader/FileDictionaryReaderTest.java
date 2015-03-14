package org.aconex.phone.reader;

import org.aconex.phone.reader.impl.FileDictionaryReader;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Lenovo on 11/03/2015.
 */
public class FileDictionaryReaderTest extends FileBasedDictionaryProviderTest {

    static FileBasedDictionaryReader provider = new FileDictionaryReader();

    @Override
    public FileBasedDictionaryReader provider() {


        provider.sourceOfData(dictionaryForUnitTest());
        return provider;
    }

    public static String dictionaryForUnitTest(){
        return provider.getClass().getClassLoader().getResource(DICTIONARY_FILE_THAT_EXIST).getFile();
    }

}