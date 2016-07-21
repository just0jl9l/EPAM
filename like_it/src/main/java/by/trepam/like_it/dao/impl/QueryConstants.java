package by.trepam.like_it.dao.impl;

public class QueryConstants {

	public static final String GET_ACCOUNT_BY_ID = "SELECT name, surname, status, photo FROM account WHERE id_account = ?;";
	public static final String INSERT_ACCOUNT = "INSERT INTO account(name, surname, status, login, password) VALUES(?, ?, ?::account_status, ?, ?);";
	public static final String DELETE_ACCOUNT_BY_ID = "DELETE FROM account WHERE id_account = ?;";
	public static final String UPDATE_ACCOUNT = "UPDATE account SET name = ?, surname = ?, photo = ? WHERE id_account = ?;";
	public static final String ACCOUNT_LOG_IN = "SELECT id_account, name, surname, status, photo FROM account WHERE login = ? AND password = ?;";
	public static final String ACCOUNT_RATING_BY_ID = "SELECT avg(m.value) FROM mark AS m WHERE m.id_answer IN (SELECT a.id_answer FROM answer AS a WHERE a.id_author = ?);";

	public static final String INSERT_CATEGORY = "INSERT INTO category(name, description, image) VALUES (?, ?, ?);";
	public static final String DELETE_CATEGORY_BY_ID = "DELETE FROM category WHERE id_category = ?;";
	public static final String GET_CATEGORY_BY_ID = "SELECT name, description, image FROM category WHERE id_category = ?;";
	public static final String UPDATE_CATEGORY = "UPDATE category SET name = ?, description = ?, image = ? WHERE id_category = ?;";
	public static final String GET_ALL_CATEGORIES = "SELECT id_category, name, description, image FROM category;";

	public static final String INSERT_MESSAGE = "INSERT INTO message(name, text, id_category, id_author, date_of_posting) VALUES (?, ?, ?, ?, ?);";
	public static final String DELETE_MESSAGE_BY_ID = "DELETE FROM message WHERE id_message = ?;";
	public static final String GET_MESSAGE_BY_ID = "SELECT name, text, id_author, date_of_posting FROM message WHERE id_message = ?;";
	public static final String GET_ALL_MESSAGES_OF_CATEGORY = "SELECT id_message, name, text, id_author, date_of_posting FROM message WHERE id_category = ?;";
	public static final String UPDATE_MESSAGE = "UPDATE message SET name = ?, text = ?, id_author = ?, date_of_posting = ?, id_category = ? WHERE id_message = ?;";
	
	public static final String INSERT_ANSWER = "INSERT INTO answer(id_message, text, date_of_posting, id_author) VALUES (?, ?, ?, ?);";
	public static final String DELETE_ANSWER_BY_ID = "DELETE FROM answer WHERE id_answer = ?;";
	public static final String GET_ANSWER_BY_ID = "SELECT text, date_of_posting, id_author FROM answer WHERE id_answer = ?;";
	public static final String GET_ALL_ANSWER_OF_MESSAGE = "SELECT id_answer, text, date_of_posting, id_author FROM answer WHERE id_message = ?;";
	public static final String UPDATE_ANSWER = "UPDATE answer SET id_message = ?, text = ?, date_of_posting = ?, id_author = ? WHERE id_answer = ?;";
	public static final String ANSWER_RATING_BY_ID = "SELECT avg(m.value) FROM mark AS m WHERE m.id_answer = ?;";	
	
	public static final String INSERT_MARK = "INSERT INTO mark(id_answer, id_author, value, date_of_voting) VALUES (?, ?, ?, ?);";
	public static final String DELETE_MARK = "DELETE FROM mark WHERE id_answer = ? AND id_author = ?;";
	public static final String GET_ALL_MARKS_OF_ANSWER = "SELECT id_author, value, date_of_voting FROM mark WHERE id_answer = ?;";
	public static final String UPDATE_MARK = "UPDATE mark SET value = ?, date_of_voting = ? WHERE id_answer = ? AND id_author = ?;";	

	public static final String INSERT_IMAGE = "INSERT INTO image(path, format) VALUES (?, ?);";
	public static final String DELETE_IMAGE_BY_ID = "DELETE FROM image WHERE id_image = ?;";
	public static final String GET_IMAGE_BY_ID = "SELECT path, format FROM image WHERE id_image = ?;";
	public static final String UPDATE_IMAGE = "UPDATE image SET path = ?, format = ? WHERE id_image = ?;";
	
}
