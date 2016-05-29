package by.trepam.news.domain.criteria;

public class CriteriaNameAuthor implements ICriteria{
	private String name;
	private String author;

	public CriteriaType getCriteriaType() {
		return CriteriaType.NAME_AND_AUTHOR;
	}

	public String[] getParams() {
		String[] s = {name,author};
		return s;
	}
	
	public void setName(String a){
		name=a;
	}
	
	public void setAuthor(String d){
		author=d;
	}

}
