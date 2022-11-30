import java.util.ArrayList;

public class MorseCodeTree implements LinkedConverterTreeInterface<String> {

	private TreeNode<String> root;
	
	
	public MorseCodeTree() {
		root = new TreeNode<String>("");
		buildTree();
	}
	
	
	@Override
	public TreeNode<String> getRoot() {
		return root;
	}
	
	@Override
	public void setRoot(TreeNode<String> newNode) {
		root = newNode;
	}
	
	@Override
	public void insert(String code, String result) {
		addNode(root, code, result);
	}
	
	@Override
	public void addNode(TreeNode<String> root, String code, String letter) {
		TreeNode<String> codeNode = root;
		
		if (code.length() == 1)
		{
			if (code.equals("."))
			{
				codeNode.leftChild = new TreeNode<String>(letter);
			}
			else if (code.equals("-"))
			{
				codeNode.rightChild = new TreeNode<String>(letter);
			}
		}
		
		else
		{
			if (code.charAt(0) == '.')
			{
				codeNode = codeNode.leftChild;
			}
			if (code.charAt(0) == '-')
			{
				codeNode = codeNode.rightChild;
			}
			
			addNode(codeNode, code.substring(1), letter);
		}
	}
	
	@Override
	public String fetch(String code) {
		return fetchNode(root, code);
	}
	
	@Override
	public String fetchNode(TreeNode<String> root, String code) {
		TreeNode<String> codeNode = root;
		String letter = "";
		
		if (code.length() == 1)
		{
			if (code.equals("."))
			{
				letter = codeNode.leftChild.getData();
			}
			if (code.equals("-"))
			{
				letter = codeNode.rightChild.getData();
			}
			
			return letter;
		}
		else
		{
			if (code.charAt(0) == '.')
			{
				codeNode = codeNode.leftChild;
			}
			if (code.charAt(0) == '-')
			{
				codeNode = codeNode.rightChild;
			}
			
			return fetchNode(codeNode, code.substring(1));
		}
	}
	
	@Override
	public LinkedConverterTreeInterface<String> delete(String data) 
			throws UnsupportedOperationException {
	}
	
	@Override
	public LinkedConverterTreeInterface<String> update() 
			throws UnsupportedOperationException {	
	}
	
	@Override
	public void buildTree() {
		insert(".", "e");
		insert("-", "t");
		
		insert("..", "i");
		insert(".-", "a");
		insert("-.", "n");
		insert("--", "m");
		
		insert("...", "s");
		insert("..-", "u");
		insert(".-.", "r");
		insert(".--", "w");
		insert("-..", "d");
		insert("-.-", "k");
		insert("--.", "g");
		insert("---", "o");
		
		insert("....", "h");
		insert("...-", "v");
		insert("..-.", "f");
		insert(".-..", "l");
		insert(".--.", "p");
		insert(".---", "j");
		insert("-...", "b");
		insert("-..-", "x");
		insert("-.-.", "c");
		insert("-.--", "y");
		insert("--..", "z");
		insert("--.-", "q");
	}
	
	@Override
	public ArrayList<String> toArrayList() {
		ArrayList<String> listArray = new ArrayList<>();
		
		LNRoutputTraversal(root, listArray);
		return listArray;
	}
	
	@Override
	public void LNRoutputTraversal(TreeNode<String> root, ArrayList<String> list) {
		if (root.leftChild != null)
		{
			LNRoutputTraversal(root.leftChild, list);
		}
		
		list.add(root.getData());
		
		if (root.rightChild != null)
		{
			LNRoutputTraversal(root.rightChild, list);
		}
	}
}
