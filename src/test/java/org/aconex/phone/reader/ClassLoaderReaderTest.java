package org.aconex.phone.reader;

import org.aconex.phone.reader.impl.ClassLoaderReader;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Zainul Franciscus on 14/03/2015.
 */
public class ClassLoaderReaderTest extends AbstractReaderTest {

    AbstractReader provider = new ClassLoaderReader();

    @Override
    public AbstractReader reader() {

        provider.sourceOfData(DICTIONARY_FILE_THAT_EXIST);
        return provider;
    }

    @Test
    public void shouldReturnANullFile() throws Exception {
        provider.sourceOfData(NON_EXISTING_FILE);
        assertNull(provider.reader());

    }

}
