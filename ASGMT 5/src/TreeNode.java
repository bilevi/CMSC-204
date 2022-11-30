/*
 * Class: CMSC 204
 * Instructor: Monshi
 * Description: A program that converts morse code into English and vice versa.
 * Due: 11/30/2022
 * Platform/compiler: Eclipse
 * I pledge that I have completed the programming assignment independently.
   I have not copied the code from a student or any source.
   I have not given my code to any student.
   Print your Name here: B. Leigh Vining
 */

public class TreeNode<T> {
	
	T data;
	TreeNode<T> leftChild, rightChild;
	
	/**
	 * 	
	 * @param dataNode  A node that points to specific data
	 */
	public TreeNode (T dataNode) {
		data = dataNode;
		
		leftChild = null;
		rightChild = null;
	}
	
	/**
	 * 	
	 * @param node  The node to be deep copied
	 */
	public TreeNode (TreeNode<T> node) {
		TreeNode<T> deepCopy = new TreeNode<T>(node.data);
	}
	

	/**
	 * 	
	 * @return This node's data
	 */
	public T getData() {
		return data;
	}
}
