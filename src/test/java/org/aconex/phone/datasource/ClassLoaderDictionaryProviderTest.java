package org.aconex.phone.datasource;

import org.aconex.phone.datasource.impl.ClassLoaderDictionaryProvider;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Lenovo on 10/03/2015.
 */
public class ClassLoaderDictionaryProviderTest extends FileBasedDictionaryProviderTest{

    FileBasedDictionaryProvider provider = new ClassLoaderDictionaryProvider();

    @Override
    public FileBasedDictionaryProvider provider() {

        provider.sourceOfData(DICTIONARY_FILE_THAT_EXIST);
        return provider;
    }

    @Test
    public void shouldReturnANullFile(){
        provider.sourceOfData(NON_EXISTING_FILE);
        assertNull(provider.file());

    }

}
