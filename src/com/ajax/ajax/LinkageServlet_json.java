package com.ajax.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import com.ajax.base.linkage.dao.ILinkageDAO;
import com.ajax.base.linkage.dao.LinkageDAOImpl;
import com.ajax.base.linkage.domain.BasicDomain;
import com.ajax.base.linkage.domain.City;
import com.ajax.base.linkage.domain.Provice;
import com.ajax.base.util.IngoreJson;

@WebServlet("/linkage_json")
public class LinkageServlet_json extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ILinkageDAO dao;
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		dao=new LinkageDAOImpl();
	}
	/**
	 * [{'id':1,'name':'北京'},{'id':2,'name':'四川'}]
	 */
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			req.setCharacterEncoding("utf-8");
			//返回Json字符串,要设置contentType:text/json
			resp.setContentType("text/json;charset=utf-8");
			String cmd=req.getParameter("cmd");
			PrintWriter out=resp.getWriter();
			if("listProvices".equals(cmd)){
				//处理省份
				//查询所有的省份信息;
				List<Provice> ps=this.dao.listProvice();
				//拼装成json
				createResult(out, ps);
			}else if("listCitiesByProviceId".equals(cmd)){
				//处理城市
				Long pid=Long.parseLong(req.getParameter("proviceId"));
				List<City> cs=this.dao.listCitiesByProvice(pid);
				createResult(out,cs);
			} 
	}

	/**
	 * [{'id':1,'name':'北京'},{'id':2,'name':'四川'}]
	 */
	private void createResult(PrintWriter out, List<? extends BasicDomain> ps) {
		JsonConfig config = new JsonConfig();
		config.addIgnoreFieldAnnotation(IngoreJson.class);
		out.print(JSONArray.fromObject(ps,config).toString());
	}

	

}
