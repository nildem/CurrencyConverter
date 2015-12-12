package com.zooplus.challange.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateConnector {
    private static HibernateConnector instance;
    private Configuration cfg;
    private SessionFactory sessionFactory;

    private HibernateConnector() throws HibernateException {
        cfg = new Configuration().configure();
        sessionFactory = cfg.buildSessionFactory();
    }

    public static synchronized HibernateConnector getInstance() throws HibernateException {
        if (instance == null) {
            instance = new HibernateConnector();
        }
        return instance;
    }

    public Session getSession() throws HibernateException {
        Session session = sessionFactory.openSession();
        if (!session.isConnected()) {
            this.reconnect();
        }
        return session;
    }

    private void reconnect() throws HibernateException {
        this.sessionFactory = cfg.buildSessionFactory();
    }
}
