package org.aconex.phone.reader;

import org.aconex.phone.reader.impl.FileReader;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Zainul Franciscus on 14/03/2015.
 */
public class FileReaderTest extends AbstractReaderTest {

    static AbstractReader reader = new FileReader();

    @Override
    public AbstractReader reader() {

        String path = dictionaryForUnitTest();
        reader.sourceOfData(path);
        return reader;
    }

    public static String dictionaryForUnitTest(){
        return ClassLoader.getSystemClassLoader().getSystemResource(DICTIONARY_FILE_THAT_EXIST).getPath();
    }

}
