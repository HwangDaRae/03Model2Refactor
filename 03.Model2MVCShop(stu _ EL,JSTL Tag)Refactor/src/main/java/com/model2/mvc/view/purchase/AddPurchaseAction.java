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
		
		purchaseVO.setPaymentOption(request.getParameter("paymentOption")); //���Ź��
		purchaseVO.setReceiverName(request.getParameter("receiverName")); //�������̸�
		purchaseVO.setReceiverPhone(request.getParameter("receiverPhone")); //�����ڿ���ó
		purchaseVO.setDivyAddr(request.getParameter("receiverAddr")); //�������ּ�
		purchaseVO.setDivyRequest(request.getParameter("receiverRequest")); //�����ڿ�û����
		purchaseVO.setDivyDate(request.getParameter("receiverDate")); //����������
		purchaseVO.setAmount(Integer.parseInt(request.getParameter("amount"))); //����
		
		//��ǰ��ȣ�� ��ǰ�� �����´�
		ProductService pService = new ProductServiceImpl();
		Product productVO = pService.getProduct(Integer.parseInt(request.getParameter("prodNo")));
		purchaseVO.setPurchaseProd(productVO);
		
		//�����ھ��̵�� ���������� �����´�
		UserService uService = new UserServiceImpl();
		User userVO = uService.getUser(request.getParameter("buyerId"));
		purchaseVO.setBuyer(userVO);
		
		//purchaseVO.setTranCode(); //amount�� 0�̸� tran_code=1 amount�� 0�� �ƴϸ� tran_code=0
		// ��ǰ ���� - ������ ����
		productVO.setAmount( productVO.getAmount() - Integer.parseInt(request.getParameter("amount")) );
		pService.updateProduct(productVO);
		
		//�����»�ǰ�� ���������� PurchaseVO�� �ִ´�
		PurchaseService service = new PurchaseServiceImpl();
		purchaseVO = service.addPurchase(purchaseVO, productVO);
		System.out.println("purchaseVO.toString() : " + purchaseVO.toString());
		
		request.setAttribute("purchaseVO", purchaseVO);
		
		System.out.println("[AddPurchaseAction execute() end...]");
		return "forward:/purchase/addPurchase.jsp";
	}

}
