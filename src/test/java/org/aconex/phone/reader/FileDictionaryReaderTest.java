package org.aconex.phone.reader;

import org.aconex.phone.reader.impl.FileDictionaryReader;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Zainul Franciscus on 14/03/2015.
 */
public class FileDictionaryReaderTest extends FileBasedDictionaryProviderTest {

    static FileBasedDictionaryReader provider = new FileDictionaryReader();

    @Override
    public FileBasedDictionaryReader provider() {

        String path = dictionaryForUnitTest();
        provider.sourceOfData(path);
        return provider;
    }

    public static String dictionaryForUnitTest(){
        return ClassLoader.getSystemClassLoader().getSystemResource(DICTIONARY_FILE_THAT_EXIST).getPath();
    }

}
