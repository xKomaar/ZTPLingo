package pl.ztplingo.database;

import pl.ztplingo.model.Sentence;
import pl.ztplingo.model.User;
import pl.ztplingo.model.Word;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DatabaseProxy implements Database {

    private final DatabaseConnection databaseConnection;

    public DatabaseProxy() {
        this.databaseConnection = DatabaseConnection.getInstance();
    }

    public Integer saveUser(User user) {
        if(user.getUsername().length() < 3) {
            return -1;
        }
        User u = getUserByUsername(user.getUsername());
        if(u == null) {
            return databaseConnection.saveUser(user);
        } else {
            return -2;
        }
    }

    public Integer saveWord(Word word) {
        Pattern pattern = Pattern.compile("[A-Za-złąćęóżźńś]+", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(word.getPolish());
        if(!matcher.matches()) {
            return -1;
        }
        matcher = pattern.matcher(word.getEnglish());
        if(!matcher.matches()) {
            return -2;
        }
        return databaseConnection.saveWord(word);
    }
    
    public Integer saveSentence(Sentence sentence) {
        if(!sentence.getPolish().contains(" ")) {
            return -1;
        }
        if(!sentence.getEnglish().contains(" ")) {
            return -2;
        }
        Pattern pattern = Pattern.compile("[A-Za-złąćęóżźńś]+\\s+,*\\.*\\?*;*!*", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(sentence.getPolish());
        if(matcher.matches()) {
            return -1;
        }
        matcher = pattern.matcher(sentence.getEnglish());
        if(matcher.matches()) {
            return -2;
        }
        return databaseConnection.saveSentence(sentence);
    }

    public User getUserById(Integer id) {
        return databaseConnection.getUserById(id);
    }

    public User getUserByUsername(String username) {
        return databaseConnection.getUserByUsername(username);
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
