package com.model2.mvc.view.cart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.cart.CartService;
import com.model2.mvc.service.cart.impl.CartServiceImpl;
import com.model2.mvc.service.domain.Cart;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;

public class AddCartAction extends Action {
	public AddCartAction() {
		System.out.println("[AddCartAction default Constructor()]");
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("[AddCartAction execute() start...]");
		Map<String, Object> map = new HashMap<String, Object>();
		Cart cart = new Cart();
		
		ProductService p_service = new ProductServiceImpl();
		Product product = p_service.getProduct( Integer.parseInt(request.getParameter("prod_no")) );

		// getProduct.jsp���� ��ٱ��� ��ư Ŭ���� ��ٱ��Ͽ� �߰��ȴ�.
		cart.setProd_no(Integer.parseInt(request.getParameter("prod_no")));
		cart.setUser_id( ( (User)request.getSession(true).getAttribute("user") ).getUserId() );
		cart.setImage(product.getFileName());
		cart.setProd_name(product.getProdName());
		cart.setProd_detail(product.getProdDetail());
		cart.setAmount(Integer.parseInt(request.getParameter("amount")));
		cart.setPrice(product.getPrice());
		
		System.out.println("AddCartAction cart : " + cart.toString());
		
		// ���� ��ǰ�� �ִ��� ���ϴ� ����Ʈ
		CartService service = new CartServiceImpl();
		map = service.getCartList( ( (User)request.getSession(true).getAttribute("user") ).getUserId() );
		
		//��ٱ��� ���θ� �����ͼ� ��ǰ��ȣ�� ���ٸ� �����߰�
		boolean isProdNo = false;
		ArrayList<Cart> p_list = (ArrayList<Cart>)map.get("list");
		for (int i = 0; i < p_list.size(); i++) {
			System.out.println(p_list.get(i).getProd_no());
			System.out.println(Integer.parseInt(request.getParameter("prod_no")));
			if(p_list.get(i).getProd_no() == Integer.parseInt(request.getParameter("prod_no"))){
				isProdNo = true;
				//���� ������Ʈ
				cart.setAmount(p_list.get(i).getAmount() + Integer.parseInt(request.getParameter("amount")));
				service.updateAmount(cart);
				System.out.println(cart.toString());
			}
		}
		
		if(!isProdNo) {
			service.insertCart(cart);
		}
		map = service.getCartList( ( (User)request.getSession(true).getAttribute("user") ).getUserId() );
		
		request.setAttribute("list", map.get("list"));
		request.setAttribute("count", map.get("count"));

		System.out.println("[AddCartAction execute() end...]");
		return "forward:/cart/listCart.jsp";
	}

}
