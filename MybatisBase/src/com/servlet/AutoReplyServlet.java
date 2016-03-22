package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.MicroMessage;
import com.service.MessageService;

/**
 * Servlet implementation class AutoReplyServlet
 */
public class AutoReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AutoReplyServlet() {
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
		//处理中文乱码
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		response. setCharacterEncoding("UTF-8");
		String command = request.getParameter("content");
		PrintWriter out = response.getWriter();
		MessageService messageService = new MessageService();
//		String result = messageService.queryMessageByMybatis(command);
		String result = messageService.queryCommandByMybatis(command);
		out.write(result);
		out.flush();
		out.close();
	}

}
