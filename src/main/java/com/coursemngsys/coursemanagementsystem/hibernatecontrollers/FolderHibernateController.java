package com.coursemngsys.coursemanagementsystem.hibernatecontrollers;

import com.coursemngsys.coursemanagementsystem.Model.Folder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class FolderHibernateController {
    private SessionFactory factory = null;

    public FolderHibernateController(SessionFactory factory) {
        this.factory = factory;
    }

    private Session getSession() {
        return factory.openSession();
    }

    public void createFolder(Folder folder){
        Session session = null;
        try{
            session = getSession();
            Transaction tx = session.beginTransaction();
            session.save(folder);
            tx.commit();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if(session != null){
                session.close();
            }
        }
    }

    public void editFolder(Folder folder){
        Session session = null;
        try{
            session = getSession();
            Transaction tx = session.beginTransaction();
            session.update(folder);
            tx.commit();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if(session != null){
                session.close();
            }
        }
    }

    public void removeFolder(int id){
        Session session = null;
        try{
            session = getSession();
            Transaction tx = session.beginTransaction();
            Folder folder = null;
            try{
                folder = session.getReference(Folder.class, id);
                folder.getId();
            } catch (Exception e){
                System.out.println("No such folder by given ID");
            }
            session.remove(folder);
            tx.commit();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if(session != null){
                session.close();
            }
        }
    }

    public Folder getFolderById(int id){
        Session session = null;
        Folder folder = null;
        try{
            session = getSession();
            Transaction tx = session.beginTransaction();
            folder = session.find(Folder.class, id);
            folder.getId();
            tx.commit();
        } catch (Exception e){
            System.out.println("No such folder by given ID");
        }
        return folder;
    }

    public List<Folder> getAllFolders(boolean all, int resMax, int resFirst){
        Session session = null;
        try {
            session = getSession();
            CriteriaQuery<Object> query = session.getCriteriaBuilder().createQuery();
            query.select(query.from(Folder.class));
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
