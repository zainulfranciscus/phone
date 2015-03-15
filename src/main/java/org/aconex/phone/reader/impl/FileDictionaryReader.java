package org.aconex.phone.reader.impl;

import org.aconex.phone.reader.FileBasedDictionaryReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

/**
 * Created by Lenovo on 11/03/2015.
 */
public class FileDictionaryReader extends FileBasedDictionaryReader {


    private String locationOfFile;

    @Override
    public Reader reader() throws FileNotFoundException {
        FileReader reader = new FileReader(new File(locationOfFile));
        return reader;
    }

    @Override
    public boolean fileExist() {
        return new File(locationOfFile).exists();
    }

    @Override
    public void sourceOfData(String f) {
        locationOfFile = f;
      
    }
}
