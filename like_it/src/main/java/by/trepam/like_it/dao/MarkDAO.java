package by.trepam.like_it.dao;

import java.util.List;

import by.trepam.like_it.dao.exception.DAOException;
import by.trepam.like_it.domain.Mark;

public interface MarkDAO {

	void insert(Mark mark,Integer answerID) throws DAOException;
	void delete(Integer authorID,Integer answerID) throws DAOException;
	List<Mark> getAllMarksOfAnswer(Integer ancwerID) throws DAOException;
	void update(Mark mark,Integer answerID) throws DAOException;
}
