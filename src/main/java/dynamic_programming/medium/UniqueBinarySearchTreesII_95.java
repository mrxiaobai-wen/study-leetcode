package dynamic_programming.medium;

import common.TreeNode;

import java.util.*;

/**
 * 95. 不同的二叉搜索树 II
 * 给定一个整数 n，生成所有由 1 ... n 为节点所组成的二叉搜索树。
 * <p>
 * 示例:
 * <p>
 * 输入: 3
 * 输出:
 * [
 * [1,null,3,2],
 * [3,2,null,1],
 * [3,1,null,null,2],
 * [2,1,3],
 * [1,null,2,null,3]
 * ]
 * 解释:
 * 以上的输出对应以下 5 种不同结构的二叉搜索树：
 * <p>
 * 1         3     3      2      1
 * \       /     /      / \      \
 * 3     2     1      1   3      2
 * /     /       \                 \
 * 2     1         2                 3
 */
public class UniqueBinarySearchTreesII_95 {

    public List<TreeNode> generateTrees(int n) {
        // 递归
        return recurse(n);
        // todo 动态规划实现
    }

    /**
     * 递归
     */
    private List<TreeNode> recurse(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }
        Map<String, List<TreeNode>> container = new HashMap<>();
        return fun(1, n, container);
    }

    private List<TreeNode> fun(int min, int max, Map<String, List<TreeNode>> container) {
        if (min > max) {
            return Collections.emptyList();
        }
        String curKey = min + "_" + max;
        if (container.containsKey(curKey)) {
            return container.get(curKey);
        }
        List<TreeNode> curList = new ArrayList<>();
        for (int i = min; i <= max; i++) {
            List<TreeNode> leftList = fun(min, i - 1, container);
            List<TreeNode> rightList = fun(i + 1, max, container);
            if (leftList.size() > 0 && rightList.size() > 0) {
                for (TreeNode left : leftList) {
                    for (TreeNode right : rightList) {
                        TreeNode node = new TreeNode(i);
                        node.left = left;
                        node.right = right;
                        curList.add(node);
                    }
                }
            } else if (leftList.size() > 0) {
                for (TreeNode left : leftList) {
                    TreeNode node = new TreeNode(i);
                    node.left = left;
                    curList.add(node);
                }
            } else if (rightList.size() > 0) {
                for (TreeNode right : rightList) {
                    TreeNode node = new TreeNode(i);
                    node.right = right;
                    curList.add(node);
                }
            } else {
                TreeNode node = new TreeNode(i);
                curList.add(node);
            }
        }
        container.put(min + "_" + max, curList);
        return curList;
    }
}
