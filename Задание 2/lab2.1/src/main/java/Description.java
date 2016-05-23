import java.util.ArrayList;
import java.util.List;

public class Description {
	private String general;
	private List<ListItem> listItem;
	
	public Description(String general, List<ListItem> listit) {
		this.general=general;
		this.listItem=listit;
	}
	
	public Description() {
		listItem=new ArrayList<ListItem>();
		general=new String();
	}

	public String toString(){
		String str=new String();
		if(general!=null && general.length()>0){
			str+="\n   -General: "+general;
		}
		if(listItem.size()>0){
			str+="\n   -List: ";
		}		
		for(ListItem d:listItem){
			str+=d.toString();
		}
		return str;
	}
}
