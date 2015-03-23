package org.aconex.phone.repository;

import org.aconex.phone.criteria.PhoneNumberCriteria;
import org.aconex.phone.domain.DictionaryWord;
import org.aconex.phone.reader.iterator.AbstractIterator;
import org.aconex.phone.reader.Reader;
import org.aconex.phone.reader.impl.ClassLoaderReader;
import org.aconex.phone.repository.impl.DictionaryRepositoryImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.aconex.phone.domain.DictionaryWordTest.NUMBER_46;
import static org.aconex.phone.domain.DictionaryWordTest.THE_WORD_GO;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by Lenovo on 7/03/2015.
 */
public class DictionaryRepositoryTest {

    private DictionaryRepository dictionaryRepository;

    @Before
    public void setup() {
        dictionaryRepository = new DictionaryRepositoryImpl();
    }

    @Test
    public void testSetDataSourceProvider() {

        Reader provider = new ClassLoaderReader();
        dictionaryRepository.setReader(provider);
        assertEquals(provider, dictionaryRepository.getReader());
    }

    @Test
    public void testShouldReturnWordsThatMatchesPhoneNumber() throws Exception {

        AbstractIterator mockIterator = mock(AbstractIterator.class);
        Reader mockProvider = mock(Reader.class);
        when(mockProvider.iterator()).thenReturn(mockIterator);


        when(mockIterator.next()).thenReturn(THE_WORD_GO,null);

        PhoneNumberRepository mockPhoneNumberRepository = mock(PhoneNumberRepository.class);
        when(mockPhoneNumberRepository.convertWordToNumber(THE_WORD_GO)).thenReturn(NUMBER_46);

        dictionaryRepository.setReader(mockProvider);
        dictionaryRepository.setPhoneNumberRepository(mockPhoneNumberRepository);
        List<DictionaryWord> words = dictionaryRepository.findWordThatMatchesPhoneNumber(new PhoneNumberCriteria(NUMBER_46));

        assertEquals(1,words.size());
        assertEquals(THE_WORD_GO, words.get(0).getWord());

        verify(mockPhoneNumberRepository,times(1)).convertWordToNumber(THE_WORD_GO);

        verify(mockIterator,times(2)).next();


    }




}
