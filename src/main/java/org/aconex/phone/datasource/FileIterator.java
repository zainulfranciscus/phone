package org.aconex.phone.datasource;

import java.io.*;

/**
 * Created by Lenovo on 11/03/2015.
 */
public class FileIterator extends DictionaryIterator {


    private BufferedReader reader;

    @Override
    public String next() throws Exception {
        return reader.readLine();
    }

    @Override
    public Closeable resourceToClose() {
        return  reader;
    }

    public void setReader(BufferedReader r) {
        reader = r;
    }

}
