package pl.ztplingo.databaseSingleton;

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

import java.util.List;

public class DatabaseConnection {

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

    public void saveUser(User user) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(session != null) session.close();
        }
    }

    public void saveWord(Word word) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.persist(word);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(session != null) session.close();
        }
    }

    public void saveSentence(Sentence sentence) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.persist(sentence);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(session != null) session.close();
        }
    }

    public User getUser(Integer id)
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

    public Word getWord(Integer id)
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

    public Sentence getSentence(Integer id)
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

    public void deleteUser(Integer id)
    {
        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            User user = session.get(User.class, id);

            session.remove(user);

            transaction.commit();
        } catch (Exception e){
            e.printStackTrace();
            if(transaction != null) transaction.rollback();
        } finally {
            if(session != null) session.close();
        }
    }

    public void deleteWord(Integer id)
    {
        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            Word word = session.get(Word.class, id);

            session.remove(word);

            transaction.commit();
        } catch (Exception e){
            e.printStackTrace();
            if(transaction != null) transaction.rollback();
        } finally {
            if(session != null) session.close();
        }
    }

    public void deleteSentence(Integer id)
    {
        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            Sentence sentence = session.get(Sentence.class, id);

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
