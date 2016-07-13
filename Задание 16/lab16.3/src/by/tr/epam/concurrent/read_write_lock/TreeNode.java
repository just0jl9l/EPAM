package by.tr.epam.concurrent.read_write_lock;

import java.util.Map;
import java.util.TreeMap;

public class TreeNode {
	private Map<Character, TreeNode> children = new TreeMap<Character, TreeNode>();
	private boolean leaf;
	
	public boolean isLeaf() {
		return leaf;
	}
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}
	public Map<Character, TreeNode> getChildren() {
		return children;
	}
	public void setChildren(Map<Character, TreeNode> children) {
		this.children = children;
	}
}
