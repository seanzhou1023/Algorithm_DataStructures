package stack;

/*
 * Created by eric on 11/7/16.
 */
public class T150_Reverse_Polish_Notation {
    public int evalRPN(String[] tokens) {
        int[] stack = new int[tokens.length];
        int top = 0;
        for(String token: tokens){
            if(!"+-*/".contains(token)){
                stack[top++] = Integer.valueOf(token);
                continue;
            }
            int v2 = stack[--top], v1 = stack[--top];
            switch(token.charAt(0)){
                case '+': v1 += v2;break;
                case '-': v1 -= v2;break;
                case '*': v1 *= v2;break;
                case '/': v1 /= v2;break;
                default:
            }
            stack[top++] = v1;
        }
        return stack[--top];
    }
}
