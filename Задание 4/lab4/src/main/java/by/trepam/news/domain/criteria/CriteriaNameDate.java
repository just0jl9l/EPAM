package by.trepam.news.domain.criteria;

public class CriteriaNameDate implements ICriteria{
	private String name;
	private String date;

	public CriteriaType getCriteriaType() {
		return CriteriaType.NAME_AND_DATE;
	}

	public String[] getParams() {
		String[] s = {name,date};
		return s;
	}
	
	public void setName(String a){
		name=a;
	}
	
	public void setDate(String d){
		date=d;
	}

}
