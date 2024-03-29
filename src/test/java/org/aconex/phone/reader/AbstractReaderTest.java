package org.aconex.phone.reader;

import org.aconex.phone.reader.iterator.AbstractIterator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

/**
 * Created by Zainul Franciscus on 14/03/2015.
 */
public abstract class AbstractReaderTest {

    public static final String DICTIONARY_FILE_THAT_EXIST = "words.txt";
    public static final String NON_EXISTING_FILE = "non_existing_file.txt";
    AbstractReader dictionaryProvider;

    @Before
    public void setup(){
        dictionaryProvider = reader();
    }

    @Test
    public void iteratorShouldReturn5WordsFromTestFile() throws Exception {

        AbstractIterator iterator = dictionaryProvider.iterator();
        assertNotNull(iterator);
        assertEquals("Air", iterator.next());
        assertEquals("Animal", iterator.next());
        assertEquals("Go", iterator.next());
        assertEquals("In", iterator.next());
        assertEquals("Oh", iterator.next());

    }

    @Test
    public void shouldBeTrueBecauseTheDictionaryFileExist(){
        assertTrue(dictionaryProvider.fileExist());

    }

    @Test
    public void shouldBeFalseBecauseTheDictionaryFileDoesNotExist(){
        dictionaryProvider.sourceOfData(NON_EXISTING_FILE);
        assertFalse(dictionaryProvider.fileExist());
    }



    public abstract AbstractReader reader();
}
