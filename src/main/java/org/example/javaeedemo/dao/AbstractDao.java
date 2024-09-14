package org.example.javaeedemo.dao;

import org.example.javaeedemo.utils.HibernateAnnotationUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;


public abstract class AbstractDao<PK extends Serializable, T> {

    private final Class<T> persistentClass;

    @SuppressWarnings("unchecked")
    public AbstractDao() {
        this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    protected Session getSession() {
        return HibernateAnnotationUtil.getSessionFactory().getCurrentSession();
    }


    @SuppressWarnings("unchecked")
    public T getByKey(PK key) {
        Transaction txn = null;
        try (Session session = getSession()) {
            txn = session.beginTransaction();
            return getSession().get(persistentClass, key);
        } catch (Exception e) {
            if (txn != null) {
                txn.rollback();
            }
            e.printStackTrace();
            throw new HibernateException("Getting entity error, " + e);
        }

    }


    public void persist(T entity) {
        getSession().persist(entity);
    }


    public void update(T entity) {
        getSession().merge(entity);
    }


    public void delete(T entity) {
        getSession().remove(entity);
    }

}
