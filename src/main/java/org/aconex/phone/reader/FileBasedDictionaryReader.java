package org.aconex.phone.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Created by Lenovo on 11/03/2015.
 */
public abstract class FileBasedDictionaryReader implements DictionaryReader {

    @Override
    public DictionaryIterator iterator() throws Exception {

        FileReader fileReader = new FileReader(file());
        final BufferedReader br = new BufferedReader(fileReader);

        FileIterator iterator = new FileIterator();
        iterator.setReader(br);
        return iterator;
    }


    public abstract File file();

    public abstract boolean fileExist();
}
