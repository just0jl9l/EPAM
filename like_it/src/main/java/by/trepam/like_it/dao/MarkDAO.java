package by.trepam.like_it.dao;

import java.util.List;

import by.trepam.like_it.dao.exception.DAOException;
import by.trepam.like_it.domain.Mark;

public interface MarkDAO {

	void insert(Mark mark,int answerID) throws DAOException;
	void delete(int authorID,int answerID) throws DAOException;
	List<Mark> getAllMarksOfAnswer(int ancwerID) throws DAOException;
	void update(Mark mark,int answerID) throws DAOException;
}
