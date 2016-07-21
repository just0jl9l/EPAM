package by.trepam.like_it.dao;

import java.util.List;

import by.trepam.like_it.dao.exception.DAOException;
import by.trepam.like_it.domain.Category;

public interface CategoryDAO {

	void insert(Category category)throws DAOException ;
	void delete(int categoryID)throws DAOException ;
	Category getCategory(int categoryID)throws DAOException ;
	List<Category> getAllCategories()throws DAOException ;
	void update(Category category)throws DAOException ;
}
