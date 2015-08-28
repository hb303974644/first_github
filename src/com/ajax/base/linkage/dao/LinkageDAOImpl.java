package com.ajax.base.linkage.dao;

import java.util.List;

import org.hibernate.Session;

import com.ajax.base.linkage.domain.City;
import com.ajax.base.linkage.domain.Provice;
import com.ajax.base.util.HibernateUtil;


public class LinkageDAOImpl implements ILinkageDAO {

	@Override
	public List<Provice> listProvice() {
		Session session = HibernateUtil.getInstance().openSession();
		List<Provice> list=session.createQuery("FROM Provice").list();
		session.close();
		return list;
	}

	@Override
	public List<City> listCitiesByProvice(Long id) {
		Provice p=new Provice();
		p.setId(id);
		
		Session session = HibernateUtil.getInstance().openSession();
		List<City> list=session.createQuery("FROM City c WHERE c.provice = ?").setEntity(0, p).list();
		session.close();
		return list;
	}

}
