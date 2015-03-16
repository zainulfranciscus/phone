package org.aconex.phone.reader;

/**
 * Created by Zainul Franciscus on 14/03/2015.
 */
public interface DictionaryReader {

    void sourceOfData(String location);

    DictionaryIterator iterator() throws Exception;

}
