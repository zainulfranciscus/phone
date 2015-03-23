package org.aconex.phone.reader;

import org.aconex.phone.reader.impl.InMemoryReader;
import org.aconex.phone.reader.iterator.AbstractIterator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by Zainul Franciscus on 14/03/2015.
 */
public class InMemoryReaderTest {

    private Reader inMemory;

    @Before
    public void setup() {
        inMemory = new InMemoryReader();
    }
    @Test
    public void shouldReturnListOfWords() throws Exception {

        String expectedWord = "Carnival";
        inMemory.sourceOfData(expectedWord);
        AbstractIterator iterator = inMemory.iterator();

        assertEquals(expectedWord,iterator.next());
        assertNull(iterator.next());
    }

    @Test
    public void shouldReturnNullBecauseThereIsNoResourceToClose() throws Exception {
        assertNull(inMemory.iterator().resourceToClose());

    }
}
