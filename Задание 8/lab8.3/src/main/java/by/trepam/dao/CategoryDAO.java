package by.trepam.dao;

import java.util.List;

import by.trepam.dao.exception.DAOException;
import by.trepam.domain.Category;

public interface CategoryDAO {

	void insert(Category category)throws DAOException ;
	void delete(int categoryID)throws DAOException ;
	Category getCategory(int categoryID)throws DAOException ;
	List<Category> getAllCategories()throws DAOException ;
	void update(Category category)throws DAOException ;
}
