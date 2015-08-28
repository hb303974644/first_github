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
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.ajax.base.linkage.dao.ILinkageDAO;
import com.ajax.base.linkage.dao.LinkageDAOImpl;
import com.ajax.base.linkage.domain.BasicDomain;
import com.ajax.base.linkage.domain.City;
import com.ajax.base.linkage.domain.Provice;

@WebServlet("/linkage_xml")
public class LinkageServlet_xml extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private ILinkageDAO dao;
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		dao =new LinkageDAOImpl();
	}

	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		//必须要把contentType设置为text/xml,前台才能自动把返回的内容解析成XML 的DOM元素;
		resp.setContentType("text/xml;charset=utf-8");
		String cmd=req.getParameter("cmd");
		PrintWriter out=resp.getWriter();
		if("listProvices".equals(cmd)){
			//处理省份
			//查询所有的省份信息;
			List<Provice> ps=this.dao.listProvice();
			//拼装成HTML代码片段返回<option value='1'>四川</option>
			createResult(out, ps);
		}else if("listCitiesByProviceId".equals(cmd)){
			//处理城市
			Long pid=Long.parseLong(req.getParameter("proviceId"));
			List<City> cs=this.dao.listCitiesByProvice(pid);
			createResult(out,cs);
		} 
	}
	//拼装xml片段
	private void createResult(PrintWriter out, List<? extends BasicDomain> ps) {
		try{
			//获得DOM操作对象
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
			//创建根节点
			Element root = doc.createElement("datas");
			doc.appendChild(root);
			
			for(BasicDomain b : ps){
				Element data = doc.createElement("data");
				data.setAttribute("id", b.getId().toString());
				data.setAttribute("name", b.getName());
				root.appendChild(data);
			}
			
			Transformer tf = TransformerFactory.newInstance().newTransformer();
			tf.transform(new DOMSource(doc), new StreamResult(out));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
