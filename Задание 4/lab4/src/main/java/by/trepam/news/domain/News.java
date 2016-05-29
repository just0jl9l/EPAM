package by.trepam.news.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement( name = "news")
@XmlAccessorType(XmlAccessType.FIELD)
public class News {
	@XmlElement(required = true)
	private String name;
	@XmlElement(name = "provider")
	private Provider providers;
	@XmlElement(required = true, name = "dateOfIssue")
	private String dateOfIssue;
	@XmlElement(required = true)
	private String body;
	
	public News(){
		name=new String();
		providers=new Provider();
		body=new String();		
	}

	public void setProviders(Provider list) {
		this.providers = list;
	}

	public Provider getProviders() {
		return this.providers;
	}
	
	public void setName(String name2){
		this.name=name2;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setDateOfIssue(String date){
		this.dateOfIssue=date;
	}
	
	public String getDateOfIssue(){
		return this.dateOfIssue;
	}
	
	public void setBody(String body2){
		this.body=body2;
	}
	
	public String getBody(){
		return this.body;
	}

	public void addProvider(String provider) {
		providers.addAuthor(provider);
		
	}

	public String show() {
		String str=new String();
		str+="News:\n";
		str+="- name: "+name+'\n';
		str+="- providers: "+providers.show()+'\n';
		str+="- date of issue"+dateOfIssue+'\n';
		str+="- body: "+body+'\n';
		return str;
	}

	public void setProviders(String readLine) {
		int begin = readLine.indexOf(';');
		while(begin>0){
			providers.addAuthor(readLine.substring(0, begin).trim());
			readLine=readLine.substring(begin+1);
			begin=readLine.indexOf(';');
		}
		
	}

}
