package org.aconex.phone.reader;

import org.aconex.phone.reader.iterator.AbstractIterator;

/**
 * Created by Zainul Franciscus on 14/03/2015.
 */
public interface Reader {

    void sourceOfData(String location);

    AbstractIterator iterator() throws Exception;

}
