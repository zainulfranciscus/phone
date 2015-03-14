package org.aconex.phone.datasource;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by Lenovo on 10/03/2015.
 */
public interface DictionaryProvider {

    void sourceOfData(String location);

    DictionaryIterator iterator() throws Exception;

}
