package by.trepam.dao;

import java.util.List;

import by.trepam.dao.exception.DAOException;
import by.trepam.domain.Answer;


public interface AnswerDAO {

	void insert(Answer answer,int messageID)throws DAOException ;
	void delete(int answerID)throws DAOException ;
	Answer getAnswer(int answerID)throws DAOException ;
	List<Answer> getAllAnswersOfMessage(int messageID)throws DAOException ;
	void update(Answer answer,int messageID) throws DAOException ;
	double rating(int answerID)throws DAOException ;
}
