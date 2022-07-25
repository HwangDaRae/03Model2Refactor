package com.model2.mvc.service.cart;

import java.util.Map;

import com.model2.mvc.service.domain.Cart;

public interface CartService {
	
	//insert 장바구니 상품 추가 클릭하면 화면이동 window창 보여주고 확인이면 이동 아니면 그대로(?), delete 장바구니 상품 삭제 1개 or 여러개 prod_no으로, updateAmount 수량수정, 장바구니 모두 출력
	//product 테이블 수량 넣고 재고가 있으면
	
	public void insertCart(Cart cart) throws Exception;
	
	public void deleteCart(Map<String,Object> map) throws Exception;
	
	public void updateAmount(Cart cart) throws Exception;
	
	public Map<String, Object> getCartList(String user_id) throws Exception;

}
