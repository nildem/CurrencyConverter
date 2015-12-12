package com.zooplus.challange.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zooplus.challange.hibernate.HibernateConnector;
import com.zooplus.challange.pojo.ExchangeRate;

public class ExchangeRateDAO {
    public List<ExchangeRate> listRates(int last) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            Query query = session.createQuery("from ExchangeRate u ORDER BY u.convertionDate DESC");
            List queryList = query.setMaxResults(last).list();
            if (queryList != null && queryList.isEmpty()) {
                return null;
            }
            return (List<ExchangeRate>) queryList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public List<ExchangeRate> listRatesBetweenDates(String currencyFrom, String currencyTo, Date start, Date end) {
        Session session = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            Query query = session
                    .createQuery("from ExchangeRate u where convertionDate between :from and :to and currencyFrom = :currencyFrom and currencyTo = :currencyTo order by convertionDate DESC");
            query.setParameter("from", start);
            query.setParameter("to", end);
            query.setParameter("currencyFrom", currencyFrom);
            query.setParameter("currencyTo", currencyTo);
            List queryList = query.list();
            if (queryList != null && queryList.isEmpty()) {
                return null;
            }
            return (List<ExchangeRate>) queryList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public ExchangeRate addExchangeRate(ExchangeRate rate) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateConnector.getInstance().getSession();
            transaction = session.beginTransaction();
            session.save(rate);
            transaction.commit();
            return rate;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
