package com.coursemngsys.coursemanagementsystem.hibernateControllers;

import com.coursemngsys.coursemanagementsystem.Model.Course;
import com.coursemngsys.coursemanagementsystem.Model.Moderator;
import com.coursemngsys.coursemanagementsystem.Model.Student;
import com.coursemngsys.coursemanagementsystem.Model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

public class UserHibernateController {
    private SessionFactory factory = null;

    public UserHibernateController(SessionFactory factory) {
        this.factory = factory;
    }

    private Session getSession() {
        return factory.openSession();
    }

    public void createUser(User user){
        Session session = null;
        try{
            session = getSession();
            Transaction tx = session.beginTransaction();
            session.save(user);
            tx.commit();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if(session != null){
                session.close();
            }
        }
    }

    public void editUser(User user){
        Session session = null;
        try{
            session = getSession();
            Transaction tx = session.beginTransaction();
            session.merge(user);
            //session.update(user);
            tx.commit();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if(session != null){
                session.close();
            }
        }
    }

    public void removeUser(int id){
        Session session = null;
        User user = null;
        try{
            session = getSession();
            Transaction tx = session.beginTransaction();
            user = session.getReference(User.class, id);
            session.remove(user);
            tx.commit();
        } catch (Exception e){
            System.out.println("No such user by given ID");
        } finally {
            if(session != null){
                session.close();
            }
        }
    }

    public User getUserById(int id){
        Session session = null;
        User user = null;
        try{
            session = getSession();
            Transaction tx = session.beginTransaction();
            user = session.find(User.class, id);
            tx.commit();
        } catch (Exception e){
            System.out.println("No such user by given ID");
        }
        return user;
    }

    public List<User> getAllUsers(){
        Session session = null;
        try {
            session = getSession();
            CriteriaQuery<Object> query = session.getCriteriaBuilder().createQuery();
            query.select(query.from(User.class));
            Query q = session.createQuery(query);
            return q.getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(session != null){
                session.close();
            }
        }
        return new ArrayList<>();
    }

    public List<Student> getAllStudents(){
        Session session = null;
        try {
            session = getSession();
            CriteriaQuery<Object> query = session.getCriteriaBuilder().createQuery();
            query.select(query.from(Student.class));
            Query q = session.createQuery(query);
            return q.getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(session != null){
                session.close();
            }
        }
        return new ArrayList<>();
    }

    public List<Moderator> getAllModerators(){
        Session session = null;
        try {
            session = getSession();
            CriteriaQuery<Object> query = session.getCriteriaBuilder().createQuery();
            query.select(query.from(Moderator.class));
            Query q = session.createQuery(query);
            return q.getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(session != null){
                session.close();
            }
        }
        return new ArrayList<>();
    }
}
