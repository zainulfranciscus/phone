package org.aconex.phone.reader;

/**
 * Created by Lenovo on 10/03/2015.
 */
public interface DictionaryReader {

    void sourceOfData(String location);

    DictionaryIterator iterator() throws Exception;

}
