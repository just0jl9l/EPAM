package by.trepam.like_it.dao;

import java.util.List;

import by.trepam.like_it.dao.exception.DAOException;
import by.trepam.like_it.domain.Answer;


public interface AnswerDAO {

	void insert(Answer answer,Integer messageId)throws DAOException ;
	void delete(Integer answerId)throws DAOException ;
	Answer getAnswer(Integer answerId)throws DAOException ;
	List<Answer> getAllAnswersOfMessage(Integer messageId)throws DAOException ;
	void update(Answer answer,Integer messageId) throws DAOException ;
	Double rating(Integer answerId)throws DAOException ;
}
