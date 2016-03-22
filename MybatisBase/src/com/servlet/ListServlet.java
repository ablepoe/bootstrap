package com.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.dao.MessageDao;
import com.db.DBUtil;
import com.entity.MicroMessage;
import com.service.MessageService;

/**
 * Servlet implementation class List
 * �б�ҳ���ʼ������
 */
public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设定字符集
		request.setCharacterEncoding("utf8");
		response.setCharacterEncoding("utf8");
		//获取表单参数
		String command = request.getParameter("command");
		String description = request.getParameter("description");
		String targetPage = request.getParameter("targetPage");
		//返回查询制
		request.setAttribute("command", command);
		request.setAttribute("description", description);
		request.setAttribute("targetPage", targetPage);
		
		MessageService messageService = new MessageService();
		//使用mybatis拦截器分页
		Map<String,Object> map = messageService.getLimitListByPage(command, description, targetPage);
		//在mybatis sql 中进行分页
//		Map<String,Object> map = messageService.getLimitList(command, description, targetPage);
		
		java.util.Iterator<Entry<String, Object>> it = map.entrySet().iterator();
		while(it.hasNext()){
			Entry entry = it.next();
			System.out.println(entry.getKey());
			System.out.println(entry.getValue());
			System.out.println(JSON.toJSONString(entry.getValue()));
		}
		
		request.setAttribute("microMessageList", map.get("microMessageList"));
		request.setAttribute("page", map.get("page"));
		
		request.getRequestDispatcher("/WEB-INF/jsp/back/list.jsp").forward(request, response);
	}

}
