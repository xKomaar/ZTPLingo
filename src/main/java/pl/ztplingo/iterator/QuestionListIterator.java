package pl.ztplingo.iterator;

import pl.ztplingo.decorator.PhraseDecorator;
import pl.ztplingo.model.QuestionList;

import java.util.Iterator;

public class QuestionListIterator implements Iterator<PhraseDecorator> {

    private QuestionList questionList;
    private int currentIndex;

    public QuestionListIterator(QuestionList questionList) {
        this.questionList = questionList;
        this.currentIndex = 0;
    }

    public boolean hasNext() {
        return currentIndex < questionList.getPhraseDecoratorList().size();
    }

    public PhraseDecorator next() {
        if(!hasNext()) {
            return null;
        }
        currentIndex++;
        return questionList.getPhraseDecoratorList().get(currentIndex);
    }
}
