package by.trepam.like_it.dao;

import java.util.List;

import by.trepam.like_it.dao.exception.DAOException;
import by.trepam.like_it.domain.Answer;


public interface AnswerDAO {

	void insert(Answer answer,int messageID)throws DAOException ;
	void delete(int answerID)throws DAOException ;
	Answer getAnswer(int answerID)throws DAOException ;
	List<Answer> getAllAnswersOfMessage(int messageID)throws DAOException ;
	void update(Answer answer,int messageID) throws DAOException ;
	double rating(int answerID)throws DAOException ;
}
