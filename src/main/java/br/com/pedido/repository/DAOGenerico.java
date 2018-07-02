package br.com.pedido.repository;

import br.com.pedido.config.ConnectionFactory;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

public class DAOGenerico<T extends Serializable> {

    protected static EntityManager entityManager = ConnectionFactory.getEntityManager();

    public List<T> findAll(Class<T> clazz) {
        return entityManager.createQuery("select e from " + clazz.getSimpleName() + " e", clazz)
                .getResultList();
    }

    public T insert(T obj) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(obj);
            entityManager.getTransaction().commit();
            return obj;
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
        return null;
    }

    public T update(T obj) {
        try {
            entityManager.getTransaction().begin();
            T objMerge = entityManager.merge(obj);
            entityManager.getTransaction().commit();
            return  objMerge;
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
        return null;
    }

    public T findById(Class<T> clazz, Integer id) {
        try {
            return entityManager.find(clazz, id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void delete(Class<T> clazz, Integer id) {
        try {
            T obj = entityManager.find(clazz, id);
            if (obj != null) {
                entityManager.getTransaction().begin();
                entityManager.remove(obj);
                entityManager.getTransaction().commit();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }

    }
}
