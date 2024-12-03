package com.ecommerce.services;

import java.util.List;

import com.ecommerce.model.Product;



public interface ProductService {

	public void addProduct(Product product);
	public void deleteProduct(Long id);
	public void updateProduct(Product product);
	
	public void toggledProductStatus(Long id);
	
	
	  public List<Product> getProductsBySeller(Long sellerId);
	        
	    
	 
	
}
