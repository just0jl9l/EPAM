package by.rdepam.parser.domain;

public class DOMAttribute implements DOMNode {
	private String name;
	private String value;
	private DOMElement owner;

	public DOMAttribute() {
		name = new String();
		value = new String();
		owner = new DOMElement();
	}

	public DOMAttribute(String name, DOMElement owner) {
		this.name = name;
		this.owner = owner;
		value = new String();
	}

	public String getName() {
		return this.name;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String str) {
		this.value = str;
	}

	public short getElementType() {
		return 1;
	}

	public String getNodeValue() {
		return this.value;
	}

	public DOMNode getOwnerNode() {
		return owner;
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
