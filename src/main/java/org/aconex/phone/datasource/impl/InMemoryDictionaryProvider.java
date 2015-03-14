package org.aconex.phone.datasource.impl;

import org.aconex.phone.datasource.DictionaryIterator;
import org.aconex.phone.datasource.DictionaryProvider;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.Reader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lenovo on 10/03/2015.
 */
public class InMemoryDictionaryProvider implements DictionaryProvider {

    List<String> words = new ArrayList<String>();


    @Override
    public void sourceOfData(String datasource) {
        words.add(datasource);

    }

    @Override
    public DictionaryIterator iterator() throws Exception {
        return new DictionaryIterator() {

            int index = 0;

            @Override
            public String next() {
                String word =  null;
                if(index < words.size()) {
                    word = words.get(index);
                    index += 1;

                }

                return word;
            }

            @Override
            public Closeable resourceToClose() {
                return null;
            }


        };
    }

}
