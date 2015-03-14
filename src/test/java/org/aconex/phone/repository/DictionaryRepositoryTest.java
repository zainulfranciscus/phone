package org.aconex.phone.repository;

import org.aconex.phone.domain.DictionaryWord;
import org.aconex.phone.reader.DictionaryIterator;
import org.aconex.phone.reader.DictionaryReader;
import org.aconex.phone.reader.impl.ClassLoaderDictionaryReader;
import org.aconex.phone.repository.impl.DictionaryRepositoryImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by Lenovo on 7/03/2015.
 */
public class DictionaryRepositoryTest {

    public static final String A_WORD_CALLED_GO = "Go";
    private DictionaryRepository dictionaryRepository;
    public static final String PHONE_NUMBER_46 = "46";

    @Before
    public void setup() {

        dictionaryRepository = new DictionaryRepositoryImpl();

    }

    @Test
    public void testSetDataSourceProvider() {

        DictionaryReader provider = new ClassLoaderDictionaryReader();
        dictionaryRepository.setDictionaryProvider(provider);
        assertEquals(provider, dictionaryRepository.getDictionaryProvider());
    }

    @Test
    public void testShouldReturnWordsThatMatchesPhoneNumber() throws Exception {

        DictionaryIterator mockIterator = mock(DictionaryIterator.class);
        DictionaryReader mockProvider = mock(DictionaryReader.class);
        when(mockProvider.iterator()).thenReturn(mockIterator);


        when(mockIterator.next()).thenReturn(A_WORD_CALLED_GO,null);

        PhoneNumberRepository mockPhoneNumberRepository = mock(PhoneNumberRepository.class);
        when(mockPhoneNumberRepository.convertWordToNumber(A_WORD_CALLED_GO)).thenReturn(PHONE_NUMBER_46);

        dictionaryRepository.setDictionaryProvider(mockProvider);
        dictionaryRepository.setPhoneNumberRepository(mockPhoneNumberRepository);
        List<DictionaryWord> words = dictionaryRepository.findWordThatMatchesPhoneNumber(PHONE_NUMBER_46);

        assertEquals(1,words.size());
        assertEquals(A_WORD_CALLED_GO, words.get(0).getWord());

        verify(mockPhoneNumberRepository,times(1)).convertWordToNumber(A_WORD_CALLED_GO);

        verify(mockIterator,times(2)).next();


    }




}
