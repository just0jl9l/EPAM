package by.trepam.like_it.dao;

import java.util.List;

import by.trepam.like_it.dao.exception.DAOException;
import by.trepam.like_it.domain.Category;

public interface CategoryDAO {

	void insert(Category category)throws DAOException ;
	void delete(Integer categoryId)throws DAOException ;
	Category getCategory(Integer categoryId,String lang)throws DAOException ;
	List<Category> getAllCategories(String lang)throws DAOException ;
	void update(Category category)throws DAOException ;
	Integer getCategoryId(String name) throws DAOException;
	void insertText(Category category, String lang) throws DAOException;
	void updateText(Category category, String lang) throws DAOException;
	void deleteText(Integer categoryId, String lang) throws DAOException;
	Category getLangCategory(Integer categoryId,String lang)  throws DAOException;
}
