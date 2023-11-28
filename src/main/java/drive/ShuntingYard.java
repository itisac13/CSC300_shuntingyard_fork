package drive;

import java.util.HashMap;
import java.util.Map;

public class ShuntingYard {
    public LinkedList Tokens;

    public OutputQueue ReversePolish;
    public Stack operatorStack;



    public ShuntingYard(){
        this.Tokens = new LinkedList();
        this.ReversePolish = new OutputQueue();
        this.operatorStack = new OpStack();
    }

    //parse a math expression into a linked list
    //input: the math expression as a string
    //parsed result will stored in Tokens linked list
    public void parse(String input){
        for (int i = 0; i < input.length(); i++)
        {

            char character = input.charAt(i);
            this.Tokens.append(String.valueOf(character));

        }

    }

    /*
     * 1.  While there are tokens to be read:
     * 2.        Read a token
     * 3.        If it's a number add it to queue
     * 4.        If it's an operator
     * 5.               While there's an operator on the top of the stack with greater precedence:
     * 6.                       Pop operators from the stack onto the output queue
     * 7.               Push the current operator onto the stack
     * 8.        If it's a left bracket push it onto the stack
     * 9.        If it's a right bracket 
     * 10.            While there's not a left bracket at the top of the stack:
     * 11.                     Pop operators from the stack onto the output queue.
     * 12.             Pop the left bracket from the stack and discard it
     * 13. While there are operators on the stack, pop them to the queue
     */
    //take the tokens from Tokens queue, and stored the reversed polish expression in ReversePolish queue
    public void process(){

        Map<Character, Integer> precedence = new HashMap<>();
        precedence.put('+', 1);
        precedence.put('-', 1);
        precedence.put('*', 2);
        precedence.put('/', 2);
        int size = this.Tokens.getLength();
        Node node = this.Tokens.Head;
        while (true)
        {
            String stringToken = node.Data;
            char charToken = stringToken.charAt(0);
            node = node.NextNode;

            if (node.NextNode == null)
            {
                break;
            }
            // char c = stringToken.charAt(0);
            if (Character.isDigit(stringToken.charAt(0))) {
                ReversePolish.append(stringToken);
            } else if ( charToken == '(') {
                operatorStack.append(stringToken);
            } else if (charToken == ')') {
                while (!operatorStack.isEmpty() && operatorStack.peek().Data.charAt(0) != '(') {
                    ReversePolish.append(operatorStack.pop().Data);
                }
                operatorStack.pop(); // Discard '('
            } else if (precedence.containsKey(stringToken.charAt(0))) {
                while (!operatorStack.isEmpty() && precedence.get(stringToken.charAt(0)) <= precedence.getOrDefault(operatorStack.peek().Data.charAt(0), 0)) {
                    ReversePolish.append(operatorStack.pop().Data);
                }
                operatorStack.push(stringToken);
            }

        }
    }

    /*
     * 1. Tokens on queue dequeue() one by one
     * 2. Each time a number or operand dequeue(), push it to the stack.
     * 3. Each time an operator comes up, pop() operands from the stack, perform the operations,
              and push the result back to the stack.
     * 4. Finished when there are no tokens to read. 
     * 5. The final number on the stack is the result.
     */
    //process use the reverse polish format of expression to process the math result
    //output: the math result of the expression
    public int run(){
        //to do
        throw new Error("waiting for implement");
    }

    
}
