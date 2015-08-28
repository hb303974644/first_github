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

import com.ajax.base.linkage.dao.ILinkageDAO;
import com.ajax.base.linkage.dao.LinkageDAOImpl;
import com.ajax.base.linkage.domain.BasicDomain;
import com.ajax.base.linkage.domain.City;
import com.ajax.base.linkage.domain.Provice;

@WebServlet("/linkage_html")
public class LinkageServlet_html extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private ILinkageDAO dao;
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		dao =new LinkageDAOImpl();
	}

	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		
		String cmd = req.getParameter("cmd");
		PrintWriter out = resp.getWriter();
		if("listProvices".equals(cmd)){
			//处理省份
			//查询所有的省份信息;
			List<Provice> ps = this.dao.listProvice();
			//拼装成HTML代码片段返回<option value='1'>四川</option>
			createResult(out,ps);
		}else if("listCitiesByProviceId".equals(cmd)){
			//处理城市
			Long pid = Long.parseLong(req.getParameter("proviceId"));
			List<City> cs = this.dao.listCitiesByProvice(pid);
			createResult(out, cs);
		}
	}
	//拼装html片段//拼装成HTML代码片段返回<option value='1'>四川</option>
	private void createResult(PrintWriter out, List<? extends BasicDomain> ps) {
		StringBuilder ret = new StringBuilder(300);
		for(BasicDomain p : ps){
			ret.append("<option value='").append(p.getId())
				.append("'>").append(p.getName()).append("</option>");
		}
		out.print(ret.toString());
	}
}
