import org.aconex.phone.datasource.DictionaryProvider;
import org.aconex.phone.datasource.impl.InMemoryDictionaryProvider;
import org.aconex.phone.factory.DefaultPhoneNumberRepositoryFactory;
import org.aconex.phone.repository.DictionaryRepository;
import org.aconex.phone.repository.impl.DictionaryRepositoryImpl;
import org.aconex.phone.service.PhoneNumberConverterService;
import org.aconex.phone.service.impl.PhoneNumberConverterServiceImpl;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;

import java.util.Set;


@RunWith(ConcordionRunner.class)
public class PhoneFixture {

    private DictionaryProvider provider = new InMemoryDictionaryProvider();

    public Set<String> convertPhoneNumber(String phoneNumber) throws Exception {

        DictionaryRepository dictionaryRepository = new DictionaryRepositoryImpl();
        dictionaryRepository.setDictionaryProvider(provider);

        dictionaryRepository.setPhoneNumberRepository(DefaultPhoneNumberRepositoryFactory.getInstance());

        PhoneNumberConverterService phoneNumberConverterService = new PhoneNumberConverterServiceImpl();
        phoneNumberConverterService.setDictionaryRepository(dictionaryRepository);

        Set<String> words = phoneNumberConverterService.encode(phoneNumber);
        return words;
    }

    public void setUpDictionary(String word) throws Exception {
        provider.sourceOfData(word);
    }
}
