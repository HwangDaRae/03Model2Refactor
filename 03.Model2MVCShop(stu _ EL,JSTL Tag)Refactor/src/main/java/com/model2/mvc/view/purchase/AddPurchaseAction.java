package com.model2.mvc.view.purchase;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.cart.CartService;
import com.model2.mvc.service.cart.impl.CartServiceImpl;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;

public class AddPurchaseAction extends Action {

	public AddPurchaseAction() {
		System.out.println("[AddPurchaseAction default Constructor()]");
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("[AddPurchaseAction execute() start...]");
		Map<String, Object> map = new HashMap<String, Object>();
		ProductService pService = new ProductServiceImpl();
		UserService uService = new UserServiceImpl();
		PurchaseService service = new PurchaseServiceImpl();
		Purchase purchaseVO = new Purchase();
		Product productVO = new Product();

		// 상세정보에서 구매
		purchaseVO.setPaymentOption(request.getParameter("paymentOption")); //구매방법
		purchaseVO.setReceiverName(request.getParameter("receiverName")); //구매자이름
		purchaseVO.setReceiverPhone(request.getParameter("receiverPhone")); //구매자연락처
		purchaseVO.setDivyAddr(request.getParameter("receiverAddr")); //구매자주소
		purchaseVO.setDivyRequest(request.getParameter("receiverRequest")); //구매자요청사항
		purchaseVO.setDivyDate(request.getParameter("receiverDate")); //배송희망일자
		purchaseVO.setAmount(Integer.parseInt(request.getParameter("amount"))); //수량
		
		//장바구니에서 구매
		String[] checkedProdNo = request.getParameterValues("deleteCheckBox");
		String[] allCheckBoxProdNo = request.getParameterValues("addPurchaseCheckBox");
		String[] allamountProdNo = request.getParameterValues("amount");
		for (int i = 0; i < allCheckBoxProdNo.length; i++) {
			if(checkedProdNo.equals(allCheckBoxProdNo)) {
				System.out.println("상품번호 : " + allCheckBoxProdNo[i]);
				System.out.println("수량 : " + allamountProdNo[i]);
				
				//구매할 상품정보
				productVO = pService.getProduct(Integer.parseInt(allCheckBoxProdNo[i]));
				purchaseVO.setPurchaseProd(productVO);
				
				//구매한 유저정보
				User userVO = uService.getUser( ((User)request.getSession(true).getAttribute("user")).getUserId() );
				purchaseVO.setBuyer(userVO);
				
				//구매한 상품의 수량정보
				purchaseVO.setAmount(Integer.parseInt(allamountProdNo[i]));

				// 상품 수량 -= 구매한 수량
				productVO.setAmount( productVO.getAmount() - Integer.parseInt(allamountProdNo[i]) );
				pService.updateProduct(productVO);

				//가져온상품과 구매정보를 PurchaseVO에 넣는다
				purchaseVO = service.addPurchase(purchaseVO, productVO);
				System.out.println("purchaseVO.toString() : " + purchaseVO.toString());
				
				//구매한 상품 장바구니에서 삭제
				CartService cService = new CartServiceImpl();
				map.put("user_id", ((User)request.getSession(true).getAttribute("user")).getUserId());
				map.put("deleteArray", allCheckBoxProdNo[i]);
				cService.deleteCart(map);
			}
		}
		
		request.setAttribute("purchaseVO", purchaseVO);
				
		System.out.println("[AddPurchaseAction execute() end...]");
		return "forward:/purchase/addPurchase.jsp";
	}

}
