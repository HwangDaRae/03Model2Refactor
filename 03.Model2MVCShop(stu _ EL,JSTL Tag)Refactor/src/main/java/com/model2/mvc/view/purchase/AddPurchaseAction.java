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

		// ���������� ����
		purchaseVO.setPaymentOption(request.getParameter("paymentOption")); //���Ź��
		purchaseVO.setReceiverName(request.getParameter("receiverName")); //�������̸�
		purchaseVO.setReceiverPhone(request.getParameter("receiverPhone")); //�����ڿ���ó
		purchaseVO.setDivyAddr(request.getParameter("receiverAddr")); //�������ּ�
		purchaseVO.setDivyRequest(request.getParameter("receiverRequest")); //�����ڿ�û����
		purchaseVO.setDivyDate(request.getParameter("receiverDate")); //����������
		purchaseVO.setAmount(Integer.parseInt(request.getParameter("amount"))); //����
		
		//��ٱ��Ͽ��� ����
		String[] checkedProdNo = request.getParameterValues("deleteCheckBox");
		String[] allCheckBoxProdNo = request.getParameterValues("addPurchaseCheckBox");
		String[] allamountProdNo = request.getParameterValues("amount");
		for (int i = 0; i < allCheckBoxProdNo.length; i++) {
			if(checkedProdNo.equals(allCheckBoxProdNo)) {
				System.out.println("��ǰ��ȣ : " + allCheckBoxProdNo[i]);
				System.out.println("���� : " + allamountProdNo[i]);
				
				//������ ��ǰ����
				productVO = pService.getProduct(Integer.parseInt(allCheckBoxProdNo[i]));
				purchaseVO.setPurchaseProd(productVO);
				
				//������ ��������
				User userVO = uService.getUser( ((User)request.getSession(true).getAttribute("user")).getUserId() );
				purchaseVO.setBuyer(userVO);
				
				//������ ��ǰ�� ��������
				purchaseVO.setAmount(Integer.parseInt(allamountProdNo[i]));

				// ��ǰ ���� -= ������ ����
				productVO.setAmount( productVO.getAmount() - Integer.parseInt(allamountProdNo[i]) );
				pService.updateProduct(productVO);

				//�����»�ǰ�� ���������� PurchaseVO�� �ִ´�
				purchaseVO = service.addPurchase(purchaseVO, productVO);
				System.out.println("purchaseVO.toString() : " + purchaseVO.toString());
				
				//������ ��ǰ ��ٱ��Ͽ��� ����
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
