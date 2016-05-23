
public class ListItem {
	private String text;
	private String price;
	

	public ListItem(String textValue, String textValue2) {
		text=textValue;
		price=textValue2;
	}
	
	public String toString(){
		String str=new String();
		if(text!=null){
			str+="\n      *Text: "+text;
		}
		if(price!=null){
			str+="\n      *Price: "+price;
		}
		return str;
	}
}
