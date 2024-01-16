package pl.ztplingo.model;

import pl.ztplingo.Difficulty;
import pl.ztplingo.LanguageState;
import pl.ztplingo.decorator.PhraseDecorator;
import pl.ztplingo.exerciseState.ExerciseState;
import pl.ztplingo.iterator.QuestionListIterator;
import pl.ztplingo.snapshot.QuizSessionSnapshot;

import java.util.List;

public class QuizSession {

    private QuestionListIterator questionListIterator;
    private ExerciseState exerciseState;
    private LanguageState language;
    private Difficulty difficulty;
    private int currentPoints;
    private boolean finished;
    private PhraseDecorator currentPhrase;

    public QuizSession(LanguageState language, Difficulty difficulty, QuestionList questionList) {
        this.questionListIterator = questionList.createQuestionListIterator();
        this.language = language;
        this.difficulty = difficulty;
        this.finished = false;
        this.currentPoints = 0;
    }

    public void loadNextPhrase() {
        currentPhrase = questionListIterator.next();
        if(!questionListIterator.hasNext()) {
            setFinishedToTrue();
        }
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinishedToTrue() {
        finished = true;
    }

    public boolean checkAnswer(String answer) {
        if (answer.equalsIgnoreCase(getCurrentAnswer())) {
            currentPoints++;
            return true;
        }
        else return false;
    }

    public String getCurrentQuestion() {
        if(language == LanguageState.ENGLISH_TO_POLISH) {
            return currentPhrase.getEnglish();
        } else {
            return currentPhrase.getPolish();
        }
    }

    public String getCurrentAnswer() {
        if(language == LanguageState.ENGLISH_TO_POLISH) {
            return currentPhrase.getPolish();
        } else {
            return currentPhrase.getEnglish();
        }
    }

    public List<String> getShuffledAnswer() {
        currentPhrase.shuffle(language);
        return currentPhrase.getShuffledAnswer();
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public QuizSessionSnapshot createSnapshot() {
        return new QuizSessionSnapshot(questionListIterator, exerciseState,
                language, difficulty, currentPoints, finished, currentPhrase);
    }

    public void setQuestionListIterator(QuestionListIterator questionListIterator) {
        this.questionListIterator = questionListIterator;
    }

    public void setExerciseState(ExerciseState exerciseState) {
        this.exerciseState = exerciseState;
    }

    public void setLanguage(LanguageState language) {
        this.language = language;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public void setCurrentPoints(int currentPoints) {
        this.currentPoints = currentPoints;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public void setCurrentPhrase(PhraseDecorator currentPhrase) {
        this.currentPhrase = currentPhrase;
    }
}
