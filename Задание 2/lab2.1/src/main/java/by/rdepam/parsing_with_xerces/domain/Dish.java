package by.rdepam.parsing_with_xerces.domain;
import java.util.List;

public class Dish {
	private String id;
	private String photo;
	private String name;
	private Description descr;
	private Portion portion;
	
	public Dish(String name2, String id2, String photo2, List<Description> des, List<Portion> portion2) {
		id=id2;
		photo=photo2;
		name=name2;
		if(des.size()>0){
			descr=des.get(0);
		}else{
			descr=new Description();
		}
		if(portion2.size()>0){
			portion=portion2.get(0);
		}else{
			portion=new Portion();
		}
	}
	
	public String show(){
		String str= " ID: "+id;
		if(photo!=null && photo.length()>0){
			str+=";\n Photo: "+photo;
		}
		if(name!=null && name.length()>0){
			str+=";\n Name: "+name;
		}
		if(descr!=null){
			str+=";\n Description: "+descr.show();
		}
		if(portion!=null){
			str+=";\n Portion: "+portion.show()+".";
		}
		return str;
	}	
	
}
