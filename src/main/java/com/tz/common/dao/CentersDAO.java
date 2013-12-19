package com.tz.common.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tz.common.model.Center;

/**
 * @author TZ
 * 
 */
@Repository
@Transactional
public class CentersDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public Center getByCode(String code) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				Center.class);
		criteria.add(Restrictions.eq("code", code));
		return (Center)criteria.uniqueResult();
	}

	public Center getById(int id) {
		return (Center) sessionFactory.getCurrentSession()
				.get(Center.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Center> searchCenters(String name) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				Center.class);
		criteria.add(Restrictions.ilike("name", name + "%"));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<Center> getAllCenters() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				Center.class);
		return criteria.list();
	}

	public int save(Center center) {
		return (Integer) sessionFactory.getCurrentSession().save(center);
	}

	public Center update(Center center) {
		return (Center)sessionFactory.getCurrentSession().merge(center);
	}

	public void delete(int id) {
		Center c = getById(id);
		sessionFactory.getCurrentSession().delete(c);
	}

}
