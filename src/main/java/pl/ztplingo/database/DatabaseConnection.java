package pl.ztplingo.database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import pl.ztplingo.model.Sentence;
import pl.ztplingo.model.User;
import pl.ztplingo.model.Word;

public class DatabaseConnection {

    private static DatabaseConnection instance;

    private final SessionFactory sessionFactory;

    private DatabaseConnection() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();

        Metadata metadata = new MetadataSources(ssr).getMetadataBuilder().build();
        sessionFactory = metadata.getSessionFactoryBuilder().build();
    }

    protected static DatabaseConnection getInstance() {
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

    protected void saveUser(User user) {
        saveEntity(user);
    }

    protected void saveWord(Word word) {
        saveEntity(word);
    }

    protected void saveSentence(Sentence sentence) {
        saveEntity(sentence);
    }

    protected User getUser(Integer id)
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

    protected Word getWord(Integer id)
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

    protected Sentence getSentence(Integer id)
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

    protected void updateUser(User user)
    {
        updateEntity(user);
    }

    protected void updateWord(Word word)
    {
        updateEntity(word);
    }

    protected void updateSentence(Sentence sentence)
    {
        updateEntity(sentence);
    }

    protected void deleteUser(Integer id)
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

    protected void deleteWord(Integer id)
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

    protected void deleteSentence(Integer id)
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
