package com.model2.mvc.service.product.impl;

import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.dao.ProductDAO;

public class ProductServiceImpl implements ProductService {
	
	private ProductDAO productDAO;
	
	public ProductServiceImpl() {
		System.out.println("ProductServiceImpl default constructor");
		productDAO = new ProductDAO();
	}

	@Override
	public Product addProduct(Product productVO) throws Exception {
		System.out.println("ProductServiceImpl addProduct(ProductVO productVO)");
		int i = productDAO.insertProduct(productVO);
		if(i==1) {
			System.out.println("상품 등록 성공");
			return productVO;
		}else {
			System.out.println("상품 등록 실패");
			return null;
		}
	}

	@Override
	public Product getProduct(int prodNo) throws Exception {
		System.out.println("ProductServiceImpl getProduct(int prodNo)");
		return productDAO.findProduct(prodNo);
	}

	@Override
	public Map<String, Object> getProductList(Search searchVO) throws Exception {
		System.out.println("ProductServiceImpl getProductList(SearchVO searchVO)");		
		return productDAO.getProductList(searchVO);
	}

	@Override
	public Product updateProduct(Product productVO) throws Exception {
		System.out.println("ProductServiceImpl updateProduct(ProductVO productVO)");
		int i = productDAO.updateProduct(productVO);
		if(i==1) {
			System.out.println("상품 수정 성공");
			return productDAO.findProduct(productVO.getProdNo());
		}else {
			System.out.println("상품 수정 실패");
			return null;
		}
	}

}
