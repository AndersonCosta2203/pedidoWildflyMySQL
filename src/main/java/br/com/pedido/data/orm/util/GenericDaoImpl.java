package br.com.pedido.data.orm.util;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import br.com.pedido.util.data.GenericData;

public abstract class GenericDaoImpl<T extends Serializable> implements GenericData<T> {

	private static final String ENTITY_MANAGER_IS_CLOSED_ENTITY = "Entity Manager is Closed, Entity: ";

	private static final String FROM = "FROM ";

	private static final String PERSISTENCE_UNIT_NAME = "MySqlDS";

	private static EntityManagerFactory entityManagerFactory = Persistence
			.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

	protected final EntityManager entityManager;

	private final EntityManagerFactory factory;
	
	private final Logger logger = Logger.getLogger(getEntityClass());

	///////////////////////////////////////////////////////////////////
	// CONSTRUCTOR
	///////////////////////////////////////////////////////////////////
	public GenericDaoImpl() {
		this(entityManagerFactory);
	}

	public GenericDaoImpl(EntityManagerFactory factory) {
		this.factory = factory;
		entityManager = factory.createEntityManager();
	}

	protected abstract Class<T> getEntityClass();


	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		logger.info("Executando findAll, Entity: " + getEntityClass().getSimpleName());

		try {
			return entityManager.createQuery(FROM + getEntityClass().getName()).getResultList();
		} catch (Exception e) {
			logger.error("Erro ao executar o findAll, Entity: " + getEntityClass().getSimpleName(), e);
			return Collections.emptyList();
		}
	}

	@Override
	public T findById(Integer id) {
		logger.info("Executando findById, Entity: " + getEntityClass().getSimpleName());
		try {
			return entityManager.find(getEntityClass(), id);
		} catch (Exception e) {
			logger.error("Erro ao executar o findById, Entity: " + getEntityClass().getSimpleName(), e);
			return null;
		}
	}

	@Override
	public T insert(T obj) {
		logger.info("Executando Insert, Entity: " + getEntityClass().getSimpleName());
		try {
			beginTransaction();
			entityManager.persist(obj);
			entityManager.flush();
			commit();
			return obj;
		} catch (Exception e) {
			rollBack();
			logger.error(
					"Erro ao executar o Insert, Entity: " + getEntityClass().getSimpleName(), e);
			return null;
		}
	}

	@Override
	public T update(T obj) {
		logger.info("Executando Update, Entity: " + getEntityClass().getSimpleName());
		try {
			beginTransaction();
			entityManager.merge(obj);
			commit();
			return obj;
		} catch (Exception e) {
			rollBack();
			logger.error(
					"Erro ao executar o Update, Entity: " + getEntityClass().getSimpleName() + ". Log: " + e);
			return null;
		}
	}

	@Override
	public void delete(Integer id) {
		logger.info("Executando Delete, Entity: " + getEntityClass().getSimpleName());
		try {
			beginTransaction();
			entityManager.remove(findById(id));
			commit();
		} catch (Exception e) {
			rollBack();
			logger.error(
					"Erro ao executar o Delete, Entity: " + getEntityClass().getSimpleName() + ". Log: " + e);
		}
	}

	///////////////////////////////////////////////////////////////////
	// CRUD Methods
	///////////////////////////////////////////////////////////////////
	public Object executeQuery(String query, Object... params) {
		logger.info("Executando Query, Entity: " + this.getEntityClass().getSimpleName());
		Query createdQuery = this.entityManager.createQuery(query);

		for (int i = 0; i < params.length; i++) {
			createdQuery.setParameter(i, params[i]);
		}
		return createdQuery.getResultList();
	}

	public Object executeQuerySingleResult(String query, Object... params) {
		logger.info("Executando Query, Entity: " + this.getEntityClass().getSimpleName());
		Query createdQuery = this.entityManager.createQuery(query);
		for (int i = 0; i < params.length; i++) {
			createdQuery.setParameter(i, params[i]);
		}
		return createdQuery.getSingleResult();

	}

	///////////////////////////////////////////////////////////////////
	// Transaction Methods
	///////////////////////////////////////////////////////////////////
	public void beginTransaction() {
		if (entityManager.isOpen()) {
			entityManager.getTransaction().begin();
		} else {
			logger.warn(ENTITY_MANAGER_IS_CLOSED_ENTITY + getEntityClass().getSimpleName());
		}
	}

	public void commit() {
		if (entityManager.isOpen()) {
			entityManager.getTransaction().commit();
		} else {
			logger.warn(ENTITY_MANAGER_IS_CLOSED_ENTITY + getEntityClass().getSimpleName());
		}
		entityManager.getTransaction().begin();
	}

	public void close() {
		entityManager.close();
		factory.close();
	}

	public void rollBack() {
		if (entityManager.isOpen()) {
			entityManager.getTransaction().rollback();
		} else {
			logger.warn(ENTITY_MANAGER_IS_CLOSED_ENTITY + getEntityClass().getSimpleName());
		}
		entityManager.getTransaction().rollback();
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}
}
