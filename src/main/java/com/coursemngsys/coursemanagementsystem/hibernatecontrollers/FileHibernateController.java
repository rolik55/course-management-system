package com.coursemngsys.coursemanagementsystem.hibernatecontrollers;

import com.coursemngsys.coursemanagementsystem.Model.File;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class FileHibernateController {
    private SessionFactory factory = null;

    public FileHibernateController(SessionFactory factory) {
        this.factory = factory;
    }

    private Session getSession() {
        return factory.openSession();
    }

    public void createFile(File file){
        Session session = null;
        try{
            session = getSession();
            Transaction tx = session.beginTransaction();
            session.save(file);
            tx.commit();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if(session != null){
                session.close();
            }
        }
    }

    public void editFile(File file){
        Session session = null;
        try{
            session = getSession();
            Transaction tx = session.beginTransaction();
            session.update(file);
            tx.commit();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if(session != null){
                session.close();
            }
        }
    }

    public void removeFile(int id){
        Session session = null;
        try{
            session = getSession();
            Transaction tx = session.beginTransaction();
            File file = null;
            try{
                file = session.getReference(File.class, id);
                file.getId();
            } catch (Exception e){
                System.out.println("No such user by given ID");
            }
            session.remove(file);
            tx.commit();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if(session != null){
                session.close();
            }
        }
    }

    public File getFileById(int id){
        Session session = null;
        File file = null;
        try{
            session = getSession();
            Transaction tx = session.beginTransaction();
            file = session.find(File.class, id);
            file.getId();
            tx.commit();
        } catch (Exception e){
            System.out.println("No such user by given ID");
        }
        return file;
    }

    public List<File> getAllFiles(boolean all, int resMax, int resFirst){
        Session session = null;
        try {
            session = getSession();
            CriteriaQuery<Object> query = session.getCriteriaBuilder().createQuery();
            query.select(query.from(File.class));
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
