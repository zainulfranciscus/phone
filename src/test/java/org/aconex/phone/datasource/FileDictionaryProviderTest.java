package org.aconex.phone.datasource;

import org.aconex.phone.datasource.impl.FileDictionaryProvider;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Lenovo on 11/03/2015.
 */
public class FileDictionaryProviderTest extends FileBasedDictionaryProviderTest {

    static FileBasedDictionaryProvider provider = new FileDictionaryProvider();

    @Override
    public FileBasedDictionaryProvider provider() {


        provider.sourceOfData(dictionaryForUnitTest());
        return provider;
    }

    public static String dictionaryForUnitTest(){
        return provider.getClass().getClassLoader().getResource(DICTIONARY_FILE_THAT_EXIST).getFile();
    }

}
