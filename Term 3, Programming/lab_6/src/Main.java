import java.util.EmptyStackException;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * Created by Yule Cat on 16.10.2017.
 */

// String builder, string tokenizer, stack for operands
public class Main {
    private static Stack<Character> operands = new Stack();
    private static Stack<Integer> numbers = new Stack();

    public static void main(String[] args){
        try{
            if (args.length == 0) throw new IllegalArgumentException("At least 1 argument required!");
        }
        catch(IllegalArgumentException exc){
            System.out.println(exc);
            return;
        }

        StringTokenizer infix = new StringTokenizer(args[0], "+-*/()", true);

        while(infix.hasMoreTokens()){
            String token = infix.nextToken();
            if (token.equals("(")) {
                operands.push('(');
            }
            else if (token.equals(")")){
                try {
                    while (!operands.peek().equals('(')) {
                        calc(operands.pop());
                    }
                    operands.pop();
                }
                catch(EmptyStackException exc){
                    System.out.println("Invalid brackets notation!");
                    return;
                }
            }
            else if((int)token.charAt(0) >= '0' && (int)token.charAt(0) <= '9'){
                numbers.push(Integer.parseInt(token));
            }
            else{
                char curOp = token.charAt(0);
                try {
                    while (opPriority(operands.peek()) >= opPriority(curOp)) {
                        calc(operands.pop());
                    }
                }
                catch (EmptyStackException exc) {}
                operands.push(curOp);
            }
        }

        try {
            while (!operands.empty()) {
                calc(operands.pop());
            }
        }
        catch(EmptyStackException exc){}

        System.out.println("Res = " + numbers.peek());
    }

    private static void calc(char op){
        try {
            int firstOp = numbers.pop();
            int secondOp = numbers.pop();

            switch (op) {
                case '+':
                    numbers.push(secondOp + firstOp);
                    return;

                case '-':
                    numbers.push(secondOp - firstOp);
                    return;

                case '*':
                    numbers.push(secondOp * firstOp);
                    return;

                case '/':
                    numbers.push(secondOp / firstOp);
                    return;
            }
        }
        catch(EmptyStackException exc){
            System.out.println("Bad notation!");
        }
    }

    private static int opPriority(char operand){
        switch (operand){
            case '+':
            case '-':
                return 1;

            case '*':
            case '/':
                return 2;
        }
        return 0;
    }
}
