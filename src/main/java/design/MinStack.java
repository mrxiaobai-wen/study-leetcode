package design;

import java.util.Stack;

/**
 * 155. 最小栈
 * <p>
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 * <p>
 * push(x) —— 将元素 x 推入栈中。 pop() —— 删除栈顶的元素。 top() —— 获取栈顶元素。 getMin() —— 检索栈中的最小元素。  
 * <p>
 * 示例:
 * <p>
 * 输入： ["MinStack","push","push","push","getMin","pop","top","getMin"] [[],[-2],[0],[-3],[],[],[],[]]
 * <p>
 * 输出： [null,null,null,null,-3,null,0,-2]
 * <p>
 * 解释： MinStack minStack = new MinStack(); minStack.push(-2); minStack.push(0); minStack.push(-3); minStack.getMin();
 * --> 返回 -3. minStack.pop(); minStack.top();      --> 返回 0. minStack.getMin();   --> 返回 -2.  
 * <p>
 * 提示：
 * <p>
 * pop、top 和 getMin 操作总是在 非空栈 上调用。
 * <p>
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/min-stack 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MinStack {

    Stack<Integer> dataStack;
    Stack<Integer> minStack;

    public MinStack() {
        dataStack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int x) {
        dataStack.push(x);
        if (minStack.isEmpty()) {
            minStack.push(x);
        } else {
            minStack.push(Math.min(x, minStack.peek()));
        }
    }

    public void pop() {
        minStack.pop();
        dataStack.pop();
    }

    public int top() {
        return dataStack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
