package org.aconex.phone.reader.impl;

import org.aconex.phone.reader.AbstractReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Reader;

/**
 * Created by Zainul Franciscus on 14/03/2015.
 */
public class FileReader extends AbstractReader {

    private String locationOfFile;

    @Override
    public Reader reader() throws FileNotFoundException {
        java.io.FileReader reader = new java.io.FileReader(new File(locationOfFile));
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
