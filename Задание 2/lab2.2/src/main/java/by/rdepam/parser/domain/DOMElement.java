package by.rdepam.parser.domain;
public class DOMElement implements DOMNode {
	private String name;
	private DOMAttributeList attrs;
	private DOMNodeList children;
	private DOMElement parent;

	public DOMElement() {
		name = new String();
		parent = new DOMElement();
		children = new DOMNodeList();
		attrs = new DOMAttributeList();
	}

	public DOMElement(String name, DOMElement parent) {
		this.name = name;
		this.parent = parent;;
		children = new DOMNodeList();
		attrs = new DOMAttributeList();
	}

	public short getElementType() {
		return DOMConstants.ELEMENT;
	}

	public String getNodeValue() {
		return this.name;
	}

	public DOMNode getParentNode() {
		return this.parent;
	}

	public DOMNodeList getChildNodes() {
		return this.children;
	}

	public DOMAttributeList getAttributes() {
		return this.attrs;
	}

	public boolean hasAttribute() {
		return (attrs.size() != 0);
	}

	public String getAttribute(String name) {
		return attrs.get(name).getValue();
	}

	public DOMAttribute getAttributeNode(String name) {
		return attrs.get(name);
	}

	public void setAttribute(String name, String value) {
		attrs.set(name, value);
	}

	public void removeAttribute(String name) {
		attrs.remove(name);
	}

	public DOMNodeList getElementByTagName(String name) {
		return children.getByTagName(name);
	}

	public void addChild(DOMNode n) {
		children.add(n);
	}
	
	public String getTextElement(){
		return children.getTextElement().getWholeText();
	}

	public void setAttributes(DOMAttributeList at) {
		attrs=at;		
	}

	public void setChildren(DOMNodeList els) {
		children=els;
		
	}
}
