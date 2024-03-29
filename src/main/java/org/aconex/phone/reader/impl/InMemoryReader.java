package org.aconex.phone.reader.impl;

import org.aconex.phone.reader.iterator.AbstractIterator;
import org.aconex.phone.reader.Reader;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zainul Franciscus on 14/03/2015.
 */
public class InMemoryReader implements Reader {

    List<String> words = new ArrayList<String>();


    @Override
    public void sourceOfData(String datasource) {
        words.add(datasource);

    }

    @Override
    public AbstractIterator iterator() throws Exception {
        return new AbstractIterator() {

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
