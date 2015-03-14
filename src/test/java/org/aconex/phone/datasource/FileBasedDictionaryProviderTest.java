package org.aconex.phone.datasource;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

/**
 * Created by Lenovo on 12/03/2015.
 */
public abstract class FileBasedDictionaryProviderTest {

    public static final String DICTIONARY_FILE_THAT_EXIST = "words.txt";
    public static final String NON_EXISTING_FILE = "non_existing_file.txt";
    FileBasedDictionaryProvider dictionaryProvider;

    @Before
    public void setup(){
        dictionaryProvider = provider();
    }

    @Test
    public void iteratorShouldReturn5WordsFromTestFile() throws Exception {

        DictionaryIterator iterator = dictionaryProvider.iterator();
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



    public abstract FileBasedDictionaryProvider provider();
}
