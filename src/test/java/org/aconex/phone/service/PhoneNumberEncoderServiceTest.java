package org.aconex.phone.service;


import org.aconex.phone.domain.DictionaryWord;
import org.aconex.phone.repository.DictionaryRepository;

import org.aconex.phone.service.impl.PhoneNumberEncoderServiceImpl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Zainul Franciscus on 3/03/2015.
 */
public class PhoneNumberEncoderServiceTest {

    private static PhoneNumberEncoderService phoneNumberEncoderService = new PhoneNumberEncoderServiceImpl();

    @Test
    public void shouldRecogniseAll2ConsecutiveDigits() {
        assertTrue(phoneNumberEncoderService.has2ConsecutiveDigits("AA22CC"));
        assertTrue(phoneNumberEncoderService.has2ConsecutiveDigits("33CC"));
        assertTrue(phoneNumberEncoderService.has2ConsecutiveDigits("BB33"));
    }

    @Test
    public void shouldBeFalseWhenThereIsOnly1Number() {
        assertFalse(phoneNumberEncoderService.has2ConsecutiveDigits("ZZ2CC"));
        assertFalse(phoneNumberEncoderService.has2ConsecutiveDigits("3KL"));
        assertFalse(phoneNumberEncoderService.has2ConsecutiveDigits("YU3"));
    }

    @Test
    public void shouldBeTrueWhenThereIsMoreThan2ConsecutiveDigits() {
        assertTrue(phoneNumberEncoderService.has2ConsecutiveDigits("XX1234CC"));
        assertTrue(phoneNumberEncoderService.has2ConsecutiveDigits("5689DDVV"));
        assertTrue(phoneNumberEncoderService.has2ConsecutiveDigits("QWER33110"));
    }

    @Test
    public void emptyPhoneNumbersShouldBeConvertedToEmptyString() throws Exception {
        Set<String> wordFromDictionary = phoneNumberEncoderService.encode("");
        assertEquals(0, wordFromDictionary.size());
    }

    @Test
    public void shouldBeReplacedWithGo() throws Exception {

        String phoneNumber = "46";
        String wordThatMatchesPhoneNumber = "GO";

        List<DictionaryWord> wordThatMatches46 = new ArrayList<DictionaryWord>();
        wordThatMatches46.add(new DictionaryWord(wordThatMatchesPhoneNumber, phoneNumber));

        DictionaryRepository mockDictionaryRepository = mock(DictionaryRepository.class);
        when(mockDictionaryRepository.findWordThatMatchesPhoneNumber(phoneNumber)).thenReturn(wordThatMatches46);

        phoneNumberEncoderService.setDictionaryRepository(mockDictionaryRepository);
        Set<String> words = phoneNumberEncoderService.encode(phoneNumber);

        assertEquals(1,words.size());
        assertEquals(wordThatMatchesPhoneNumber,words.iterator().next());

    }

    @Test
    public void shouldReturnThePhoneNumber() throws Exception {
        String phoneNumber = "88";

        DictionaryRepository mockDictionaryRepository = mock(DictionaryRepository.class);
        when(mockDictionaryRepository.findWordThatMatchesPhoneNumber(phoneNumber)).thenReturn(new ArrayList<DictionaryWord>());

        phoneNumberEncoderService.setDictionaryRepository(mockDictionaryRepository);
        Set<String> words = phoneNumberEncoderService.encode(phoneNumber);

        assertEquals(1,words.size());
        assertEquals(phoneNumber,words.iterator().next());
    }


}
