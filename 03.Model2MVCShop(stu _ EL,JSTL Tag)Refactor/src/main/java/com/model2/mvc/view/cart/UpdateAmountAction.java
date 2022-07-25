package com.model2.mvc.view.cart;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.cart.CartService;
import com.model2.mvc.service.cart.impl.CartServiceImpl;
import com.model2.mvc.service.domain.Cart;
import com.model2.mvc.service.domain.User;

public class UpdateAmountAction extends Action {
	public UpdateAmountAction() {
		System.out.println("[UpdateAmountAction default Constructor()]");
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("[UpdateAmountAction execute() start...]");
		
		//+, - ÇßÀ» ¶§
		Cart cart = new Cart();
		cart.setAmount(Integer.parseInt(request.getParameter("amount")));
		cart.setProd_no(Integer.parseInt(request.getParameter("prod_no")));
		String user_id = ( (User)request.getSession(true).getAttribute("user") ).getUserId();
		cart.setUser_id( user_id );
		
		CartService service = new CartServiceImpl();
		service.updateAmount(cart);
		Map<String, Object> map = service.getCartList(user_id);
		request.setAttribute("list", map.get("list"));
		request.setAttribute("count", map.get("count"));

		System.out.println("[UpdateAmountAction execute() end...]");
		return "forward:/cart/listCart.jsp";
	}

}
