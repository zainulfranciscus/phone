import org.aconex.phone.reader.DictionaryReader;
import org.aconex.phone.reader.impl.InMemoryDictionaryReader;
import org.aconex.phone.factory.DefaultPhoneNumberRepositoryFactory;
import org.aconex.phone.repository.DictionaryRepository;
import org.aconex.phone.repository.impl.DictionaryRepositoryImpl;
import org.aconex.phone.service.PhoneNumberEncoderService;
import org.aconex.phone.service.impl.PhoneNumberEncoderServiceImpl;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;

import java.util.Set;

/**
 * Created by Zainul Franciscus on 16/03/2015.
 */
@RunWith(ConcordionRunner.class)
public class PhoneFixture {

    private DictionaryReader provider = new InMemoryDictionaryReader();

    public Set<String> convertPhoneNumber(String phoneNumber) throws Exception {

        DictionaryRepository dictionaryRepository = new DictionaryRepositoryImpl();
        dictionaryRepository.setDictionaryProvider(provider);

        dictionaryRepository.setPhoneNumberRepository(DefaultPhoneNumberRepositoryFactory.getInstance());

        PhoneNumberEncoderService phoneNumberEncoderService = new PhoneNumberEncoderServiceImpl();
        phoneNumberEncoderService.setDictionaryRepository(dictionaryRepository);

        Set<String> words = phoneNumberEncoderService.encode(phoneNumber);
        return words;
    }

    public void setUpDictionary(String word) throws Exception {
        provider.sourceOfData(word);
    }
}
