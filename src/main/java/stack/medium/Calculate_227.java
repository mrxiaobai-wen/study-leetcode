package stack.medium;

import org.junit.Test;

import java.util.Stack;

/**
 * 227. 基本计算器 II
 * <p>
 * 实现一个基本的计算器来计算一个简单的字符串表达式的值。
 * <p>
 * 字符串表达式仅包含非负整数，+， - ，*，/ 四种运算符和空格  。 整数除法仅保留整数部分。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "3+2*2" 输出: 7 示例 2:
 * <p>
 * 输入: " 3/2 " 输出: 1 示例 3:
 * <p>
 * 输入: " 3+5 / 2 " 输出: 5 说明：
 * <p>
 * 你可以假设所给定的表达式都是有效的。 请不要使用内置的库函数 eval。
 * <p>
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/basic-calculator-ii 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Calculate_227 {

    @Test
    public void test() {
        // System.out.println(calculate("3+2*2"));
        System.out.println(calculate("3+5/2"));
    }

    public int calculate(String s) {
        return fun(s);
    }

    private int fun(String s) {
        Stack<Integer> numStack = new Stack<>();
        Stack<Character> operatorStack = new Stack<>();
        int num = 0;
        char[] charArr = s.toCharArray();
        for (int i = 0; i < charArr.length; i++) {
            char temp = charArr[i];
            if (' ' == temp) {
                continue;
            } else if (temp >= '0' && temp <= '9') {
                num = num * 10 + (temp - '0');
            } else {
                numStack.add(num);
                num = 0;
                if (operatorStack.isEmpty()) {
                    operatorStack.add(temp);
                } else {
                    // 比较两个操作符优先级
                    while (!operatorStack.isEmpty() && compareOperator(operatorStack.peek(), temp) >= 0) {
                        int num2 = numStack.pop();
                        int num1 = numStack.pop();
                        numStack.add(cal(num1, num2, operatorStack.pop()));
                    }
                    operatorStack.add(temp);
                }
            }
        }
        numStack.add(num);
        while (!operatorStack.isEmpty()) {
            int num2 = numStack.pop();
            int num1 = numStack.pop();
            numStack.add(cal(num1, num2, operatorStack.pop()));
        }
        return numStack.pop();
    }

    private int cal(int num1, int num2, char operator) {
        if ('+' == operator) {
            return num1 + num2;
        } else if ('-' == operator) {
            return num1 - num2;
        } else if ('*' == operator) {
            return num1 * num2;
        } else {
            return num1 / num2;
        }
    }

    private int compareOperator(char op1, char op2) {
        int num1 = 0;
        if (op1 == '*' || op1 == '/') {
            num1 = 1;
        }
        int num2 = 0;
        if (op2 == '*' || op2 == '/') {
            num2 = 1;
        }
        return num1 - num2;
    }
}
