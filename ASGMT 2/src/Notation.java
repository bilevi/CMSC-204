import java.lang.*;
/*
 * Class: CMSC 204
 * Instructor: Monshi
 * Description: A program with a GUI that changes a string between postfix and infix notation.
 * Due: 10/09/2022
 * Platform/compiler: Eclipse
 * I pledge that I have completed the programming assignment independently.
   I have not copied the code from a student or any source.
   I have not given my code to any student.
   Print your Name here: B. Leigh Vining
 */

/**
 * 
 * @author B. Leigh Vining
 *
 */

public class Notation {

	/**
	 * 
	 * @param postfixExpr  the Postfix expression to be evaluated in String format
	 * @return  the postfix expression in double format
	 * @throws InvalidNotationFormatException  If the format of the postfix expression is invalid
	 */
	public static double evaluatePostfixExpression(String postfixExpr) throws InvalidNotationFormatException {
		MyStack<Double> myStack = new MyStack(postfixExpr.length());
		char[] stringCharArray = postfixExpr.toCharArray();

		try {
			for (char currentChar : stringCharArray) 
			{
				
				if (currentChar == '(' || Character.isDigit(currentChar)) 
				{
					myStack.push(Double.parseDouble(String.valueOf(currentChar))); //turn char into a string, and then a double
				}

				if (currentChar == '+' || currentChar == '-' || currentChar == '*' || currentChar == '/') 
				{
					if (myStack.size() < 2) 
					{
						throw new InvalidNotationFormatException();
					} 
					else {
						double second = myStack.pop();
						double first = myStack.pop();

						if (currentChar == '+') 
						{
							myStack.push(first + second);
						}

						if (currentChar == '-') 
						{
							myStack.push(first - second);

						}

						if (currentChar == '*')
						{
							myStack.push(first * second);

						}

						if (currentChar == '/')
						{
							myStack.push(first / second);

						}
					} // end of else
				} // end of operand if
			} // end of for loop

			if (myStack.size() > 1) 
			{
				throw new InvalidNotationFormatException();
			}

		} catch (StackUnderflowException e) {
			throw new InvalidNotationFormatException();
		} catch (StackOverflowException e) {
			throw new InvalidNotationFormatException();
		}

		return Double.parseDouble(myStack.toString()); //return the tostring, but in a double format
	}

	/**
	 * 
	 * @param postfix  The postfix expression to be evaluated to infix in String format
	 * @return  the converted infix expression
	 * @throws InvalidNotationFormatException  If the format of the postfix expression is invalid
	 */
	public static String convertPostfixToInfix(String postfix) throws InvalidNotationFormatException {
		MyStack<String> myStack = new MyStack<>(postfix.length());
		char[] stringCharArray = postfix.toCharArray();

		try {
			for (char currentChar : stringCharArray) 
			{
				if (Character.isDigit(currentChar)) 
				{
					myStack.push(String.valueOf(currentChar)); //get string value of char
				}

				if (currentChar == '+' || currentChar == '-' || currentChar == '*' || currentChar == '/')
				{
					if (myStack.size() < 2)
					{
						throw new InvalidNotationFormatException();
					} 
					else {
						String second = myStack.pop();
						String first = myStack.pop();

						String result = ("(" + first + String.valueOf(currentChar) + second + ")");
						myStack.push(result);

					} // end of else
				} // end of operand if
			} // end of for loop

			if (myStack.size() > 1) 
			{
				throw new InvalidNotationFormatException();
			}
			
		} catch (StackUnderflowException e) {
			throw new InvalidNotationFormatException();
		} catch (StackOverflowException e) {
			throw new InvalidNotationFormatException();
		}
		
		return myStack.toString();
	}

	/**
	 * 
	 * @param infix  The infix expression to be evaluated to postfix in String format
	 * @return  the converted postfix expression
	 * @throws InvalidNotationFormatException  If the format of the infix expression is invalid
	 */
	public static String convertInfixToPostfix(String infix) throws InvalidNotationFormatException {
		MyQueue<Character> myQueue = new MyQueue<>(infix.length());
		MyStack<Character> myStack = new MyStack<>(infix.length());
		char[] stringCharArray = infix.toCharArray();
		
		try {
			for (char currentChar : stringCharArray)
			{
				if (Character.isDigit(currentChar))
				{
					myQueue.enqueue(currentChar);
				}

				else if (currentChar == '(')
				{
					myStack.push(currentChar);
				}

				else if (currentChar == '+' || currentChar == '-')
				{
					if (!myStack.isEmpty())
					{
						while (myStack.top() == '+' || myStack.top() == '-' || 
								myStack.top() == '*'|| myStack.top() == '/') {
							myQueue.enqueue(myStack.pop());
						}
					}
					myStack.push(currentChar);
				}

				else if (currentChar == '*' || currentChar == '/') 
				{
					if (!myStack.isEmpty()) 
					{
						while (myStack.top() == '*' || myStack.top() == '/') {
							myQueue.enqueue(myStack.pop());
						}
					}
					myStack.push(currentChar);
				}

				if (currentChar == ')') 
				{
					while (!myStack.isEmpty() && myStack.top() != '(') {
						myQueue.enqueue(myStack.pop());
					}

					if (myStack.isEmpty()) 
					{
						throw new InvalidNotationFormatException();
					}

					if (!myStack.isEmpty() && myStack.top() == '(') 
					{
						myStack.pop();
					}
				}

			} // end of for loop

			while (!myStack.isEmpty() && myStack.top() != '(') {
				myQueue.enqueue(myStack.pop());
			}

			return myQueue.toString();

		} catch (QueueOverflowException e) {
			throw new InvalidNotationFormatException();
		} catch (StackUnderflowException e) {
			throw new InvalidNotationFormatException();
		} catch (StackOverflowException e) {
			throw new InvalidNotationFormatException();
		}
	}
	
} //end of class