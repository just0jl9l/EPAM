package by.trepam.like_it.service;

import java.util.List;

import by.trepam.like_it.domain.Category;
import by.trepam.like_it.service.exception.ServiceException;

public interface CategoryService {
		
		List<Category> getCategories(Object lang) throws ServiceException;
		Category getCategory(Integer category_id, Object lang) throws ServiceException;
		void addCategory(Category categoryRu,Category categoryEn) throws ServiceException;
		Integer getCategoryIdByTitle(String name) throws ServiceException;
		void deleteCategory(Integer id_category) throws ServiceException;
		void updateCategory(Category categoryRu, Category categoryEn) throws ServiceException;
		
}
