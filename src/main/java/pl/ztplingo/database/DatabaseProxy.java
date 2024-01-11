package pl.ztplingo.database;

import pl.ztplingo.model.Sentence;
import pl.ztplingo.model.User;
import pl.ztplingo.model.Word;

import java.util.List;

//TODO: Dodac rzeczy typu w save czy user exists i sprawdzanie czy slowo jest slowem, a zdanie zdaniem
public class DatabaseProxy implements Database {

    private final DatabaseConnection databaseConnection;

    public DatabaseProxy() {
        this.databaseConnection = DatabaseConnection.getInstance();
    }

    public void saveUser(User user) {
        databaseConnection.saveUser(user);
    }

    public void saveWord(Word word) {
        databaseConnection.saveWord(word);
    }
    
    public void saveSentence(Sentence sentence) {
        databaseConnection.saveSentence(sentence);
    }

    public User getUserById(Integer id) {
        return databaseConnection.getUserById(id);
    }

    public Word getWordById(Integer id) {
        return databaseConnection.getWordById(id);
    }

    public Sentence getSentenceById(Integer id) {
        return databaseConnection.getSentenceById(id);
    }

    public List<Word> getWordsByUser(User user) {
        return databaseConnection.getWordsByUser(user);
    }

    public List<Sentence> getSentencesByUser(User user) {
        return databaseConnection.getSentencesByUser(user);
    }

    public void updateUser(User user) {
        databaseConnection.updateUser(user);
    }
    
    public void updateWord(Word word) {
        databaseConnection.updateWord(word);
    }
    
    public void updateSentence(Sentence sentence) {
        databaseConnection.updateSentence(sentence);
    }
    
    public void deleteUser(User user) {
        databaseConnection.deleteUser(user);
    }
    
    public void deleteWord(Word word) {
        databaseConnection.deleteWord(word);
    }

    public void deleteSentence(Sentence sentence) {
        databaseConnection.deleteSentence(sentence);
    }
}
