package com.model2.mvc.service.cart;

import java.util.Map;

import com.model2.mvc.service.domain.Cart;

public interface CartService {
	
	//insert ��ٱ��� ��ǰ �߰� Ŭ���ϸ� ȭ���̵� windowâ �����ְ� Ȯ���̸� �̵� �ƴϸ� �״��(?), delete ��ٱ��� ��ǰ ���� 1�� or ������ prod_no����, updateAmount ��������, ��ٱ��� ��� ���
	//product ���̺� ���� �ְ� ��� ������
	
	public void insertCart(Cart cart) throws Exception;
	
	public void deleteCart(Map<String,Object> map) throws Exception;
	
	public void updateAmount(Cart cart) throws Exception;
	
	public Map<String, Object> getCartList(String user_id) throws Exception;

}
