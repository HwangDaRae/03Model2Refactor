package com.model2.mvc.service.user.impl;

import java.util.HashMap;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.dao.UserDAO;


public class UserServiceImpl implements UserService{
	
	private UserDAO userDAO;
	
	public UserServiceImpl() {
		userDAO=new UserDAO();
	}

	public void addUser(User userVO) throws Exception {
		userDAO.insertUser(userVO);
	}

	public User loginUser(User userVO) throws Exception {
		User dbUser=userDAO.findUser(userVO.getUserId());

		//아이디로 찾은 db정보의 비밀번호와 사용자가 입력한 비밀번호가 일치하는지 확인
		if(! dbUser.getPassword().equals(userVO.getPassword()))
			throw new Exception("로그인에 실패했습니다.");
		
		return dbUser;
	}

	public User getUser(String userId) throws Exception {
		return userDAO.findUser(userId);
	}

	public HashMap<String,Object> getUserList(Search searchVO) throws Exception {
		return userDAO.getUserList(searchVO);
	}

	public void updateUser(User userVO) throws Exception {
		userDAO.updateUser(userVO);
	}

	public boolean checkDuplication(String userId) throws Exception {
		boolean result=true;
		User userVO=userDAO.findUser(userId);
		if(userVO != null) {
			result=false;
		}
		return result;
	}
}