package com.tz.common.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tz.common.model.Region;

/**
 * @author TZ
 * 
 */
@Repository
@Transactional
public class RegionsDAO {

    @Autowired
    private CentersDAO centersDAO;

    @Autowired
    private SessionFactory sessionFactory;

    public Region getByCode(String uipCenterCode, String code){
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Region.class);
        int uip_center_id = centersDAO.getByCode(uipCenterCode).getId();
        criteria.add(Restrictions.eq("uip_center_id", uip_center_id));
        criteria.add(Restrictions.eq("code", code));
        return (Region)criteria.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    public List<Region> getByCode2(String uipCenterCode){
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Region.class);
        int uip_center_id = centersDAO.getByCode(uipCenterCode).getId();
        criteria.add(Restrictions.eq("uip_center_id", uip_center_id));
        return criteria.list();
    }

    public Region getById(int uipCenterId, int id){
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Region.class);
        criteria.add(Restrictions.eq("uip_center_id", uipCenterId));
        criteria.add(Restrictions.eq("id", id));
        return (Region)criteria.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    public List<Region> searchRegions(String name){
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Region.class);
        criteria.add(Restrictions.ilike("name", name + "%"));
        return criteria.list();
    }

    @SuppressWarnings("unchecked")
    public List<Region> getAllRegions(){
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Region.class);
        return criteria.list();
    }

    public Region save(Region region){
        sessionFactory.getCurrentSession().save(region);
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Region.class);
        criteria.add(Restrictions.eq("code", region.getCode()));
        return (Region)criteria.uniqueResult();
    }

    public Region update(Region region){
        return (Region)sessionFactory.getCurrentSession().merge(region);
    }

    public void delete(int uipCenterId, int id){
        Region c = getById(uipCenterId, id);
        sessionFactory.getCurrentSession().delete(c);
    }

}
