public interface DOMNode {
	short getElementType();

	String getNodeValue();

	DOMNode getParentNode();

	DOMNodeList getChildNodes();

	DOMAttributeList getAttributes();
}
