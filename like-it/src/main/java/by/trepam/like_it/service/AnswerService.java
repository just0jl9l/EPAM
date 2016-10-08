package by.trepam.like_it.service;

import by.trepam.like_it.domain.Answer;
import by.trepam.like_it.domain.Mark;
import by.trepam.like_it.service.exception.DataNotFoundException;
import by.trepam.like_it.service.exception.GettingDataException;
import by.trepam.like_it.service.exception.WrongDataException;

/**
 * Interface class of service layer. It provides set of available operations on
 * answers.
 *
 */

public interface AnswerService {

	/**
	 * Method adds mark of answer with answerId, if it exists. If this answer
	 * already has mark of this mark's author, mark will be overwrite. If mark
	 * and answer have the same author, this mark won't be inserted.
	 * 
	 * @param mark
	 * @param answerId
	 * @throws GettingDataException
	 * @throws WrongDataException
	 * @throws DataNotFoundException
	 */
	void rating(Mark mark, Integer answerId) throws GettingDataException, WrongDataException, DataNotFoundException;

	/**
	 * Method adds answer to message with messageId, if this message exists.
	 * 
	 * @param answer
	 * @param messageId
	 * @throws GettingDataException
	 * @throws WrongDataException
	 */
	void addAnswer(Answer answer, Integer messageId) throws GettingDataException, WrongDataException;
}
