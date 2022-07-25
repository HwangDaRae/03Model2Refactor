package com.model2.mvc.service.cart.impl;

import java.util.Map;

import com.model2.mvc.service.cart.CartService;
import com.model2.mvc.service.cart.dao.CartDAO;
import com.model2.mvc.service.domain.Cart;

public class CartServiceImpl implements CartService {
	CartDAO cartDAO;

	public CartServiceImpl() {
		System.out.println("CartServiceImpl default constructor");
		cartDAO = new CartDAO();
	}

	@Override
	public void insertCart(Cart cart) throws Exception {
		 System.out.println("CartServiceImpl insertCart(Cart cart)");
		 cartDAO.insertCart(cart);
	}

	@Override
	public void deleteCart(Map<String, Object> map) throws Exception {
		System.out.println("CartServiceImpl deleteCart(Map<String,Object> map)");
		cartDAO.deleteCart(map);
	}

	@Override
	public void updateAmount(Cart cart) throws Exception {
		System.out.println("CartServiceImpl updateAmount(Cart cart)");
		cartDAO.updateAmount(cart);
	}

	@Override
	public Map<String, Object> getCartList(String user_id) throws Exception {
		System.out.println("CartServiceImpl getCartList(String user_id)");
		return cartDAO.getCartList(user_id);
	}

}
