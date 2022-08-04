package com.model2.mvc.service.purchase;

import java.util.List;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;

public interface PurchaseService {
	
	public Purchase addPurchase(Purchase purchaseVO, Product productVO) throws Exception;
	
	public Purchase getPurchase(int tranNo) throws Exception;
	
	public List<Purchase> getListPurchase(String tranId) throws Exception;
	
	public Map<String, Object> getPurchaseList(Search searchVO, String userID) throws Exception;
	
	public Map<String, Object> getSaleList(Search searchVO) throws Exception;
	
	public Purchase updatePurchase(Purchase purchaseVO) throws Exception;
	
	public void updateTranCode(Purchase purchaseVO) throws Exception;
}
