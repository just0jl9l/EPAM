package by.trepam.like_it.service;

import java.util.List;

import by.trepam.like_it.domain.Category;
import by.trepam.like_it.service.exception.DataNotFoundException;
import by.trepam.like_it.service.exception.GettingDataException;
import by.trepam.like_it.service.exception.WrongDataException;

/**
 * Interface class of service layer. It provides set of available operations on
 * categories.
 *
 */

public interface CategoryService {

	/**
	 * Method returns list of all existing category, preferably in language
	 * lang.
	 * 
	 * @param lang
	 * @return
	 * @throws GettingDataException
	 */
	List<Category> getCategories(Object lang) throws GettingDataException;

	/**
	 * Method returns existing category (preferably in language lang) or null,
	 * if category with categoryId does not exist.
	 * 
	 * @param categoryId
	 * @param lang
	 * @return
	 * @throws DataNotFoundException
	 * @throws GettingDataException
	 * @throws WrongDataException
	 */
	Category getCategory(Integer categoryId, Object lang)
			throws DataNotFoundException, GettingDataException, WrongDataException;

	/**
	 * Method adds new category in English like categoryEn and in Russian like
	 * categoryRu.
	 * 
	 * @param categoryRu
	 * @param categoryEn
	 * @throws WrongDataException
	 * @throws GettingDataException
	 */
	void addCategory(Category categoryRu, Category categoryEn) throws WrongDataException, GettingDataException;

	/**
	 * Method returns id of category with title name or null, if there is no
	 * category with title name.
	 * 
	 * @param name
	 * @return
	 * @throws WrongDataException
	 * @throws GettingDataException
	 * @throws DataNotFoundException
	 */
	Integer getCategoryIdByTitle(String name) throws WrongDataException, GettingDataException, DataNotFoundException;

	/**
	 * Method deletes category, if category with categoryId exists.
	 * 
	 * @param categoryId
	 * @throws WrongDataException
	 * @throws GettingDataException
	 */
	void deleteCategory(Integer categoryId) throws WrongDataException, GettingDataException;

	/**
	 * Method updates existing category in English like categoryEn and in
	 * Russian like categoryRu.
	 * 
	 * @param categoryRu
	 * @param categoryEn
	 * @throws GettingDataException
	 * @throws WrongDataException
	 */
	void updateCategory(Category categoryRu, Category categoryEn) throws GettingDataException, WrongDataException;

	/**
	 * Method returns existing category with categoryId, title and description
	 * in language lang or null, if there is no title and description of
	 * category with id in language lang.
	 * 
	 * @param id
	 * @param lang
	 * @return
	 * @throws GettingDataException
	 * @throws WrongDataException
	 */
	Category getCategoryText(Integer id, Object lang) throws GettingDataException, WrongDataException;

}
