package com.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		//返回查询制
		request.setAttribute("command", command);
		request.setAttribute("description", description);	
		MessageService messageService = new MessageService();
		request.setAttribute("microMessageList", messageService.queryMessageListByMybatis(command, description));
		/*try {
			request.setAttribute("microMessageList", messageDao.queryMessageList(command, description));
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
		request.getRequestDispatcher("/WEB-INF/jsp/back/list.jsp").forward(request, response);
	}

}
