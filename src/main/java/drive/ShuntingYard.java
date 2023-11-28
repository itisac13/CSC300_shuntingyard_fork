package drive;

import java.util.HashMap;
import java.util.Map;

public class ShuntingYard {
    public LinkedList<String> Tokens;

    public Queue<String> ReversePolish;
    public Stack<Character> operatorStack;


    public ShuntingYard(){
        this.Tokens = new LinkedList<String>();
        this.ReversePolish = new Queue<String>();
        this.operatorStack = new Stack<Character>();
    }

    private static int performOperation(char operator, int operand1, int operand2) {
        switch (operator) {
            case '^':
                return (int) Math.pow(operand1, operand2);
            case '+':
                return operand1 + operand2;
            case '-':
                return operand1 - operand2;
            case '*':
                return operand1 * operand2;
            case '/':
                return operand1 / operand2;
            default:
                throw new IllegalArgumentException("Invalid operator");
        }
    }

    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }

    private boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?"); // Check if the string is a number
    }

    private int precedence(char operator) {
        if (operator == '+' || operator == '-') {
            return 1;
        } else if (operator == '*' || operator == '/') {
            return 2;
        } else if (operator == '^') {
            return 3;
        }
        return -1;
    }

    //parse a math expression into a linked list
    //input: the math expression as a string
    //parsed result will stored in Tokens linked list
    public void parse(String expression) {
        StringBuilder numBuilder = new StringBuilder();

        for (char c : expression.toCharArray()) {
            if (Character.isDigit(c)) {
                numBuilder.append(c);
            } else {
                if (numBuilder.length() > 0) {
                    Tokens.append(numBuilder.toString());
                    numBuilder.setLength(0); // Reset the StringBuilder
                }
                if (!Character.isWhitespace(c)) {
                    Tokens.append(Character.toString(c));
                }
            }
        }

        if (numBuilder.length() > 0) {
            Tokens.append(numBuilder.toString());
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

        Node<String> currentNode = Tokens.Head;
        for (int i = 0; i < Tokens.Size; i++) {

            String token = currentNode.Data;
            currentNode = currentNode.NextNode;

            if (isNumeric(token)) {
                ReversePolish.enqueue(token); // Add numbers directly to the output queue
            } else if (isOperator(token.charAt(0))) {
                char currentOperator = token.charAt(0);
                while (!operatorStack.isEmpty() && precedence(operatorStack.peek().Data) >= precedence(currentOperator)) {
                    ReversePolish.enqueue(Character.toString(operatorStack.pop().Data));
                }
                operatorStack.push(currentOperator);
            } else if (token.equals("(")) {
                operatorStack.push('(');
            } else if (token.equals(")")) {
                while (!operatorStack.isEmpty() && operatorStack.peek().Data != '(') {
                    ReversePolish.enqueue(Character.toString(operatorStack.pop().Data));
                }
                operatorStack.pop(); // Discard the '('
            }
        }

        while (!operatorStack.isEmpty()) {
            ReversePolish.enqueue(Character.toString(operatorStack.pop().Data));
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
    private int charDigitToInt(char c)
    {
        return c - '0';
    }
    public int run(){
        Stack<Integer> evaluationStack = new Stack<>();
        Node<String> currentNode = ReversePolish.Head;
        for (int i = 0; i < ReversePolish.Size; i++) {

            String token = currentNode.Data;
            currentNode = currentNode.NextNode;
            if (isNumeric(token)) {
                evaluationStack.push(Integer.parseInt(token));
            } else if (isOperator(token.charAt(0))) {
                int operand2 = evaluationStack.pop().Data;
                int operand1 = evaluationStack.pop().Data;
                int result = performOperation(token.charAt(0), operand1, operand2);
                evaluationStack.push(result);
            }
        }

        return evaluationStack.pop().Data;
    }

    
}
