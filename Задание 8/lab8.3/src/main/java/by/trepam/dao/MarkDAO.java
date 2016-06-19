package by.trepam.dao;

import java.util.List;

import by.trepam.dao.exception.DAOException;
import by.trepam.domain.Mark;

public interface MarkDAO {

	void insert(Mark mark,int answerID) throws DAOException;
	void delete(int authorID,int answerID) throws DAOException;
	List<Mark> getAllMarksOfAnswer(int ancwerID) throws DAOException;
	void update(Mark mark,int answerID) throws DAOException;
}
