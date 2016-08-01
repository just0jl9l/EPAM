package by.trepam.like_it.service;

import by.trepam.like_it.domain.Answer;
import by.trepam.like_it.domain.Mark;
import by.trepam.like_it.service.exception.ServiceException;

public interface AnswerService {
	
	void rating(Mark mark, int answer_id) throws ServiceException;
	void addAnswer(Answer answer,int messageID) throws ServiceException;
}
