package by.trepam.like_it.service;

import java.util.List;

import by.trepam.like_it.domain.Category;
import by.trepam.like_it.service.exception.ServiceException;

public interface CategoryService {
		
		List<Category> getCategories(Object lang) throws ServiceException;
		Category getCategory(Integer category_id, Object lang) throws ServiceException;
		void addCategory(Category category) throws ServiceException;
		Integer getCategoryIdByTitle(String name) throws ServiceException;
		void addCategoryText(Category category, String lang) throws ServiceException;
		void deleteCategory(Integer id_category) throws ServiceException;
		void updateCategory(Category category) throws ServiceException;
		void updateCategoryText(Category category, String lang) throws ServiceException;
		void deleteCategoryText(Integer category_id, String lang) throws ServiceException;
		
}
