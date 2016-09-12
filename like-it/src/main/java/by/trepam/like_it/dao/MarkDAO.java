package by.trepam.like_it.dao;

import java.util.List;

import by.trepam.like_it.dao.exception.DAOException;
import by.trepam.like_it.domain.Mark;

public interface MarkDAO {

	void insert(Mark mark,Integer answerId) throws DAOException;
	void delete(Integer authorId,Integer answerId) throws DAOException;
	List<Mark> getAllMarksOfAnswer(Integer ancwerId) throws DAOException;
	void update(Mark mark,Integer answerId) throws DAOException;
}
