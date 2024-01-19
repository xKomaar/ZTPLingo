package pl.ztplingo.snapshot;

import pl.ztplingo.settings.Difficulty;
import pl.ztplingo.settings.ExerciseMode;
import pl.ztplingo.settings.Language;
import pl.ztplingo.decorator.PhraseDecorator;
import pl.ztplingo.iterator.QuestionListIterator;
import pl.ztplingo.model.QuizSession;

public class QuizSessionSnapshot {

    private QuestionListIterator questionListIterator;
    private ExerciseMode exerciseMode;
    private Language language;
    private Difficulty difficulty;
    private int currentPoints;
    private boolean finished;
    private PhraseDecorator currentPhrase;

    public QuizSessionSnapshot(QuestionListIterator questionListIterator, ExerciseMode exerciseMode,
                               Language language, Difficulty difficulty, int currentPoints, boolean finished, PhraseDecorator currentPhrase) {
        this.questionListIterator = questionListIterator;
        this.exerciseMode = exerciseMode;
        this.language = language;
        this.difficulty = difficulty;
        this.currentPoints = currentPoints;
        this.finished = finished;
        this.currentPhrase = currentPhrase;
    }

    public void restore(QuizSession quizSession) {
        quizSession.setQuestionListIterator(questionListIterator);
        quizSession.setExerciseMode(exerciseMode);
        quizSession.setLanguage(language);
        quizSession.setDifficulty(difficulty);
        quizSession.setCurrentPoints(currentPoints);
        quizSession.setFinished(finished);
        quizSession.setCurrentPhrase(currentPhrase);
    }
}
