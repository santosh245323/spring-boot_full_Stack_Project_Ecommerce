package com.ecommerce.services;

import java.util.List;

import com.ecommerce.model.Category;

public interface CategoryService {

	void addCategory(Category category);
	void deleteCategory(int id);
	void updateCategory(Category category);
	Category getCategoryById(int id);
	
	List<Category> getAllCategories();
	
	Category FindDuplicate(String name);
	
	
	
}
