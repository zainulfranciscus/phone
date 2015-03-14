package org.aconex.phone.datasource;

import org.aconex.phone.datasource.impl.InMemoryDictionaryProvider;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by Lenovo on 10/03/2015.
 */
public class InMemoryDictionaryProviderTest {

    private DictionaryProvider inMemory;

    @Before
    public void setup() {
        inMemory = new InMemoryDictionaryProvider();
    }
    @Test
    public void shouldReturnListOfWords() throws Exception {

        String expectedWord = "Carnival";
        inMemory.sourceOfData(expectedWord);
        DictionaryIterator iterator = inMemory.iterator();

        assertEquals(expectedWord,iterator.next());
        assertNull(iterator.next());
    }

    @Test
    public void shouldReturnNullBecauseThereIsNoResourceToClose() throws Exception {
        assertNull(inMemory.iterator().resourceToClose());

    }
}
