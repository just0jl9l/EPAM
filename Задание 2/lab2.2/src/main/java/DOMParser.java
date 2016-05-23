import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class DOMParser {
	private DOMDocument document;

	public void parse(File file) throws IOException {
		FileInputStream inFile = new FileInputStream(file);
		byte[] str = new byte[inFile.available()];
		inFile.read(str);
		String text = new String(str);
		document = new DOMDocument();
		text = remove(text);
		document.setDocumentElement(findElements(text,null).getFirst());
	}

	private DOMNodeList findElements(String els,DOMElement parent) {
		DOMNodeList nodes = new DOMNodeList();
		int begin = els.indexOf('<');
		if(begin<0){
			DOMText text=new DOMText(els.trim(),parent);
			nodes.add(text);
		}
		while (begin > -1) {
			int cur = 0;
			int len = els.length();
			char[] chars = els.toCharArray();
			for (int i = begin; i < len - 1; i++) {
				if (chars[i] == '<') {
					if (chars[i + 1] == '/') {
						cur--;
					} else {
						cur++;
					}
				}
				if (cur == 0) {
					int end = i;
					while (true) {
						if (chars[i] == '>') {
							String curel = els.substring(begin, i + 1);
							els = els.substring(i+1);
							String tag=curel.substring(curel.indexOf('<')+1, curel.indexOf('>'));
							int spase=tag.indexOf(' ');
							DOMElement element;
							if(!(spase>-1)){
								element=new DOMElement(tag,parent);
							}else{
								element=new DOMElement(tag.substring(0,spase),parent);
								element.setAttributes(findAttributes(tag.substring(spase+1),element));
							}
							String substr = curel.substring(curel.indexOf('>')+1,end-begin);
							element.setChildren(findElements(substr,element));
							nodes.add(element);
							break;
						} else {
							i++;
						}
					}
					break;
				}
			}
			begin = els.indexOf('<');
		}
		return nodes;
	}

	private DOMAttributeList findAttributes(String str, DOMElement owner) {
		DOMAttributeList attrs = new DOMAttributeList();
		int eq = str.indexOf('=');
		while (eq > -1) {
			DOMAttribute attr = new DOMAttribute(str.substring(0, eq).trim(), owner);
			eq = str.indexOf('"');
			if(eq<0){
				eq = str.indexOf('\'');
			}
			str = str.substring(eq + 1);
			eq = str.indexOf('"');
			if(eq<0){
				eq = str.indexOf('\'');
			}
			attr.setValue(str.substring(0, eq));
			attrs.add(attr);
			str = str.substring(eq + 1);
			eq = str.indexOf('=');
		}
		return attrs;
	}

	private String remove(String text) {
		while (text.indexOf("<?") > -1) {
			text = text.substring(0, text.indexOf("<?")) + text.substring(text.indexOf("?>") + 2);
		}
		while (text.indexOf("<!--") > -1) {
			text = text.substring(0, text.indexOf("<!--")) + text.substring(text.indexOf("-->") + 3);
		}
		while (text.indexOf("<!") > -1) {
			int m = text.indexOf("<!");
			int cur = 1;
			int len = text.length();
			char[] chars = text.toCharArray();
			for (int i = 0; i < len; i++) {
				if (chars[i] == '<' & m < i) {
					cur++;
				}
				if (chars[i] == '>' & m <= i) {
					cur--;
				}
				if (cur == 0) {
					cur = i;
					text = text.substring(0, m) + text.substring(cur + 1);
					break;
				}
			}
		}
		return text;
	}

	public DOMDocument getDocument() {
		return document;
	}
}
