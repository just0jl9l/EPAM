package by.trepam.like_it.service;

import java.util.List;

import by.trepam.like_it.domain.Category;
import by.trepam.like_it.service.exception.DataNotFoundException;
import by.trepam.like_it.service.exception.GettingDataException;
import by.trepam.like_it.service.exception.WrongDataException;

public interface CategoryService {
		
		List<Category> getCategories(Object lang) throws DataNotFoundException, GettingDataException;
		Category getCategory(Integer categoryId, Object lang) throws DataNotFoundException, GettingDataException, WrongDataException;
		void addCategory(Category categoryRu,Category categoryEn) throws WrongDataException, GettingDataException;
		Integer getCategoryIdByTitle(String name) throws WrongDataException, GettingDataException, DataNotFoundException ;
		void deleteCategory(Integer categoryId) throws WrongDataException,GettingDataException ;
		void updateCategory(Category categoryRu, Category categoryEn) throws GettingDataException,WrongDataException;
		Category getLangCategory(Integer id, Object lang) throws GettingDataException, WrongDataException;
		
}
