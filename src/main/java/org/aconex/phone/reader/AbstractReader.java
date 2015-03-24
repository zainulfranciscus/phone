package org.aconex.phone.reader;

import org.aconex.phone.reader.iterator.AbstractIterator;
import org.aconex.phone.reader.iterator.FileIterator;

import java.io.*;

/**
 * Created by Zainul Franciscus on 14/03/2015.
 */
public abstract class AbstractReader implements Reader {

    @Override
    public AbstractIterator iterator() throws Exception {

        final BufferedReader br = new BufferedReader(reader());

        FileIterator iterator = new FileIterator();
        iterator.setReader(br);
        return iterator;
    }


    public abstract java.io.Reader reader()throws Exception;

    public abstract boolean fileExist();
}
