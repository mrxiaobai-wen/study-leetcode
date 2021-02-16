package design;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * 341. 扁平化嵌套列表迭代器
 * <p>
 * 给你一个嵌套的整型列表。请你设计一个迭代器，使其能够遍历这个整型列表中的所有整数。
 * <p>
 * 列表中的每一项或者为一个整数，或者是另一个列表。其中列表的元素也可能是整数或是其他列表。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [[1,1],2,[1,1]] 输出: [1,1,2,1,1] 解释: 通过重复调用 next 直到 hasNext 返回 false，next 返回的元素的顺序应该是: [1,1,2,1,1]。 示例 2:
 * <p>
 * 输入: [1,[4,[6]]] 输出: [1,4,6] 解释: 通过重复调用 next 直到 hasNext 返回 false，next 返回的元素的顺序应该是: [1,4,6]。
 * <p>
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/flatten-nested-list-iterator 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NestedIterator implements Iterator<Integer> {

    private Stack<NestedInteger> stack;

    /**
     * 当前解法：真迭代器 另一种解法：构造器中递归遍历出来
     */
    public NestedIterator(List<NestedInteger> nestedList) {
        stack = new Stack<>();
        for (int i = nestedList.size() - 1; i >= 0; i--) {
            stack.add(nestedList.get(i));
        }
    }

    @Override
    public Integer next() {
        return stack.pop().getInteger();
    }

    @Override
    public boolean hasNext() {
        if (stack.isEmpty()) {
            return false;
        } else {
            NestedInteger cur = stack.peek();
            if (cur.isInteger()) {
                return true;
            } else {
                cur = stack.pop();
                for (int i = cur.getList().size() - 1; i >= 0; i--) {
                    stack.add(cur.getList().get(i));
                }
                return hasNext();
            }
        }
    }

    //This is the interface that allows for creating nested lists.
    //You should not implement it, or speculate about its implementation
    public interface NestedInteger {

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }
}
