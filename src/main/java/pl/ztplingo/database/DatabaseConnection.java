package pl.ztplingo.database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import pl.ztplingo.model.Sentence;
import pl.ztplingo.model.User;
import pl.ztplingo.model.Word;

import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection implements Database {

    private static DatabaseConnection instance;

    private final SessionFactory sessionFactory;

    private DatabaseConnection() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();

        Metadata metadata = new MetadataSources(ssr).getMetadataBuilder().build();
        sessionFactory = metadata.getSessionFactoryBuilder().build();
    }

    public static DatabaseConnection getInstance() {
        if(instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    private void saveEntity(Object o) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.persist(o);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(session != null) session.close();
        }
    }

    public void saveUser(User user) {
        saveEntity(user);
    }

    public void saveWord(Word word) {
        saveEntity(word);
    }

    public void saveSentence(Sentence sentence) {
        saveEntity(sentence);
    }

    public User getUserById(Integer id)
    {
        Session session = null;
        User user = null;
        try {
            session = sessionFactory.openSession();
            user = session.get(User.class, id);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if(session != null) session.close();
        }

        return user;
    }

    public User getUserByUsername(String username)
    {
        Session session = null;
        User user = null;
        try {
            session = sessionFactory.openSession();

            Query query = session.createQuery("FROM User U WHERE U.username = :username");
            query.setParameter("username", username);
            if(!query.list().isEmpty()) {
                user = (User) query.list().get(0);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if(session != null) session.close();
        }

        return user;
    }

    public Word getWordById(Integer id)
    {
        Session session = null;
        Word word = null;
        try {
            session = sessionFactory.openSession();
            word = session.get(Word.class, id);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if(session != null) session.close();
        }

        return word;
    }

    public Sentence getSentenceById(Integer id)
    {
        Session session = null;
        Sentence sentence = null;
        try {
            session = sessionFactory.openSession();
            sentence = session.get(Sentence.class, id);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if(session != null) session.close();
        }

        return sentence;
    }

    public List<Word> getWordsByUser(User user) {
        Session session = null;
        List<Word> words = new ArrayList<>();

        try {
            session = sessionFactory.openSession();

            Query query = session.createQuery("FROM Word W WHERE W.user = :user");
            query.setParameter("user", user);
            words = query.list();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if(session != null) session.close();
        }

        return words;
    }
    
    public List<Sentence> getSentencesByUser(User user) {
        Session session = null;
        List<Sentence> sentences = new ArrayList<>();
        try {
            session = sessionFactory.openSession();

            Query query = session.createQuery("FROM Sentence S WHERE S.user = :user");
            query.setParameter("user", user);
            sentences = query.list();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if(session != null) session.close();
        }
        return sentences;
    }

    private void updateEntity(Object o) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            session.merge(o);

            transaction.commit();
        } catch (Exception e){
            e.printStackTrace();
            if(transaction != null) transaction.rollback();
        } finally {
            if(session != null) session.close();
        }
    }

    public void updateUser(User user)
    {
        updateEntity(user);
    }

    public void updateWord(Word word)
    {
        updateEntity(word);
    }

    public void updateSentence(Sentence sentence)
    {
        updateEntity(sentence);
    }

    public void deleteUser(User u)
    {
        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            User user = session.get(User.class, u.getId());

            session.remove(user);

            transaction.commit();
        } catch (Exception e){
            e.printStackTrace();
            if(transaction != null) transaction.rollback();
        } finally {
            if(session != null) session.close();
        }
    }

    public void deleteWord(Word w)
    {
        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            Word word = session.get(Word.class, w.getId());

            session.remove(word);

            transaction.commit();
        } catch (Exception e){
            e.printStackTrace();
            if(transaction != null) transaction.rollback();
        } finally {
            if(session != null) session.close();
        }
    }

    public void deleteSentence(Sentence s)
    {
        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            Sentence sentence = session.get(Sentence.class, s.getId());

            session.remove(sentence);

            transaction.commit();
        } catch (Exception e){
            e.printStackTrace();
            if(transaction != null) transaction.rollback();
        } finally {
            if(session != null) session.close();
        }
    }
}
