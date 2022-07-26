package com.model2.mvc.service.purchase.impl;

import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.dao.PurchaseDAO;

public class PurchaseServiceImpl implements PurchaseService {
	
	private PurchaseDAO purchaseDAO;

	public PurchaseServiceImpl() {
		System.out.println("PurchaseServiceImpl default constructor");
		purchaseDAO = new PurchaseDAO();
	}

	@Override
	public Purchase addPurchase(Purchase purchaseVO, Product productVO) throws Exception {
		System.out.println("PurchaseServiceImpl addPurchase(PurchaseVO purchaseVO) start...");
		purchaseDAO.insertPurchase(purchaseVO, productVO);
		return purchaseVO;		
	}

	@Override
	public Purchase getPurchase(int tranNo) throws Exception {
		System.out.println("PurchaseServiceImpl getPurchase(int tranNo) start...");
		return purchaseDAO.findPurchase(tranNo);
	}

	@Override
	public Map<String, Object> getPurchaseList(Search searchVO, String userID) throws Exception {
		System.out.println("PurchaseServiceImpl getPurchaseList(SearchVO searchVO) start...");
		return purchaseDAO.getPurchaseList(searchVO, userID);
	}

	@Override
	public Map<String, Object> getSaleList(Search searchVO) throws Exception {
		System.out.println("PurchaseServiceImpl getSaleList(SearchVO searchVO) start...");
		return null;
	}

	@Override
	public Purchase updatePurchase(Purchase purchaseVO) throws Exception {
		System.out.println("PurchaseServiceImpl updatePurchase(PurchaseVO purchaseVO) start...");
		purchaseDAO.updatePurchase(purchaseVO);
		return purchaseDAO.findPurchase(purchaseVO.getTranNo());
	}

	@Override
	public void updateTranCode(Purchase purchaseVO) throws Exception {
		System.out.println("PurchaseServiceImpl updateTranCode(PurchaseVO purchaseVO) start...");
		purchaseDAO.updateTranCode(purchaseVO);
	}

}
