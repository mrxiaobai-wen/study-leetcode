package design.easy;

/**
 * 面试题 03.01. 三合一
 *
 * 三合一。描述如何只用一个数组来实现三个栈。
 * 你应该实现push(stackNum, value)、pop(stackNum)、isEmpty(stackNum)、peek(stackNum)方法。stackNum表示栈下标，value表示压入的值。
 *
 * 构造函数会传入一个stackSize参数，代表每个栈的大小。
 *
 * 示例1:
 *
 *  输入：
 * ["TripleInOne", "push", "push", "pop", "pop", "pop", "isEmpty"]
 * [[1], [0, 1], [0, 2], [0], [0], [0], [0]]
 *  输出：
 * [null, null, null, 1, -1, -1, true]
 * 说明：当栈为空时`pop, peek`返回-1，当栈满时`push`不压入元素。
 * 示例2:
 *
 *  输入：
 * ["TripleInOne", "push", "push", "push", "pop", "pop", "pop", "peek"]
 * [[2], [0, 1], [0, 2], [0, 3], [0], [0], [0], [0]]
 *  输出：
 * [null, null, null, null, 2, 1, -1, -1]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/three-in-one-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class TripleInOne {

    private int stackSize = 0;
    private int[] stack;
    private int[] top;
    public TripleInOne(int stackSize) {
        this.stackSize = stackSize;
        stack = new int[stackSize * 3];
        top = new int[3];
    }

    public void push(int stackNum, int value) {
        if (top[stackNum] >= stackSize) {
            return;
        } else {
            stack[stackNum * stackSize + top[stackNum]++] = value;
        }
    }

    public int pop(int stackNum) {
        if (top[stackNum] <= 0) {
            return -1;
        } else {
            top[stackNum]--;
            return stack[stackNum * stackSize + top[stackNum]];
        }
    }

    public int peek(int stackNum) {
        if (isEmpty(stackNum)) {
            return -1;
        }
        return stack[stackNum * stackSize + top[stackNum] - 1];
    }

    public boolean isEmpty(int stackNum) {
        return top[stackNum] <= 0;
    }
}

/**
 * Your TripleInOne object will be instantiated and called as such:
 * TripleInOne obj = new TripleInOne(stackSize);
 * obj.push(stackNum,value);
 * int param_2 = obj.pop(stackNum);
 * int param_3 = obj.peek(stackNum);
 * boolean param_4 = obj.isEmpty(stackNum);
 */