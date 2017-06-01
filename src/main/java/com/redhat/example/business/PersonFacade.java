package com.redhat.example.business;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptors;

import com.redhat.example.dao.PersonDao;
import com.redhat.example.model.Person;
import com.redhat.example.trace.Trace;
import com.redhat.example.trace.TraceInterceptor;

@Stateless
@Named
// デフォルトでJTA(TransactionManager)ベースのトランザクションが有効
// @TransactionMamagement(TransactionManagementType.CONTAINER)
// @TransactionAttribute(TransactionAttributeType.REQUIRED)
public class PersonFacade {
	@Inject Logger log;
	@Inject PersonDao dao;
	
	@Interceptors(TraceInterceptor.class)
	public void create(Person entry) throws BusinessException {
		dao.create(entry);
		
		// firstName, lastNameのどちらかに"ERROR"の文字列が含まれていたらロールバックする。
		if ((entry.getFirstName() != null && entry.getFirstName().contains("ERROR"))
				|| (entry.getLastName() != null && entry.getLastName().contains("ERROR"))) {
			throw new BusinessException("ロールバックのテスト");
		}
		log.info("エントリを保存しました。entry="+entry);
	}

	public List<Person> findAll() {
		return dao.findAll();
	}

	public Person find(String id) {
		return dao.find(id);
	}

	public void update(Person entity) {
		dao.update(entity);
	}

	public void remove(String id) {
		dao.remove(id);
	}
}
