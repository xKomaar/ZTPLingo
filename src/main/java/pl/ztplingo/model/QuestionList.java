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
        int bound;
        //sprawdzamy, czy zdań jest mniej, niż połowa żądanej ilości pytań
        //jeżeli tak, to musimy dodać więcej słów (np 6 pytań, a w bazie są tylko 2 zdania - 4 slowa, 2 zdania)
        if(sentences.size() < questionQuantity-questionQuantity/2) {
            bound = questionQuantity/2 + questionQuantity-questionQuantity/2 - sentences.size();
        } else {
            bound = questionQuantity/2;
        }
        //dodajemy losowe (lub wszystkie) słowa
        for(int i=0; i<bound && !words.isEmpty(); i++) {
            int randomIndex = random.nextInt(words.size());
            Word randomWord = words.get(randomIndex);
            WordDecorator wordDecorator = new WordDecorator(randomWord);
            phraseDecoratorList.add(wordDecorator);
            words.remove(randomWord);
        }
        //teraz pętla wykona się tyle razy, ile pozostało fraz do wpisania (od obecnej wielkosci listy fraz, do żądanej ilosci pytan)
        bound = phraseDecoratorList.size();
        //dodajemy losowe (lub wszystkie) zdania
        for(int i=bound; i<questionQuantity && !sentences.isEmpty(); i++) {
            int randomIndex = random.nextInt(sentences.size());
            Sentence randomSentence = sentences.get(randomIndex);
            SentenceDecorator sentenceDecorator = new SentenceDecorator(randomSentence);
            phraseDecoratorList.add(sentenceDecorator);
            sentences.remove(randomSentence);
        }
        //mieszamy listę
        Collections.shuffle(phraseDecoratorList);
    }

    public QuestionListIterator createQuestionListIterator() {
        return new QuestionListIterator(this);
    }

    public List<PhraseDecorator> getPhraseDecoratorList() {
        return phraseDecoratorList;
    }
}
