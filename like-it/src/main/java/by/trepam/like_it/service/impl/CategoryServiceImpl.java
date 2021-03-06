package by.trepam.like_it.service.impl;

import java.util.List;

import by.trepam.like_it.command.impl.CommandConstant;
import by.trepam.like_it.dao.AccountDAO;
import by.trepam.like_it.dao.CategoryDAO;
import by.trepam.like_it.dao.ImageDAO;
import by.trepam.like_it.dao.MessageDAO;
import by.trepam.like_it.dao.exception.DAOException;
import by.trepam.like_it.dao.factory.DAOFactory;
import by.trepam.like_it.dao.factory.PostgresqlDAOFactory;
import by.trepam.like_it.domain.Account;
import by.trepam.like_it.domain.Category;
import by.trepam.like_it.domain.Message;
import by.trepam.like_it.service.CategoryService;
import by.trepam.like_it.service.exception.DataNotFoundException;
import by.trepam.like_it.service.exception.GettingDataException;
import by.trepam.like_it.service.exception.WrongDataException;

public class CategoryServiceImpl implements CategoryService {
	private static final CategoryServiceImpl service = new CategoryServiceImpl();
	private static final DAOFactory daoFactory = PostgresqlDAOFactory.getInstance();

	private static final String EN = "en";
	private static final String RU = "ru";

	private CategoryServiceImpl() {
	}

	public static CategoryServiceImpl getInstance() {
		return service;
	}

	public List<Category> getCategories(Object lang) throws GettingDataException {
		try {
			List<Category> categories = null;
			CategoryDAO catdao = daoFactory.getCategoryDAO();
			if (lang != null) {
				categories = catdao.getAllCategories(lang.toString());
			} else {
				categories = catdao.getAllCategories(EN);
			}
			ImageDAO imgdao = daoFactory.getImageDAO();
			for (Category c : categories) {
				c.setImage(imgdao.getImage(c.getImage().getId()));
			}
			return categories;
		} catch (DAOException e) {
			throw new GettingDataException("DAOException occurred during getting categories", e);
		}
	}

	public Category getCategory(Integer id, Object lang)
			throws DataNotFoundException, GettingDataException, WrongDataException {
		try {
			Category category = null;
			if (id == null) {
				throw new WrongDataException("Wrong category ID");
			}
			CategoryDAO catdao = daoFactory.getCategoryDAO();
			if (lang != null) {
				category = catdao.getCategory(id, lang.toString());
			} else {
				category = catdao.getCategory(id, EN);
			}
			if (category == null) {
				throw new DataNotFoundException("Category wasn't found");
			} else {
				MessageDAO messdao = daoFactory.getMessageDAO();
				List<Message> messages = messdao.getAllMessagesOfCategory(id);
				Account ac;
				ImageDAO imgdao = daoFactory.getImageDAO();
				for (Message m : messages) {
					AccountDAO acdao = daoFactory.getAccountDAO();
					ac = acdao.getAccount(m.getAuthor().getId());
					ac.setPhoto(imgdao.getImage(ac.getPhoto().getId()));
					ac.setRating(acdao.rating(ac.getId()));
					m.setAuthor(ac);
				}
				category.setMessages(messages);
				category.setImage(imgdao.getImage(category.getImage().getId()));
			}
			return category;
		} catch (DAOException e) {
			throw new GettingDataException("DAOException occurred during getting category", e);
		}
	}

	public Category getCategoryText(Integer id, Object lang) throws GettingDataException, WrongDataException {
		try {
			Category category = null;
			if (id == null || lang == null) {
				throw new WrongDataException("Wrong data");
			}
			CategoryDAO catdao = daoFactory.getCategoryDAO();
			category = catdao.getCategoryText(id, lang.toString());
			return category;
		} catch (DAOException e) {
			throw new GettingDataException("DAOException occurred during getting category", e);
		}
	}

	public void addCategory(Category categoryRu, Category categoryEn) throws WrongDataException, GettingDataException {
		try {
			Integer id;
			if (categoryRu == null && categoryEn == null) {
				throw new WrongDataException("Not all data");
			}
			CategoryDAO catdao = daoFactory.getCategoryDAO();
			if (categoryRu != null) {
				catdao.insert(categoryRu);
				id = getCategoryIdByTitle(categoryRu.getName());
				categoryRu.setId(id);
				addCategoryText(categoryRu, RU);
				if (categoryEn != null) {
					categoryEn.setId(id);
					addCategoryText(categoryEn, EN);
				}
			} else {
				if (categoryEn != null) {
					catdao.insert(categoryEn);
					id = getCategoryIdByTitle(categoryEn.getName());
					categoryEn.setId(id);
					addCategoryText(categoryEn, EN);
				}
			}
		} catch (DAOException e) {
			throw new GettingDataException("DAOException occurred during adding category", e);
		} catch (DataNotFoundException e) {
			throw new GettingDataException("Category wasn't added", e);
		}

	}

	private void addCategoryText(Category category, String lang) throws GettingDataException, WrongDataException {
		try {
			if (category == null || lang == null) {
				throw new WrongDataException("Not all data");
			}
			CategoryDAO catdao = daoFactory.getCategoryDAO();
			catdao.insertText(category, lang);
		} catch (DAOException e) {
			throw new GettingDataException("DAOException occurred during adding category text", e);
		}
	}

	public Integer getCategoryIdByTitle(String name)
			throws GettingDataException, WrongDataException, DataNotFoundException {
		try {
			Integer id = null;
			if (name == null || CommandConstant.EMPTY.equals(name)) {
				throw new WrongDataException("Wrong category name");
			}
			CategoryDAO catdao = daoFactory.getCategoryDAO();
			id = catdao.getCategoryId(name);
			if (id == null) {
				throw new DataNotFoundException("Category id wasn't found");
			}
			return id;
		} catch (DAOException e) {
			throw new GettingDataException("DAOException occurred during getting category id by name", e);
		}
	}

	public void deleteCategory(Integer categoryId) throws WrongDataException, GettingDataException {
		try {
			if (categoryId == null) {
				throw new WrongDataException("Wrong category ID");
			}
			CategoryDAO catdao = daoFactory.getCategoryDAO();
			catdao.delete(categoryId);
		} catch (DAOException e) {
			throw new GettingDataException("DAOException occurred during deleting category", e);
		}
	}

	public void updateCategory(Category categoryRu, Category categoryEn)
			throws GettingDataException, WrongDataException {
		try {
			if (categoryRu == null && categoryEn == null) {
				throw new WrongDataException("Not all data");
			}
			CategoryDAO catdao = daoFactory.getCategoryDAO();
			if (categoryRu != null) {
				catdao.update(categoryRu);
				if (getCategoryText(categoryRu.getId(), RU) != null) {
					updateCategoryText(categoryRu, RU);
				} else {
					addCategoryText(categoryRu, RU);
				}
				if (categoryEn != null) {
					if (getCategoryText(categoryEn.getId(), EN) != null) {
						updateCategoryText(categoryEn, EN);
					} else {
						addCategoryText(categoryEn, EN);
					}
				} else {
					deleteCategoryText(categoryRu.getId(), EN);
				}
			} else {
				if (categoryEn != null) {
					deleteCategoryText(categoryEn.getId(), RU);
					catdao.update(categoryEn);
					if (getCategoryText(categoryEn.getId(), EN) != null) {
						updateCategoryText(categoryEn, EN);
					} else {
						addCategoryText(categoryEn, EN);
					}
				}
			}
		} catch (DAOException e) {
			throw new GettingDataException("DAOException occurred during updating category", e);
		}

	}

	private void updateCategoryText(Category category, String lang) throws GettingDataException, WrongDataException {
		try {
			if (category == null || lang == null) {
				throw new WrongDataException("Not all data");
			}
			CategoryDAO catdao = daoFactory.getCategoryDAO();
			catdao.updateText(category, lang);
		} catch (DAOException e) {
			throw new GettingDataException("DAOException occurred during updating category text", e);
		}

	}

	private void deleteCategoryText(Integer categoryId, String lang) throws GettingDataException, WrongDataException {
		try {
			if (categoryId == null || lang == null) {
				throw new WrongDataException("Not all data");
			}
			CategoryDAO catdao = daoFactory.getCategoryDAO();
			catdao.deleteText(categoryId, lang);
		} catch (DAOException e) {
			throw new GettingDataException("DAOException occurred during deleting category", e);
		}

	}
}
