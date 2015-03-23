package org.aconex.phone.criteria;

import org.aconex.phone.domain.DictionaryWord;

import java.util.List;

/**
 * Created by Lenovo on 22/03/2015.
 */
public interface Criteria {

    boolean match(DictionaryWord dictionaryWord);

    List<DictionaryWord> matchList(List<DictionaryWord> dictionaryWords);
}
