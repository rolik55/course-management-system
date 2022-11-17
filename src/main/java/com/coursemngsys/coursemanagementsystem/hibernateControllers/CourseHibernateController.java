package com.coursemngsys.coursemanagementsystem.hibernateControllers;

import com.coursemngsys.coursemanagementsystem.Model.Course;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class CourseHibernateController {
    private SessionFactory factory = null;

    public CourseHibernateController(SessionFactory factory) {
        this.factory = factory;
    }

    private Session getSession() {
        return factory.openSession();
    }

    public void createCourse(Course course){
        Session session = null;
        try{
            session = getSession();
            Transaction tx = session.beginTransaction();
            session.save(course);
            tx.commit();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if(session != null){
                session.close();
            }
        }
    }

    public void editCourse(Course course){
        Session session = null;
        try{
            session = getSession();
            Transaction tx = session.beginTransaction();
            session.merge(course);
            tx.commit();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if(session != null){
                session.close();
            }
        }
    }

    public void removeCourse(int id){
        Session session = null;
        try{
            session = getSession();
            Transaction tx = session.beginTransaction();
            Course course = null;
            try{
                course = session.getReference(Course.class, id);
                course.getId();
            } catch (Exception e){
                System.out.println("No such user by given ID");
            }
            session.remove(course);
            tx.commit();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if(session != null){
                session.close();
            }
        }
    }

    public Course getCourseById(int id){
        Session session = null;
        Course course = null;
        try{
            session = getSession();
            Transaction tx = session.beginTransaction();
            course = session.find(Course.class, id);
            course.getId();
            tx.commit();
        } catch (Exception e){
            System.out.println("No such user by given ID");
        }
        return course;
    }

    public List<Course> getAllCourses(boolean all, int resMax, int resFirst){
        Session session = null;
        try {
            session = getSession();
            CriteriaQuery<Object> query = session.getCriteriaBuilder().createQuery();
            query.select(query.from(Course.class));
            Query q = session.createQuery(query);

            if (!all) {
                q.setMaxResults(resMax);
                q.setFirstResult(resFirst);
            }
            return q.getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(session != null){
                session.close();
            }
        }
        return null;
    }

}
