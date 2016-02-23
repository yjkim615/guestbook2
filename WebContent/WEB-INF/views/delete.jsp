<%@page import="com.bit.guestbook.dao.GuestbookDao"%>
<%@page import="com.bit.guestbook.vo.GuestbookVo"%>
<% 
		String no = request.getParameter("no");
	String password = request.getParameter("password");
	
	GuestbookVo vo = new GuestbookVo();
	vo.setNo(Long.parseLong(no));
	vo.setPassword(password);
	
	GuestbookDao dao = new GuestbookDao();
	dao.delete(vo);
	
	response.sendRedirect("/guestbook");
%>