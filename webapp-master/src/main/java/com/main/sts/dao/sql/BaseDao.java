package com.main.sts.dao.sql;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Restrictions;
import org.hibernate.stat.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
@Component
public class BaseDao {

    @Autowired
    private SessionFactory sessionFactory;

    // public Session session = null;

    // public Session openSession() {
    // Session session = null;
    // if (session != null && session.isOpen())
    // return session;
    // else
    // session = sessionFactory.openSession();
    // return session;
    // }

    public Session openSession() {
//        Statistics statistics = sessionFactory.getStatistics();
//        statistics.setStatisticsEnabled(true);
//        statistics.logSummary();
        
        Session session = sessionFactory.openSession();
        return session;
    }

    public <T> List<T> getAllRecords(Class<T> clz) {
        return getAllRecords(clz, null);
    }
    // public Session getCurrentSession() {
    // Session session = sessionFactory.getCurrentSession();
    // return session;
    // }

    @SuppressWarnings("unchecked")
    // @Transactional
    public <T> List<T> getAllRecords(Class<T> clz, Order... orders) {
        Session session = null;
        Transaction tx = null;
        // Query query=null;
        // return openSession().createCriteria(clz).list();
        try {
            session = openSession();
            // tx = session.beginTransaction();
            Criteria c = session.createCriteria(clz);
            if (orders != null) {
                for (Order order : orders) {
                    c.addOrder(order);
                }
            }
            List ls = c.list();;
            // tx.commit();
            return ls;
        } catch (HibernateException e) {
            handleException(e, tx);
            return null;
        } finally {
            tx = null;
            closeSession(session);
        }
    }

    @SuppressWarnings("unchecked")
    // @Transactional
    public <T> BigInteger getCountOFRecords(String query) {
        Session session = null;
        Transaction tx = null;
        // Query query=null;
        // return openSession().createCriteria(clz).list();
        try {
            session = openSession();
            // tx = session.beginTransaction();
            Object count = session.createSQLQuery(query).uniqueResult();
            if(count instanceof Integer) {
                return BigInteger.valueOf((Integer)count);
            }
            return (BigInteger)count;

        } catch (HibernateException e) {
            handleException(e, tx);
            return null;
        } finally {
            tx = null;
            closeSession(session);
        }
    }
    @Transactional
    public boolean insertEntity(Object entity) {
        Session session = null;
        Transaction tx = null;
        session = openSession();
        try {
            tx = session.beginTransaction();
            session.save(entity);
            tx.commit();
            return true;
        } catch (HibernateException e) {
            handleException(e, tx);
            throw e;
        } finally {
            tx = null;
            closeSession(session);
        }
    }

    @Transactional
    public Serializable insertEntityAndGetId(Object entity) {
        Session session = null;
        Transaction tx = null;
        session = openSession();
        try {
            tx = session.beginTransaction();
            Serializable id = session.save(entity);
            tx.commit();
            return id;
        } catch (HibernateException e) {
            handleException(e, tx);
            throw e;
        } finally {
            tx = null;
            closeSession(session);
        }
    }

    @Transactional
    public boolean updateEntity(Object entity) {
        Session session = null;
        session = openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.merge(entity);
            tx.commit();
            return true;
        } catch (HibernateException e) {
            handleException(e, tx);
            return false;
        } finally {
            closeSession(session);
        }
    }

    @Transactional
    public <T> int updateEntitys(Class<T> clz, String queryStr, Map<Integer, Object> parameters) {
        Session session = null;
        Transaction tx = null;
        Query query = null;
        try {
            session = openSession();
            tx = session.beginTransaction();

            query = session.createQuery(queryStr);
            for (Integer k : parameters.keySet()) {
                query.setParameter(k, parameters.get(k));
            }
            System.out.println(query);
            int rowsAffected = query.executeUpdate();
            tx.commit();
            return rowsAffected;
        } catch (HibernateException e) {
            handleException(e, tx);
            return -1;
        } finally {
            tx = null;
            closeSession(session);
        }
    }

    @Transactional
    public <T> int updateEntities(Class<T> clz, String queryStr, Map<String, Object> parameters) {
        Session session = null;
        Transaction tx = null;
        Query query = null;
        try {
            session = openSession();
            tx = session.beginTransaction();
            query = session.createQuery(queryStr);
            for (String k : parameters.keySet()) {
                Object value = parameters.get(k);
                if (value instanceof Integer) {
                    query.setInteger(k, (Integer) value);
                } else if (value instanceof Long) {
                    query.setLong(k, (Long) value);
                } else if (value instanceof String) {
                    query.setString(k, (String) value);
                } else if (value instanceof Double) {
                    query.setDouble(k, (Double) value);
                } else if (value instanceof Timestamp) {
                    query.setTimestamp(k, (Timestamp) value);
                } else if (value instanceof Date) {
                    query.setDate(k, (Date) value);
                }
            }
            System.out.println(query);
            int rowsAffected = query.executeUpdate();
            tx.commit();
            return rowsAffected;
        } catch (HibernateException e) {
            handleException(e, tx);
            return -1;
        } finally {
            tx = null;
            closeSession(session);
        }
    }

    /**
     * For external session management.
     * 
     * @param session
     * @param clz
     * @param queryStr
     * @param parameters
     * @return
     */
    public <T> int updateEntities(Session session, Class<T> clz, String queryStr, Map<String, Object> parameters) {
        Query query = null;
        try {
            query = session.createQuery(queryStr);
            for (String k : parameters.keySet()) {
                Object value = parameters.get(k);
                if (value instanceof Integer) {
                    query.setInteger(k, (Integer) value);
                } else if (value instanceof Long) {
                    query.setLong(k, (Long) value);
                } else if (value instanceof String) {
                    query.setString(k, (String) value);
                } else if (value instanceof Date) {
                    query.setDate(k, (Date) value);
                } else if (value instanceof Timestamp) {
                    query.setTimestamp(k, (Timestamp) value);
                } else if (value instanceof Double) {
                    query.setDouble(k, (Double) value);
                }
            }
            System.out.println(query);
            int rowsAffected = query.executeUpdate();
            return rowsAffected;
        } catch (HibernateException e) {
            throw e;
        } finally {
        }
    }

    @Transactional
    public boolean deleteEntity(Object entity) {
        Session session = null;
        session = openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(entity);
            tx.commit();
            return true;
        } catch (HibernateException e) {
            handleException(e, tx);
            throw e;
        } finally {
            closeSession(session);
            tx = null;
        }
    }

    @Transactional
    public <T> T getSingleRecord(Class<T> clz, String queryStr, Map<Integer, Object> parameters) {
        return getSingleRecord(clz, queryStr, parameters, -1);
    }

    @SuppressWarnings("unchecked")
    @Transactional
    public <T> T getSingleRecord(Class<T> clz, String queryStr, Map<Integer, Object> parameters, boolean cacheEnabled) {
        return getSingleRecord(clz, queryStr, parameters, -1, cacheEnabled);
    }

    public <T> T getSingleRecord(Class<T> clz, String queryStr, Map<Integer, Object> parameters, int maxResults) {
        return getSingleRecord(clz, queryStr, parameters, maxResults, false);
    }

    public <T> T getSingleRecord(Class<T> clz, String queryStr, Map<Integer, Object> parameters, int maxResults,
            boolean cacheEnabled) {
        Session session = null;
        Transaction tx = null;
        Query query = null;
        try {
            // BaseDao.session=openSession();
            session = openSession();
            tx = session.beginTransaction();
            query = session.createQuery(queryStr);
            for (Integer k : parameters.keySet()) {
                query.setParameter(k, parameters.get(k));
            }
            if (maxResults > 0) {
                query.setMaxResults(maxResults);
            }
            if (cacheEnabled) {
                query.setCacheable(true);
            }
            T obj = (T) query.uniqueResult();
            tx.commit();
            return obj;
        } catch (HibernateException e) {
            handleException(e, tx);
            return null;
        } finally {
            tx = null;
            // this.tx=session.beginTransaction();
            // deleteIdleConnections("SELECT pg_terminate_backend(pid) FROM
            // pg_stat_activity WHERE pid <> pg_backend_pid()AND state = 'idle'
            // AND datname = 'sts_canada'");
            closeSession(session);
        }
    }

    @Transactional
    public <T> List<T> getRecords(Class<T> clz, String queryStr, Map<Integer, Object> parameters, int maxResults) {
        return getRecords(clz, queryStr, parameters, maxResults, false);
    }

    @SuppressWarnings("unchecked")
    @Transactional
    public <T> List<T> getRecords(Class<T> clz, String queryStr, Map<Integer, Object> parameters, int maxResults,
            boolean cacheEnabled) {
        Session session = null;
        Transaction tx = null;
        try {
            session = openSession();
            tx = session.beginTransaction();
            Query query = session.createQuery(queryStr);
            if (parameters != null) {
                for (Integer k : parameters.keySet()) {
                    Object val = parameters.get(k);
                    query.setParameter(k, val);
                }
            }
            if (maxResults != 0) {
                query.setMaxResults(maxResults);
            }
            if (cacheEnabled) {
                query.setCacheable(true);
            }
            List<T> results = (List<T>) query.list();
            tx.commit();
            return results;
        } catch (HibernateException e) {
            handleException(e, tx);
            return null;
        } finally {
            tx = null;
            closeSession(session);
        }
    }

    public <T> T getSingleRecordWithNamedQuery(Class<T> clz, String queryStr, Map<String, Object> parameters,
            boolean cacheEnabled) {
        List<T> ls = getRecordsListWithNamedQuery(clz, queryStr, parameters, 1, cacheEnabled);
        if (ls != null && ls.size() > 0) {
            return ls.get(0);
        } else {
            return null;
        }
    }

    public <T> List<T> getRecordsListWithNamedQuery(Class<T> clz, String queryStr, Map<String, Object> parameters,
            int maxResults) {
        return getRecordsListWithNamedQuery(clz, queryStr, parameters, maxResults, false);
    }
    /**
     * Only works with Named query parameter. like
     * 
     * Query query = session.createQuery("FROM Cat c WHERE c.id IN (:ids)");
     * query.setParameterList("ids", listOfIds); OR query.setParameter("ids",
     * listOfIds);
     * 
     * @param clz
     * @param queryStr
     * @param parameters
     * @param maxResults
     * @return
     */
    @SuppressWarnings("unchecked")
    @Transactional
    public <T> List<T> getRecordsListWithNamedQuery(Class<T> clz, String queryStr, Map<String, Object> parameters,
            int maxResults, boolean cacheEnabled) {
        Session session = null;
        Transaction tx = null;
        try {
            session = openSession();
            tx = session.beginTransaction();
            Query query = session.createQuery(queryStr);
            for (String k : parameters.keySet()) {
                Object val = parameters.get(k);
                if (val instanceof Collection) {
                    query.setParameterList(k, (Collection) val);
                } else {
                    query.setParameter(k, val);
                }
            }
            // if (maxResults != 0) {
            if (maxResults > 0) {
                query.setMaxResults(maxResults);
            }
            if (cacheEnabled) {
                query.setCacheable(true);
            }
            List<T> results = (List<T>) query.list();
            tx.commit();
            return results;
        } catch (HibernateException e) {
            handleException(e, tx);
            return null;
        } finally {
            tx = null;
            closeSession(session);
        }
    }

    @Transactional
    public <T> List<T> getRecords(Class<T> clz, String queryStr, Map<Integer, Object> parameters, int offset,
            int maxResults) {
        return getRecords(clz, queryStr, parameters, offset, maxResults, false);
    }

    @Transactional
    public <T> List<T> getRecords(Class<T> clz, String queryStr, Map<Integer, Object> parameters, int offset,
            int maxResults, boolean cacheEnabled) {
        Session session = null;
        Transaction tx = null;
        try {
            session = openSession();
            tx = session.beginTransaction();
            Query query = session.createQuery(queryStr);
            if (parameters != null) {
                for (Integer k : parameters.keySet()) {
                    query.setParameter(k, parameters.get(k));
                }
            }
            if (offset != 0) {
                query.setFirstResult(offset);
                // query.setFirstResult((pageNo * maxResults) + 1);
            }
            if (maxResults != 0) {
                query.setMaxResults(maxResults);
            }
            if (cacheEnabled) {
                query.setCacheable(true);
            }
            List<T> results = (List<T>) query.list();
            tx.commit();
            return results;
        } catch (HibernateException e) {
            handleException(e, tx);
            return null;
        } finally {
            tx = null;
            closeSession(session);
        }
    }

    @SuppressWarnings("unchecked")
    @Transactional
    public <T> List<T> getRecords(Class<T> clz, String queryStr, Map<Integer, Object> parameters) {
        return getRecords(clz, queryStr, parameters, -1);
    }

    @SuppressWarnings("unchecked")
    @Transactional
    public <T> List<T> getRecords(Class<T> clz, String queryStr, Map<Integer, Object> parameters, boolean cacheEnabled) {
        return getRecords(clz, queryStr, parameters, -1, cacheEnabled);
    }

    @SuppressWarnings({"unchecked"})
    @Transactional
    public <T> List<T> searchRecords(Class<T> clz, Map<String, Object> restrictions) {
        Session session = openSession();
        Transaction tx = session.beginTransaction();
        Criteria c = null;
        try {
            c = session.createCriteria(clz);
            for (String k : restrictions.keySet()) {
                if (restrictions.get(k) instanceof String) {
                    c.add(Restrictions.ilike(k, restrictions.get(k)));
                }
                else if (restrictions.get(k) instanceof Integer) {
                    c.add(Restrictions.eq(k, restrictions.get(k)));
                }
            }
            // System.out.println(c);
            List<T> list = c.list();
            tx.commit();
            return list;
        } catch (HibernateException e) {
            handleException(e, tx);
            return null;
        } finally {
            tx = null;
            closeSession(session);
        }
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> executeSQLQuery(String queryStr, boolean cacheEnabled) {
        Session session = null;
        SQLQuery query = null;
        Transaction tx = null;
        try {
            session = openSession();
            tx = session.beginTransaction();
            query = session.createSQLQuery(queryStr);
            if (cacheEnabled) {
                query.setCacheable(true);
            }
            System.out.println("query string:"+query.getQueryString());
            List<T> list = query.list();
            tx.commit();
            return (List<T>) list;
        } catch (HibernateException e) {
            handleException(e, tx);
            return null;
        } finally {
            tx = null;
            closeSession(session);
        }
    }
    
    public <T> List<T> getStopsBySqlQuery(Class<T> clz, String queryStr, Map<Integer, Object> parameters) {
        return getStopsBySqlQuery(clz, queryStr, parameters, false);
    }

    @SuppressWarnings("unchecked")
    @Transactional
    public <T> List<T> getStopsBySqlQuery(Class<T> clz, String queryStr, Map<Integer, Object> parameters, boolean cacheEnabled) {
        Session session = null;
        SQLQuery query = null;
        Transaction tx = null;
        try {
            session = openSession();
            tx = session.beginTransaction();
            query = session.createSQLQuery(queryStr);
            if (parameters != null) {
                for (Integer k : parameters.keySet()) {
                    query.setParameter(k, parameters.get(k));
                }
            }
            query.addEntity(clz);
            if (cacheEnabled) {
                query.setCacheable(true);
            }
            System.out.println("query string:"+query.getQueryString());
            List<T> list = query.list();
            tx.commit();
            return (List<T>) list;
        } catch (HibernateException e) {
            handleException(e, tx);
            return null;
        } finally {
            tx = null;
            closeSession(session);
        }
    }

    @SuppressWarnings("unchecked")
    @Transactional
    public <T> T getStudentsBySqlQuery(Class<T> clz, String queryStr, Map<Integer, Object> parameters) {
        Session session = null;
        SQLQuery query = null;
        Transaction tx = null;
        try {
            session = openSession();
            tx = session.beginTransaction();
            query = session.createSQLQuery(queryStr);
            for (Integer k : parameters.keySet()) {
                query.setParameter(k, parameters.get(k));
            }
            query.addEntity(clz);
            T entity = (T) query.uniqueResult();
            tx.commit();
            return entity;
        } catch (HibernateException e) {
            handleException(e, tx);
            return null;
        } finally {
            tx = null;
            closeSession(session);
        }
    }

    public void handleException(Exception e, Transaction tx) {
        e.printStackTrace();
        if (tx != null && tx.isActive()) {
            tx.rollback();
        }
    }

    // @Transactional
    protected void closeSession(Session session) {
        if (session != null && session.isOpen()) {
            try {
                session.close();
            } catch (Exception e) {
                System.out.println("Error while closing the session");
                e.printStackTrace();
            }
        }
    }

    private void deleteIdleConnections(String queryStr) {
        Session session = null;
        SQLQuery query = null;
        Transaction tx = null;
        try {
            session = openSession();
            tx = session.beginTransaction();
            query = session.createSQLQuery(queryStr);
            System.out.println("called");
            List result = query.list();
            tx.commit();
            System.out.println("committed " + result);
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            tx = null;
            closeSession(session);
        }
    }
    
    public <T> List<T> getRecordsByPagination(Class<T> clz, Integer first, Integer count) {
        return getRecordsByPagination(clz, (Criterion[]) null, null, null, first, count);
    }

    public <T> List<T> getRecordsByPagination(Class<T> clz, Criterion crit, Order order, Integer first, Integer count) {
        return getRecordsByPagination(clz, new Criterion[]{crit}, null, new Order[]{order}, first, count);
    }
    
    public <T> List<T> getRecordsByPagination(Class<T> clz, Criterion crit, Order[] orders, Integer first, Integer count) {
        return getRecordsByPagination(clz, new Criterion[]{crit}, null, orders, first, count);
    }

    public <T> List<T> getRecordsByPagination(Class<T> clz, Criterion[] crits, Projection projection, Order[] orders, Integer first, Integer count) {
        return getRecordsByPaginationWithOrder(clz, crits, null, orders, first, count);
    }

    @SuppressWarnings("unchecked")
    // @Transactional
    public <T> List<T> getRecordsByPaginationWithOrder(Class<T> clz, Criterion[] crits, Projection projection, Order[] orders, Integer first, Integer count) {
        Session session = null;
        Transaction tx = null;
        // Query query=null;
        // return openSession().createCriteria(clz).list();
        try {
            session = openSession();
            // tx = session.beginTransaction();

            Criteria criteria = session.createCriteria(clz);
            if (crits != null) {
                for (Criterion criterion : crits) {
                    if (criterion != null) {
                        criteria.add(criterion);
                    }
                }
            }

            if (orders != null) {
                for (Order order : orders) {
                    if (order != null) {
                        criteria.addOrder(order);
                    }
                }
            }
            if (first != null) {
                criteria.setFirstResult(first - 1);
            }
            if (count != null) {
                criteria.setMaxResults(count);
            }
            return criteria.list();

        } catch (HibernateException e) {
            handleException(e, tx);
            return null;
        } finally {
            tx = null;
            closeSession(session);
        }
    }

}
