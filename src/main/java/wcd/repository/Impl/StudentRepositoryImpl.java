package wcd.repository.Impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import wcd.entity.Student;
import wcd.repository.StudentRepository;
import wcd.util.HibernateUtil;

import java.util.List;

public class StudentRepositoryImpl implements StudentRepository {
    @Override
    public List<Student> findAll() {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Student ",Student.class).list();
        }
    }

    @Override
    public void save(Student student) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(student);
            transaction.commit();
        }catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public Student findById(int id) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Student.class,id);
        }
    }

    @Override
    public void update(Student student) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.getTransaction();
            session.update(Student);
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
            Student student = session.get(Student.class,id);
            if(student!= null){
                session.delete(student);
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
    public List<Student> findByName(String name) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.createQuery("from Student s where s.name LIKE :name", Student.class)
                    .setParameter("name",'%'+name+'%')
                    .list();
        }
    }

}
