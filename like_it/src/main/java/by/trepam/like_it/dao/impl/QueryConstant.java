package by.trepam.like_it.dao.impl;

public class QueryConstant {

	public static final String SQL_GET_ACCOUNT_BY_ID = "SELECT login, name, surname, status, photo FROM account WHERE id_account = ?;";
	public static final String SQL_INSERT_ACCOUNT = "INSERT INTO account(name, surname, status, login, password) VALUES(?, ?, ?::account_status, ?, ?);";
	public static final String SQL_DELETE_ACCOUNT_BY_ID = "DELETE FROM account WHERE id_account = ?;";
	public static final String SQL_UPDATE_ACCOUNT = "UPDATE account SET name = ?, surname = ?, photo = ? WHERE id_account = ?;";
	public static final String SQL_ACCOUNT_LOG_IN = "SELECT id_account, name, surname, status, photo FROM account WHERE login = ? AND password = ?;";
	public static final String SQL_ACCOUNT_RATING_BY_ID = "SELECT avg(m.value) FROM mark AS m WHERE m.id_answer IN (SELECT a.id_answer FROM answer AS a WHERE a.id_author = ?);";
	public static final String SQL_FIND_LOGIN = "SELECT * FROM account WHERE login = ?;";

	public static final String SQL_INSERT_CATEGORY = "INSERT INTO category(name, description, image) VALUES (?, ?, ?);";
	public static final String SQL_DELETE_CATEGORY_BY_ID = "DELETE FROM category_text WHERE id_category = ?;DELETE FROM category WHERE id_category = ?;";
	public static final String SQL_GET_CATEGORY_BY_ID = "SELECT g.image, coalesce(t.name, g.name) AS name, coalesce(t.description, g.description) AS description FROM category AS g LEFT JOIN (SELECT * FROM category_text WHERE lang = ?) AS t USING(id_category) WHERE g.id_category=?;";
	public static final String SQL_UPDATE_CATEGORY = "UPDATE category SET name = ?, description = ?, image = ? WHERE id_category = ?;";
	public static final String SQL_GET_ALL_CATEGORIES = "SELECT g.id_category, g.image, coalesce(t.name, g.name) AS fname, coalesce(t.description, g.description) AS description FROM category AS g LEFT JOIN (SELECT * FROM category_text WHERE lang = ?) AS t USING(id_category);";
	public static final String SQL_INSERT_CATEGORY_TEXT = "INSERT INTO category_text(lang, name, description, id_category) VALUES(?, ?, ?, ?);";
	public static final String SQL_GET_CATEGORY_ID_BY_NAME = "SELECT id_category FROM category WHERE name = ?;";
	public static final String SQL_UPDATE_CATEGORY_TEXT = "UPDATE category_text SET name = ?, description = ? WHERE id_category = ? AND lang = ?;";
	public static final String SQL_DELETE_CATEGORY_TEXT = "DELETE FROM category WHERE id_category = ? AND lang = ?;";

	public static final String SQL_INSERT_MESSAGE = "INSERT INTO message(name, text, id_category, id_author, date_of_posting) VALUES (?, ?, ?, ?, ?);";
	public static final String SQL_DELETE_MESSAGE_BY_ID = "DELETE FROM message WHERE id_message = ?;";
	public static final String SQL_GET_MESSAGE_BY_ID = "SELECT name, text, id_author, date_of_posting FROM message WHERE id_message = ?;";
	public static final String SQL_GET_ALL_MESSAGES_OF_CATEGORY = "SELECT id_message, name, text, id_author, date_of_posting FROM message WHERE id_category = ? ORDER BY date_of_posting DESC;";
	public static final String SQL_UPDATE_MESSAGE = "UPDATE message SET name = ?, text = ? WHERE id_message = ?;";
	
	public static final String SQL_INSERT_ANSWER = "INSERT INTO answer(id_message, text, date_of_posting, id_author) VALUES (?, ?, ?, ?);";
	public static final String SQL_DELETE_ANSWER_BY_ID = "DELETE FROM answer WHERE id_answer = ?;";
	public static final String SQL_GET_ANSWER_BY_ID = "SELECT text, date_of_posting, id_author FROM answer WHERE id_answer = ?;";
	public static final String SQL_GET_ALL_ANSWER_OF_MESSAGE = "SELECT id_answer, text, date_of_posting, id_author FROM answer WHERE id_message = ? ORDER BY date_of_posting;";
	public static final String SQL_UPDATE_ANSWER = "UPDATE answer SET id_message = ?, text = ?, date_of_posting = ?, id_author = ? WHERE id_answer = ?;";
	public static final String SQL_ANSWER_RATING_BY_ID = "SELECT avg(m.value) FROM mark AS m WHERE m.id_answer = ?;";	
	
	public static final String SQL_INSERT_MARK = "INSERT INTO mark(id_answer, id_author, value, date_of_voting) VALUES (?, ?, ?, ?);";
	public static final String SQL_DELETE_MARK = "DELETE FROM mark WHERE id_answer = ? AND id_author = ?;";
	public static final String SQL_GET_ALL_MARKS_OF_ANSWER = "SELECT id_author, value, date_of_voting FROM mark WHERE id_answer = ?;";
	public static final String SQL_UPDATE_MARK = "UPDATE mark SET value = ?, date_of_voting = ? WHERE id_answer = ? AND id_author = ?;";	

	public static final String SQL_INSERT_IMAGE = "INSERT INTO image(path, format) VALUES (?, ?);";
	public static final String SQL_DELETE_IMAGE_BY_ID = "DELETE FROM image WHERE id_image = ?;";
	public static final String SQL_GET_IMAGE_BY_ID = "SELECT path, format FROM image WHERE id_image = ?;";
	public static final String SQL_UPDATE_IMAGE = "UPDATE image SET path = ?, format = ? WHERE id_image = ?;";
	
}
