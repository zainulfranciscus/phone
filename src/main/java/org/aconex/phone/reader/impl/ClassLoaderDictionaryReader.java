package org.aconex.phone.reader.impl;

import java.io.*;

import org.aconex.phone.reader.AbstractReader;

/**
 * Created by Zainul Franciscus on 14/03/2015.
 */
public class ClassLoaderDictionaryReader extends AbstractReader {

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
        return new InputStreamReader(getClassLoader().getResourceAsStream(fileInClasspath));
    }

    @Override
    public boolean fileExist() {
        return getClassLoader().getResource(fileInClasspath) != null;
    }

    private ClassLoader getClassLoader(){
        return getClass().getClassLoader();
    }

}
