package domain;
import java.util.ArrayList;
import java.util.List;

public class Portion {
	private String amount;
	private List<Integer> weight;
	
	public Portion(String amount2, List<Integer> w) {
		amount=amount2;
		weight=w;
	}

	public Portion() {
		amount=new String();
		weight=new ArrayList<Integer>();
	}

	public String toString(){
		String str=new String();
		if(amount!=null){
			str+="\n   -Amount: "+amount+".";			
		}
		if(weight.size()>0){
			str+="\n   -Weight: ";		
			for(Integer d:weight){
				str+=d.toString();
				str+='/';
			}
			str=str.substring(0, str.length()-1);
		}
		return str;
	}
}
