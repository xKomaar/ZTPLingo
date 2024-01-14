package pl.ztplingo.database;

import pl.ztplingo.model.Sentence;
import pl.ztplingo.model.User;
import pl.ztplingo.model.Word;

import java.util.List;

public interface Database {
    void saveUser(User user);
    void saveWord(Word word);
    void saveSentence(Sentence sentence);
    User getUserById(Integer id);
    User getUserByUsername(String username);
    Word getWordById(Integer id);
    Sentence getSentenceById(Integer id);
    void updateUser(User user);
    void updateWord(Word word);
    void updateSentence(Sentence sentence);
    void deleteUser(User user);
    void deleteWord(Word word);
    void deleteSentence(Sentence sentence);
    List<Word> getWordsByUser(User user);
    List<Sentence> getSentencesByUser(User user);
}
