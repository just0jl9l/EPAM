package by.rdepam.parsing_with_xerces.domain;
import java.util.ArrayList;
import java.util.List;

public class MenuItem {
	private List<Dish> dishs;
	private String id;
	
	public MenuItem(){
		dishs=new ArrayList<Dish>();
	}

	public void setDishs(List<Dish> dishs2) {
		dishs=dishs2;		
	}
	
	public String show(){
		String str= " ID: "+id+":\n\n";
		for(Dish d:dishs){
			str+=d.show();
			str+='\n';
			str+=("-------------------------------------\n");
		}
		return str;
	}

	public void setID(String id) {
		this.id=id;
		
	}
}
