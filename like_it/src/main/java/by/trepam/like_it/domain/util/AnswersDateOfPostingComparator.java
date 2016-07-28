package by.trepam.like_it.domain.util;

import java.util.Comparator;
import java.util.Date;

import by.trepam.like_it.domain.Answer;

public class AnswersDateOfPostingComparator implements Comparator<Answer> {
    
    @Override
    public int compare(Answer obj1, Answer obj2) {
    	Date date1 = obj1.getDateOfPosting();
    	Date date2 = obj2.getDateOfPosting();
    	if(date1.before(date2)){
    		return -1;
    	}
    	if(date1.after(date2)){
    		return 1;
    	}
    	return 0;
    }

}
