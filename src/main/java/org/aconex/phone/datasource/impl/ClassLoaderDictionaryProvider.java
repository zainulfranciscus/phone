package org.aconex.phone.datasource.impl;

import java.io.*;
import java.net.URL;

import org.aconex.phone.datasource.FileBasedDictionaryProvider;

/**
 * Created by Lenovo on 10/03/2015.
 */
public class ClassLoaderDictionaryProvider extends FileBasedDictionaryProvider {

    private String fileInClasspath;
    private URL locationOfFileInClassPath;

    @Override
    public void sourceOfData(String file) {
        fileInClasspath = file;
        locationOfFileInClassPath = this.getClass().getClassLoader().getResource(fileInClasspath);

    }

    @Override
    public File file() {

        if(!fileExist()) {
            return null;
        }

        return new File(locationOfFileInClassPath.getFile());
    }

    @Override
    public boolean fileExist() {
        return locationOfFileInClassPath != null;
    }

}
