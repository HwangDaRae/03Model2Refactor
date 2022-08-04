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
		//left.jsp 레이어에 있는 장바구니 <a href 클릭시 유저에 맞는 장바구니 리스트로 이동
		
		User user = (User)request.getSession(true).getAttribute("user");
		if(user == null) {
			user = new User();
			user.setUserId("non-member");
			request.getSession(true).setAttribute("user", user);
		}
		String user_id = user.getUserId();
		System.out.println("여기는 ListCartAction : " + user_id);
		
		CartService service = new CartServiceImpl();
		Map<String, Object> map = service.getCartList(user_id);
		
		ArrayList<Cart> list = (ArrayList<Cart>)map.get("list");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).toString());
		}
		
		request.setAttribute("list", map.get("list"));
		//count : 게시물 수, listCart.jsp에서 count>0일때 for문으로 list출력
		request.setAttribute("count", map.get("count") );
		
		System.out.println("[ListCartAction execute() end...]");
		return "forward:/cart/listCart.jsp";
	}

}
