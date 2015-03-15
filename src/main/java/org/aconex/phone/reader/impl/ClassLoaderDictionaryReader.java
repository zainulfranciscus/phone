package org.aconex.phone.reader.impl;

import java.io.*;
import java.net.URL;

import org.aconex.phone.reader.FileBasedDictionaryReader;

/**
 * Created by Lenovo on 10/03/2015.
 */
public class ClassLoaderDictionaryReader extends FileBasedDictionaryReader {

    private String fileInClasspath;

    @Override
    public void sourceOfData(String file) {
        fileInClasspath = file;
    }

    @Override
    public Reader reader() {
        if (!fileExist()) {
            return null;
        }
        return new InputStreamReader(getClass().getClassLoader().getResourceAsStream(fileInClasspath));
    }

    @Override
    public boolean fileExist() {
        return this.getClass().getClassLoader().getResource(fileInClasspath) != null;
    }

}
