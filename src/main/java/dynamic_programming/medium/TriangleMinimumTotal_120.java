package dynamic_programming.medium;

import java.util.List;

/**
 * 120. 三角形最小路径和
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 * <p>
 * 例如，给定三角形：
 * <p>
 * [
 * [2],
 * [3,4],
 * [6,5,7],
 * [4,1,8,3]
 * ]
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 * <p>
 * 说明：
 * <p>
 * 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
 */
public class TriangleMinimumTotal_120 {
    public int minimumTotal(List<List<Integer>> triangle) {
        return fun(triangle);
    }

    private int fun(List<List<Integer>> triangle) {
        for (int i = 1; i < triangle.size(); i++) {
            List<Integer> curList = triangle.get(i);
            List<Integer> preList = triangle.get(i - 1);
            for (int j = 0; j < curList.size(); j++) {
                if (j == 0) {
                    curList.set(j, curList.get(j) + preList.get(j));
                } else if (j == curList.size() - 1) {
                    curList.set(j, curList.get(j) + preList.get(j - 1));
                } else {
                    curList.set(j, curList.get(j) + Math.min(preList.get(j), preList.get(j - 1)));
                }
            }
        }
        Integer result = Integer.MAX_VALUE;
        for (int i = 0; i < triangle.get(triangle.size() - 1).size(); i++) {
            int temp = triangle.get(triangle.size() - 1).get(i);
            if (temp < result) {
                result = temp;
            }
        }
        return result;
    }
}
