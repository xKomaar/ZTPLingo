package pl.ztplingo.model;

import pl.ztplingo.decorator.PhraseDecorator;
import pl.ztplingo.decorator.SentenceDecorator;
import pl.ztplingo.decorator.WordDecorator;
import pl.ztplingo.iterator.QuestionListIterator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class QuestionList {

    private List<PhraseDecorator> phraseDecoratorList;

    public QuestionList(List<Word> words, List<Sentence> sentences, int questionQuantity) {
        phraseDecoratorList = new ArrayList<>();
        Random random = new Random();
        for(int i=0; i<questionQuantity/2 || i<words.size(); i++) {
            int randomIndex = random.nextInt(words.size());
            Word randomWord = words.get(randomIndex);
            WordDecorator wordDecorator = new WordDecorator(randomWord);
            phraseDecoratorList.add(wordDecorator);
            words.remove(randomWord);
        }
        for(int i=questionQuantity/2; i<questionQuantity || i<sentences.size(); i++) {
            int randomIndex = random.nextInt(sentences.size());
            Sentence randomSentence = sentences.get(randomIndex);
            SentenceDecorator sentenceDecorator = new SentenceDecorator(randomSentence);
            phraseDecoratorList.add(sentenceDecorator);
            sentences.remove(randomSentence);
        }
        Collections.shuffle(phraseDecoratorList);
    }

    public QuestionListIterator createQuestionListIterator() {
        return new QuestionListIterator(this);
    }

    public List<PhraseDecorator> getPhraseDecoratorList() {
        return phraseDecoratorList;
    }
}
