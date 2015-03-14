package org.aconex.phone.datasource.impl;

import org.aconex.phone.datasource.FileBasedDictionaryProvider;

import java.io.File;

/**
 * Created by Lenovo on 11/03/2015.
 */
public class FileDictionaryProvider extends FileBasedDictionaryProvider {

    private File file;

    @Override
    public File file() {
        return file;
    }

    @Override
    public boolean fileExist() {
        return file.exists();
    }

    @Override
    public void sourceOfData(String f) {
        file = new File(f);
    }
}
