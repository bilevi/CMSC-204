import java.io.*;
import java.util.*;

public class MorseCodeConverter {

	private static MorseCodeTree codeTree = new MorseCodeTree();
	
	public static String printTree() {
		String treeString = "";
		
		for (String letter : codeTree.toArrayList())
		{
			treeString += (letter + " ");
		}
		
		return treeString.substring(0, treeString.length() - 1);
	}
	
	public static String convertToEnglish (String code) {
		String phrase = "";
		String[] fileArray = code.split(" ");
		
		for (int i = 0; i < fileArray.length; i++)
		{
			if (fileArray[i].equals("/"))
			{
				phrase += " ";
			}

			phrase += codeTree.fetch(fileArray[i]);
		}
		
		return phrase;
	}
	
	public static String convertToEnglish (File codeFile)
			throws FileNotFoundException {
		Scanner file;
		String code = "";
		
		try {
			file = new Scanner(codeFile);
			
			while (file.hasNextLine())
			{
				code += file.nextLine();
			}
			
			file.close();
			return convertToEnglish(code);
			
		} catch (FileNotFoundException e)
		{
			return e.getMessage();
		}
	}
}
