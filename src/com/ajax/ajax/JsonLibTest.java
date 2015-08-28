package com.ajax.ajax;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.junit.Test;

import com.ajax.base.linkage.domain.City;
import com.ajax.base.util.IngoreJson;

public class JsonLibTest {

	@Test
	public void testJsonObject(){
		
		City c=new City();
		c.setId(1L);
		c.setName("成都");
		
		//把java对象变成JSON对象
		JSONObject jsonObj=JSONObject.fromObject(c);
		System.out.println(jsonObj.toString());
	}
	
	@Test
	public void testJsonArray(){
		
		City c=new City();
		c.setId(1L);
		c.setName("成都");
		
		City c2=new City();
		c2.setId(2L);
		c2.setName("北京");
		
		List<City> cs=new ArrayList<City>();
		cs.add(c);
		cs.add(c2);
		
		//把集合/数组变成Json数组对象
		JSONArray jsonArr=JSONArray.fromObject(cs);
		System.out.println(jsonArr.toString());
	}
	
	@Test
	public void testIngoreJsonObject(){
		City c=new City();
		c.setId(1L);
		c.setName("成都");
		
		
		//把java对象变成JSON对象
		//fromObject第二参数可以传入一个JsonConfig对象;这个对象可以针对这一次的OBJ-JSON转化提供限制;
		JsonConfig config=new JsonConfig();
		config.addIgnoreFieldAnnotation(IngoreJson.class);
		
		JSONObject jsonObj=JSONObject.fromObject(c,config);
		System.out.println(jsonObj.toString());
	}
}
