package com.bit.guestbook.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit.guestbook.dao.GuestbookDao;
import com.bit.guestbook.vo.GuestbookVo;

@WebServlet("/gb")
public class GuestbookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String actionName = request.getParameter("a");
		
		if("add".equals(actionName)){
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String message = request.getParameter("content");
			
			
			GuestbookVo vo = new GuestbookVo();
			vo.setName(name);
			vo.setPassword(password);
			vo.setMessage(message);
			
			GuestbookDao dao = new GuestbookDao();
			dao.insert(vo);
			
			response.sendRedirect("/guestbook2/gb");
		}else if("deleteform".equals(actionName)){
			// 포워딩, request 연장.
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/deleteform.jsp");
			rd.forward(request, response);
			
		}else if("delete".equals(actionName)){
			String no = request.getParameter("no");
			String password = request.getParameter("password");
			
			GuestbookVo vo = new GuestbookVo();
			vo.setNo(Long.parseLong(no));
			vo.setPassword(password);
			
			GuestbookDao dao = new GuestbookDao();
			dao.delete(vo);
			
			response.sendRedirect("/guestbook2/gb");
		}else{
			// 사용자가 넣지 않았거나, add, delete, deleteform 등 index(default) action 값 처리
			GuestbookDao dao = new GuestbookDao();
			List<GuestbookVo> list = dao.getList();
			
			// 이름만 꺼내면 "list"가 나온다.
			// view에 전달할 객체를 request범위에 저장.
			request.setAttribute("list", list);
			
			// 리퀘스트디스패쳐 객체가 생성됨. ( )안에는 path를 적어야한다.
			// 포워딩, request 연장.
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/index.jsp");
			rd.forward(request, response);
			
		}
	}

	

}
