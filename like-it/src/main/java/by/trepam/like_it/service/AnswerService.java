package by.trepam.like_it.service;

import by.trepam.like_it.domain.Answer;
import by.trepam.like_it.domain.Mark;
import by.trepam.like_it.service.exception.DataNotFoundException;
import by.trepam.like_it.service.exception.GettingDataException;
import by.trepam.like_it.service.exception.WrongDataException;

public interface AnswerService {
	
	void rating(Mark mark, Integer answerId) throws GettingDataException, WrongDataException, DataNotFoundException;
	void addAnswer(Answer answer,Integer messageId) throws GettingDataException, WrongDataException;
}
