package com.model2.mvc.view.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;


public class LoginAction extends Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User userVO=new User();
		//단일인입점 ActionServlet에서 받은 request에 있는 정보를 VO에 넣는다
		userVO.setUserId(request.getParameter("userId"));
		userVO.setPassword(request.getParameter("password"));
		
		UserService service=new UserServiceImpl();
		//로그인은 id, pwd만 있다 dbVO는 로그인 회원의 모든정보 있다
		User dbVO=service.loginUser(userVO);
		
		HttpSession session=request.getSession();
		session.setAttribute("user", dbVO);
		
		//if(dbVO.getRole().equals("admin")) {
		//	session.setAttribute("admin", "admin");
		//}
		
		return "redirect:/index.jsp";
	}
}