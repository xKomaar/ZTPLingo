package pl.ztplingo.model;

import pl.ztplingo.settings.Difficulty;
import pl.ztplingo.settings.ExerciseMode;
import pl.ztplingo.settings.Language;
import pl.ztplingo.decorator.PhraseDecorator;
import pl.ztplingo.iterator.QuestionListIterator;
import pl.ztplingo.snapshot.QuizSessionSnapshot;

import java.util.List;

public class QuizSession {

    private QuestionListIterator questionListIterator;
    private ExerciseMode exerciseMode;
    private Language language;
    private Difficulty difficulty;
    private int currentPoints;
    private boolean finished;
    private PhraseDecorator currentPhrase;
    private int questionQuantity;

    public QuizSession(Language language, Difficulty difficulty, QuestionList questionList, ExerciseMode exerciseMode) {
        this.questionListIterator = questionList.createQuestionListIterator();
        this.language = language;
        this.difficulty = difficulty;
        this.exerciseMode = exerciseMode;
        this.finished = false;
        this.currentPoints = 0;
        this.questionQuantity = 0;
    }

    public void loadNextPhrase() {
        if(questionListIterator.hasNext()) {
            currentPhrase = questionListIterator.next();
            if(!questionListIterator.hasNext()) {
                finished = true;
            }
            questionQuantity++;
        } else {
            finished = true;
        }
    }

    public boolean isFinished() {
        return finished;
    }

    public boolean checkAnswer(String answer) {
        if (answer.equalsIgnoreCase(getCurrentAnswer())) {
            currentPoints++;
            return true;
        }
        else return false;
    }

    public String getCurrentQuestion() {
        if(language == Language.ENGLISH_TO_POLISH) {
            return currentPhrase.getEnglish();
        } else {
            return currentPhrase.getPolish();
        }
    }

    public String getCurrentAnswer() {
        if(language == Language.ENGLISH_TO_POLISH) {
            return currentPhrase.getPolish();
        } else {
            return currentPhrase.getEnglish();
        }
    }

    public PhraseDecorator getCurrentPhrase() {
        return currentPhrase;
    }

    public List<String> getShuffledAnswer() {
        currentPhrase.shuffle(language);
        return currentPhrase.getShuffledAnswer();
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public int getCurrentPoints() {
        return currentPoints;
    }

    public int getQuestionQuantity() {
        return questionQuantity;
    }

    public ExerciseMode getExerciseMode() {
        return exerciseMode;
    }

    public QuizSessionSnapshot createSnapshot() {
        return new QuizSessionSnapshot(questionListIterator, exerciseMode,
                language, difficulty, currentPoints, finished, currentPhrase);
    }



    public void setQuestionListIterator(QuestionListIterator questionListIterator) {
        this.questionListIterator = questionListIterator;
    }

    public void setExerciseMode(ExerciseMode exerciseMode) {
        this.exerciseMode = exerciseMode;
    }

    public void setLanguage(Language language) {
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
