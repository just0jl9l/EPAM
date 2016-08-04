package by.trepam.like_it.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class AnswerRating extends TagSupport {

	private static final long serialVersionUID = 1L;
	private Integer value;
	private String action;
	private Integer answerId;

	public void setValue(String value) {
		this.value = new Integer(value);
	}

	public void setAction(String action) {
		this.action = action;
	}

	public void setAnswer(String answer) {
		this.answerId = new Integer(answer);
	}

	@Override
	public int doStartTag() throws JspException {
		try {
			pageContext.getOut()
					.write("<form class=\"language-form\" action=\"" + action + "\" method=\"post\">"
							+ "<input type=\"hidden\" name=\"command\" value=\"rate\" />"
							+ "<input type=\"hidden\" name=\"answer\" value=\"" + answerId + "\" />"
							+ "<input type=\"hidden\" name=\"mark\" value=\"" + value + "\" />"
							+ "<input type=\"submit\" class=\"mark_button\" value=\"" + value + "\" /></form>");
		} catch (IOException e) {
			throw new JspException(e.getMessage());
		}
		return SKIP_BODY;
	}

}
