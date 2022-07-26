package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
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
		Purchase purchaseVO = new Purchase();
		
		purchaseVO.setPaymentOption(request.getParameter("paymentOption")); //구매방법
		purchaseVO.setReceiverName(request.getParameter("receiverName")); //구매자이름
		purchaseVO.setReceiverPhone(request.getParameter("receiverPhone")); //구매자연락처
		purchaseVO.setDivyAddr(request.getParameter("receiverAddr")); //구매자주소
		purchaseVO.setDivyRequest(request.getParameter("receiverRequest")); //구매자요청사항
		purchaseVO.setDivyDate(request.getParameter("receiverDate")); //배송희망일자
		purchaseVO.setAmount(Integer.parseInt(request.getParameter("amount"))); //수량
		
		//상품번호로 상품을 가져온다
		ProductService pService = new ProductServiceImpl();
		Product productVO = pService.getProduct(Integer.parseInt(request.getParameter("prodNo")));
		purchaseVO.setPurchaseProd(productVO);
		
		//구매자아이디로 유저정보를 가져온다
		UserService uService = new UserServiceImpl();
		User userVO = uService.getUser(request.getParameter("buyerId"));
		purchaseVO.setBuyer(userVO);
		
		//purchaseVO.setTranCode(); //amount가 0이면 tran_code=1 amount가 0이 아니면 tran_code=0
		// 상품 수량 - 구매한 수량
		productVO.setAmount( productVO.getAmount() - Integer.parseInt(request.getParameter("amount")) );
		pService.updateProduct(productVO);
		
		//가져온상품과 구매정보를 PurchaseVO에 넣는다
		PurchaseService service = new PurchaseServiceImpl();
		purchaseVO = service.addPurchase(purchaseVO, productVO);
		System.out.println("purchaseVO.toString() : " + purchaseVO.toString());
		
		request.setAttribute("purchaseVO", purchaseVO);
		
		System.out.println("[AddPurchaseAction execute() end...]");
		return "forward:/purchase/addPurchase.jsp";
	}

}
