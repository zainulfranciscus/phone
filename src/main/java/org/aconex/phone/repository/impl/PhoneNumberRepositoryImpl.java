package org.aconex.phone.repository.impl;


import org.aconex.phone.domain.PhoneNumber;
import org.aconex.phone.reader.PhoneConfigurationReader;
import org.aconex.phone.repository.PhoneNumberRepository;

import java.io.IOException;

/*/*
 * Created by Zainul Franciscus on 14/03/2015.
 */
public class PhoneNumberRepositoryImpl implements PhoneNumberRepository {


    @Override
    public PhoneNumber convertWordToNumber(String word) throws IOException {

        PhoneConfigurationReader reader = PhoneConfigurationReader.getInstance("PhoneConfiguration.properties");
        StringBuilder builder = new StringBuilder();

        for (Character c : word.toCharArray()) {

            String number = reader.find(String.valueOf(c));

            if(number == null){
                continue;
            }

            builder.append(number);

        }

        return new PhoneNumber(builder.toString());
    }
}
