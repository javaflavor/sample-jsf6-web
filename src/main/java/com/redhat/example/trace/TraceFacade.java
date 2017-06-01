package com.redhat.example.trace;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.redhat.example.dao.AbstractDao;
import com.redhat.example.trace.model.TraceRecord;

@Stateless
@Named
public class TraceFacade extends AbstractDao<TraceRecord,String> {
	public TraceFacade() {
		entityClass = TraceRecord.class;
	}
	
	@PersistenceContext(unitName="ExamplePU") EntityManager em;
	
	@Override
	public EntityManager getEntityManager() {
		return em;
	}

	/**
	 * このメソッドはトランザクション境界がREQUIRES_NEWなので、上位のトランザクションの
	 * コミット／ロールバックに関わらずコミットされる。
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void create(TraceRecord entity) {
		super.create(entity);
	}

}
