package by.trepam.news.domain.criteria;

public class CriteriaAuthorDate implements ICriteria{
	private String author;
	private String date;

	public CriteriaType getCriteriaType() {
		return CriteriaType.AUTHOR_AND_DATE;
	}

	public String[] getParams() {
		String[] s = {author,date};
		return s;
	}
	
	public void setAuthor(String a){
		author=a;
	}
	
	public void setDate(String d){
		date=d;
	}

}
