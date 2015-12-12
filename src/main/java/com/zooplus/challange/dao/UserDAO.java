package com.zooplus.challange.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zooplus.challange.hibernate.HibernateConnector;
import com.zooplus.challange.pojo.User;

public class UserDAO {
    public List<User> listUsers() {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            Query query = session.createQuery("from User u");
            List queryList = query.list();
            if (queryList != null && queryList.isEmpty()) {
                return null;
            }
            System.out.println("list " + queryList);
            return (List<User>) queryList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public User findUserById(int id) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            Query query = session.createQuery("from User s where s.id = :id");
            query.setParameter("id", id);
            List queryList = query.list();
            if (queryList != null && queryList.isEmpty()) {
                return null;
            }
            return (User) queryList.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public User findUserByEmail(String email) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            Query query = session.createQuery("from User s where s.email = :email");
            query.setParameter("email", email);
            List queryList = query.list();
            if (queryList != null && queryList.isEmpty()) {
                return null;
            }
            return (User) queryList.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void updateUser(User student) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            session.saveOrUpdate(student);
            session.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public User addUser(User student) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            System.out.println("session : " + session);
            transaction = session.beginTransaction();
            session.save(student);
            transaction.commit();
            return student;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void deleteUser(int id) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            Transaction beginTransaction = session.beginTransaction();
            Query createQuery = session.createQuery("delete from User s where s.id =:id");
            createQuery.setParameter("id", id);
            createQuery.executeUpdate();
            beginTransaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
