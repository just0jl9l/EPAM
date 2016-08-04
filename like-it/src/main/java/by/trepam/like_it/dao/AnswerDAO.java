package by.trepam.like_it.dao;

import java.util.List;

import by.trepam.like_it.dao.exception.DAOException;
import by.trepam.like_it.domain.Answer;


public interface AnswerDAO {

	void insert(Answer answer,Integer messageID)throws DAOException ;
	void delete(Integer answerID)throws DAOException ;
	Answer getAnswer(Integer answerID)throws DAOException ;
	List<Answer> getAllAnswersOfMessage(Integer messageID)throws DAOException ;
	void update(Answer answer,Integer messageID) throws DAOException ;
	double rating(Integer answerID)throws DAOException ;
}
