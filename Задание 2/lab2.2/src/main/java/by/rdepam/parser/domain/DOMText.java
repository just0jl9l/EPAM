package by.rdepam.parser.domain;

public class DOMText implements DOMNode {
	private String text;
	private DOMElement owner;
	
	public DOMText(){
		text= new String();
		owner=new DOMElement();
	}

	public DOMText(String text, DOMElement owner) {
		this.text = text;
		this.owner = owner;
	}

	public String getWholeText() {
		return this.text;
	}

	public void replaceWholeText(String text) {
		this.text = text;
	}

	public short getElementType() {
		return DOMConstants.TEXT;
	}

	public String getNodeValue() {
		return this.text;
	}

	public DOMNodeList getChildNodes() {
		return null;
	}

	public DOMAttributeList getAttributes() {
		return null;
	}

	public DOMNode getParentNode() {
		return owner;
	}
}
