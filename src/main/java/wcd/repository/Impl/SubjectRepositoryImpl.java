package wcd.repository.Impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import wcd.entity.Subject;
import wcd.util.HibernateUtil;

import java.util.List;

public class SubjectRepositoryImpl implements SubjectRepository {
    @Override
    public List<Subject> findAll() {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from subject",Subject.class).list();
        }
    }

    @Override
    public void save(Subject subject) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(subject);
            transaction.commit();
        }catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public Subject findById(int id) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Subject.class,id);
        }
    }

    @Override
    public void update(Subject subject) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.getTransaction();
            session.update(subject);
            transaction.commit();
        }catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
           Subject subject = session.get(Subject.class,id);
            if(subject != null){
                session.delete(subject);
                transaction.commit();
            }
        }catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<Subject> findByName(String name) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.createQuery("from Subject s where s.name LIKE :name", Subject.class)
                    .setParameter("name",'%'+name+'%')
                    .list();
        }
    }
}
