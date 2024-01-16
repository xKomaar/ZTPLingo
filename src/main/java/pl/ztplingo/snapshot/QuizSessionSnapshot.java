package pl.ztplingo.snapshot;

import pl.ztplingo.Difficulty;
import pl.ztplingo.LanguageState;
import pl.ztplingo.decorator.PhraseDecorator;
import pl.ztplingo.exerciseState.ExerciseState;
import pl.ztplingo.iterator.QuestionListIterator;
import pl.ztplingo.model.QuizSession;

public class QuizSessionSnapshot {

    private QuestionListIterator questionListIterator;
    private ExerciseState exerciseState;
    private LanguageState language;
    private Difficulty difficulty;
    private int currentPoints;
    private boolean finished;
    private PhraseDecorator currentPhrase;

    public QuizSessionSnapshot(QuestionListIterator questionListIterator, ExerciseState exerciseState,
                               LanguageState language, Difficulty difficulty, int currentPoints, boolean finished, PhraseDecorator currentPhrase) {
        this.questionListIterator = questionListIterator;
        this.exerciseState = exerciseState;
        this.language = language;
        this.difficulty = difficulty;
        this.currentPoints = currentPoints;
        this.finished = finished;
        this.currentPhrase = currentPhrase;
    }

    public void restore(QuizSession quizSession) {
        quizSession.setQuestionListIterator(questionListIterator);
        quizSession.setExerciseState(exerciseState);
        quizSession.setLanguage(language);
        quizSession.setDifficulty(difficulty);
        quizSession.setCurrentPoints(currentPoints);
        quizSession.setFinished(finished);
        quizSession.setCurrentPhrase(currentPhrase);
    }
}
