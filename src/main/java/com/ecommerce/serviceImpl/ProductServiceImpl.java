package com.ecommerce.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.model.Product;
import com.ecommerce.repositories.ProductRepository;
import com.ecommerce.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepo;
	
	
	@Override
	public void addProduct(Product product) {
	
		productRepo.save(product);
		
		
	}


	


	@Override
	public void deleteProduct(Long id) {
		productRepo.deleteById(id);
		
	}


	@Override
	public void updateProduct(Product product) {
		
		productRepo.save(product);
	}


	@Override
	public void toggledProductStatus(Long id) {
		
		Optional<Product> optProduct = productRepo.findById(id);
		
		if(optProduct.isPresent()) {
			
			Product product = optProduct.get();
			
			
			
			productRepo.save(product);
		}
		
	}


@Override

	  public List<Product> getProductsBySeller(Long sellerId) {
	        return productRepo.findBySellerId(sellerId);
	    }
	

	
}
