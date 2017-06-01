package com.redhat.example.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

public abstract class AbstractDao<T,K> {
	protected Class<T> entityClass;

	/**
	 * コンストラクタ。可変長引数のコンストラクタだが、エンティティの型Tを取得するためのテクニック
	 * として定義しているだけであるため、このコンストラクタはデフォルトコンストラクタとして使用
	 * すること。
	 */
	public AbstractDao(T... dummy) {
		@SuppressWarnings("unchecked")
		Class<T> type = (Class<T>)dummy.getClass().getComponentType();
		entityClass = type;
	}

	public abstract EntityManager getEntityManager();

	public void create(T entity) {
		getEntityManager().persist(entity);
	}
	
	public List<T> findAll() {
		CriteriaQuery query = getEntityManager().getCriteriaBuilder().createQuery();
		query.select(query.from(entityClass));
		return getEntityManager().createQuery(query).getResultList();
	}

	public T find(K id) {
		return getEntityManager().find(entityClass, id);
	}

	public void update(T entity) {
		getEntityManager().merge(entity);
	}

	public void remove(K id) {
		T ref = getEntityManager().getReference(entityClass, id);
		getEntityManager().remove(ref);
	}

}
