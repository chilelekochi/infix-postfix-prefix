import java.util.Stack;

public class InfixConversion {

    // Function to check precedence
    static int precedence(char ch) {
        switch (ch) {
            case '+':
            case '-':
                return 1;

            case '*':
            case '/':
                return 2;

            case '^':
                return 3;
        }
        return -1;
    }

    // Function to check operand
    static boolean isOperand(char ch) {
        return Character.isLetterOrDigit(ch);
    }

    // Infix to Postfix
    static String infixToPostfix(String exp) {
        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < exp.length(); i++) {
            char ch = exp.charAt(i);

            if (isOperand(ch)) {
                result.append(ch);
            }

            else if (ch == '(') {
                stack.push(ch);
            }

            else if (ch == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result.append(stack.pop());
                }
                stack.pop();
            }

            else {
                while (!stack.isEmpty() &&
                        precedence(stack.peek()) >= precedence(ch)) {
                    result.append(stack.pop());
                }
                stack.push(ch);
            }
        }

        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }

        return result.toString();
    }

    // Reverse string
    static String reverse(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    // Infix to Prefix
    static String infixToPrefix(String exp) {
        // Step 1: Reverse
        StringBuilder reversed = new StringBuilder(exp);
        reversed.reverse();

        // Step 2: Replace brackets
        for (int i = 0; i < reversed.length(); i++) {
            if (reversed.charAt(i) == '(') {
                reversed.setCharAt(i, ')');
            } else if (reversed.charAt(i) == ')') {
                reversed.setCharAt(i, '(');
            }
        }

        // Step 3: Convert to postfix
        String postfix = infixToPostfix(reversed.toString());

        // Step 4: Reverse again
        return reverse(postfix);
    }

    public static void main(String[] args) {
        String expression = "A+B*(C-D)";

        System.out.println("Infix Expression: " + expression);
        System.out.println("Postfix Expression: " + infixToPostfix(expression));
        System.out.println("Prefix Expression: " + infixToPrefix(expression));
    }
}