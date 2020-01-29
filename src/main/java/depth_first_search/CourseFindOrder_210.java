package depth_first_search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 现在你总共有 n 门课需要选，记为 0 到 n-1。
 * <p>
 * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们: [0,1]
 * <p>
 * 给定课程总量以及它们的先决条件，返回你为了学完所有课程所安排的学习顺序。
 * <p>
 * 可能会有多个正确的顺序，你只要返回一种就可以了。如果不可能完成所有课程，返回一个空数组。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 2, [[1,0]]
 * 输出: [0,1]
 * 解释: 总共有 2 门课程。要学习课程 1，你需要先完成课程 0。因此，正确的课程顺序为 [0,1] 。
 * 示例 2:
 * <p>
 * 输入: 4, [[1,0],[2,0],[3,1],[3,2]]
 * 输出: [0,1,2,3] or [0,2,1,3]
 * 解释: 总共有 4 门课程。要学习课程 3，你应该先完成课程 1 和课程 2。并且课程 1 和课程 2 都应该排在课程 0 之后。
 *      因此，一个正确的课程顺序是 [0,1,2,3] 。另一个正确的排序是 [0,2,1,3] 。
 * 说明:
 * <p>
 * 输入的先决条件是由边缘列表表示的图形，而不是邻接矩阵。详情请参见图的表示法。
 * 你可以假定输入的先决条件中没有重复的边。
 * 提示:
 * <p>
 * 这个问题相当于查找一个循环是否存在于有向图中。如果存在循环，则不存在拓扑排序，因此不可能选取所有课程进行学习。
 * 通过 DFS 进行拓扑排序 - 一个关于Coursera的精彩视频教程（21分钟），介绍拓扑排序的基本概念。
 * 拓扑排序也可以通过 BFS 完成。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/course-schedule-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CourseFindOrder_210 {
    public static void main(String[] args) {
        CourseFindOrder_210 example = new CourseFindOrder_210();
        int[] result = example.findOrder(2, new int[][]{{1, 0}});
        for (int temp : result) {
            System.out.print(temp);
        }
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        return fun1(numCourses, prerequisites);
    }

    /**
     * 参考代码：入度表、广度优先遍历
     */
    private int[] fun1(int numCourses, int[][] prerequisites) {
        /*
         * 拓扑排序
         * 入度：设有向图中有一结点v，其入度即为当前所有从其他结点出发，终点为v的的边的数目。
         * 出度：设有向图中有一结点v，其出度即为当前所有起点为v，指向其他结点的边的数目。
         * 每次从入度为0的结点开始，加入队列。入度为0，表示没有前置结点。
         * 处理入度为0的结点，把这个结点指向的结点的入度-1。
         * 再把新的入度为0的结点加入队列。
         * 如果队列都处理完毕，但是和总结点数不符，说明有些结点形成环。
         */
        // 记录所有节点的入度
        int tempNumCourses = numCourses;
        int[] indegrees = new int[tempNumCourses];
        for (int[] cp : prerequisites) {
            indegrees[cp[0]] += cp.length - 1;
        }
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < tempNumCourses; i++) {
            if (indegrees[i] == 0) queue.addLast(i);
        }
        List<Integer> tempResult = new ArrayList<>();
        while (!queue.isEmpty()) {
            // pre目前没有前置节点，那么删除pre并修改pre指向的后继节点的入度-1
            Integer pre = queue.removeFirst();
            tempResult.add(pre);
            tempNumCourses--;
            for (int[] req : prerequisites) {
                if (req[0] == pre || indegrees[req[0]] <= 0) continue;
                for (int i = 1; i < req.length; i++) {
                    if (req[i] == pre) {
                        indegrees[req[0]]--;
                        break;
                    }
                }
                if (indegrees[req[0]] <= 0) queue.add(req[0]);
            }
        }
        if (tempNumCourses == 0) {
            int[] result = new int[numCourses];
            for (int i = 0; i < numCourses; i++) {
                result[i] = tempResult.get(i);
            }
            return result;
        } else {
            return new int[]{};
        }
    }
}
