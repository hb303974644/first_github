package com.ajax.base.linkage.dao;

import java.util.List;

import com.ajax.base.linkage.domain.City;
import com.ajax.base.linkage.domain.Provice;


public interface ILinkageDAO {

	List<Provice> listProvice();
	
	List<City> listCitiesByProvice(Long id);
}
