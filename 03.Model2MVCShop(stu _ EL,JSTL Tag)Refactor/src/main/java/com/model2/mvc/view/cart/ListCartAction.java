package com.model2.mvc.view.cart;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.cart.CartService;
import com.model2.mvc.service.cart.impl.CartServiceImpl;
import com.model2.mvc.service.domain.Cart;
import com.model2.mvc.service.domain.User;

public class ListCartAction extends Action {
	public ListCartAction() {
		System.out.println("[ListCartAction default Constructor()]");
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("[ListCartAction execute() start...]");
		//left.jsp ���̾ �ִ� ��ٱ��� <a href Ŭ���� ������ �´� ��ٱ��� ����Ʈ�� �̵�
		
		User user = (User)request.getSession(true).getAttribute("user");
		if(user == null) {
			user = new User();
			user.setUserId("non-member");
			request.getSession(true).setAttribute("user", user);
		}
		String user_id = user.getUserId();
		System.out.println("����� ListCartAction : " + user_id);
		
		CartService service = new CartServiceImpl();
		Map<String, Object> map = service.getCartList(user_id);
		
		ArrayList<Cart> list = (ArrayList<Cart>)map.get("list");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).toString());
		}
		
		request.setAttribute("list", map.get("list"));
		//count : �Խù� ��, listCart.jsp���� count>0�϶� for������ list���
		request.setAttribute("count", map.get("count") );
		
		System.out.println("[ListCartAction execute() end...]");
		return "forward:/cart/listCart.jsp";
	}

}
