package org.aconex.phone.reader;

import java.io.*;

/**
 * Created by Zainul Franciscus on 14/03/2015.
 */
public abstract class FileBasedDictionaryReader implements DictionaryReader {

    @Override
    public DictionaryIterator iterator() throws Exception {


        final BufferedReader br = new BufferedReader(reader());

        FileIterator iterator = new FileIterator();
        iterator.setReader(br);
        return iterator;
    }


    public abstract Reader reader()throws Exception;

    public abstract boolean fileExist();
}
