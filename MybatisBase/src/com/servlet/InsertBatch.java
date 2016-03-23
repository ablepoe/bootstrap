package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.service.CommandService;
import com.service.MessageService;

/**
 * Servlet implementation class InsertBatch
 */
public class InsertBatch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertBatch() {
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
		request.setCharacterEncoding("utf8");
		response.setCharacterEncoding("utf8");
		
		String id = request.getParameter("ids");
		String content = request.getParameter("contents");
		String[] ids = null;
		String[] contents = null;
		if(id != null){
			ids = id.split(",");
			System.out.println(JSON.toJSONString(ids));	
		}
		System.out.println("-----------");
		if(content != null){
			contents = content.split(",");
			System.out.println(JSON.toJSONString(contents));	
		}
		CommandService commandService = new CommandService();
		commandService.insertCommandContent(ids, contents);
		
		request.setAttribute("CommandContent", commandService.getAllCommandContent());
		request.getRequestDispatcher("/WEB-INF/jsp/back/insertContent.jsp").forward(request, response);
	}

}
