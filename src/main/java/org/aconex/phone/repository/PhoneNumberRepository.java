package org.aconex.phone.repository;

import org.aconex.phone.domain.PhoneNumber;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zainul Franciscus on 15/03/2015.
 */
public interface PhoneNumberRepository {

    PhoneNumber convertWordToNumber(String word) throws IOException;
}
