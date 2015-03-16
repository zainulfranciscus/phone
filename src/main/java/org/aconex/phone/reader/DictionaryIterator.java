package org.aconex.phone.reader;

import java.io.*;
import java.util.Iterator;

/**
 * Created by Zainul Franciscus on 14/03/2015.
 */
public abstract class DictionaryIterator{

    public abstract String next() throws Exception;

    public abstract AutoCloseable resourceToClose();

    public void close() throws Exception {
        if(resourceToClose() != null) {
            resourceToClose().close();
        }
    }
}
