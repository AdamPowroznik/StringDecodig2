import java.util.Stack;

public class StringDecoder2 {
	public int solution(String S) {
	    Stack<Integer> stack = new Stack<Integer>();
			int currentNumber = -1;
			outerLoop:
			while(!S.isEmpty()) 
			{
				String commandString = "";
				S.trim();
				int whindex = -1;
				for(int i =0; i<S.length(); i++)
				{	
					if(Character.isWhitespace(S.charAt(i))) 
					{
						whindex = i;
						break;
					}
				}
				if(whindex >= 0)
				{
					
					commandString = S.substring(0, whindex);
					S = S.substring(whindex+1);
				}
				else
				{
						commandString = S;
						S = "";
				}
				
				
				if(commandString.equalsIgnoreCase("DUP")) {
					if(!stack.isEmpty()) {
						stack.push(currentNumber);
					}
				}
				else if(commandString.equalsIgnoreCase("POP")) {
					if(!stack.isEmpty()) {
						stack.pop();
					}
				}
				else if(commandString.equals("+")) {
					if(stack.size()>=2) {
					    try
					    {
					        int newValue = Math.addExact(stack.peek(), stack.elementAt(stack.size()-2));
					        stack.pop();
					        stack.pop();
					        stack.push(newValue);
					        currentNumber = newValue;
					    }
					    catch (ArithmeticException ex)
					    {
					       // System.out.println("IntOverflow\n");
					        return -1;
					    }
					}
					else
					    return -1;
					
				}
				else if(commandString.equals("-")) {
					if(stack.size()>=2) {
					    try
					    {
					        int newValue = Math.subtractExact(stack.peek(), stack.elementAt(stack.size()-2));
					        stack.pop();
					        stack.pop();
					        stack.push(newValue);
					        currentNumber = newValue;
					    }
					    catch (ArithmeticException ex)
					    {
					        //System.out.println("IntUnderflow\n");
					        return -1;
					    }
					}
					else
					    return -1;
				}
				else {
					try 
					{ 
				        Integer.parseInt(commandString);
				        currentNumber = Integer.parseInt(commandString);//wont go if commandString is not an valid Integer
				        stack.push(currentNumber);
				    } catch(Exception e) { 
				    	System.out.println("Nieprawid³owa liczba\n");
				    	continue outerLoop;
				    } 
				}
				
				//DEBUG SECTION
				//System.out.println("Command: "+commandString);
				//if(stack.size()>0)
				//	System.out.println("Top value: "+stack.peek());
				//System.out.println("Stack size: "+stack.size()+"\n");
			}
			
			if(stack.size()>0)
				return stack.peek();
			else 
				return -1;
		}
}
