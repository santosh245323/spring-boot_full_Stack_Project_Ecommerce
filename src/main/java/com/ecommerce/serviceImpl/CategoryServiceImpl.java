package com.ecommerce.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.model.Category;
import com.ecommerce.repositories.CategoryRepository;
import com.ecommerce.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepo;
	
	@Override
	public void addCategory(Category category) {
	categoryRepo.save(category);
		
	}

	@Override
	public void deleteCategory(int id) {
		categoryRepo.deleteById(id);
		
	}

	@Override
	public void updateCategory(Category category) {
		
		categoryRepo.save(category);
	}

	@Override
	public Category getCategoryById(int id) {
		
		return categoryRepo.findById(id).get();
	}

	@Override
	public List<Category> getAllCategories() {
		
		return categoryRepo.findAll();
	}

	@Override
	public Category FindDuplicate(String name) {
		// TODO Auto-generated method stub
		return null;
	}


}
