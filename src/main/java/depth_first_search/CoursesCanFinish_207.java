package depth_first_search;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 现在你总共有 n 门课需要选，记为 0 到 n-1。
 * <p>
 * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们: [0,1]
 * <p>
 * 给定课程总量以及它们的先决条件，判断是否可能完成所有课程的学习？
 * <p>
 * 示例 1:
 * <p>
 * 输入: 2, [[1,0]]
 * 输出: true
 * 解释: 总共有 2 门课程。学习课程 1 之前，你需要完成课程 0。所以这是可能的。
 * 示例 2:
 * <p>
 * 输入: 2, [[1,0],[0,1]]
 * 输出: false
 * 解释: 总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0；并且学习课程 0 之前，你还应先完成课程 1。这是不可能的。
 * 说明:
 * <p>
 * 输入的先决条件是由边缘列表表示的图形，而不是邻接矩阵。详情请参见图的表示法。
 * 你可以假定输入的先决条件中没有重复的边。
 * 提示:
 * <p>
 * 这个问题相当于查找一个循环是否存在于有向图中。如果存在循环，则不存在拓扑排序，因此不可能选取所有课程进行学习。
 * 通过 DFS 进行拓扑排序 - 一个关于Coursera的精彩视频教程（21分钟），介绍拓扑排序的基本概念。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/course-schedule
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CoursesCanFinish_207 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        /*
        参考思路：拓扑排序。
         */
        // 解决关键：有向无环图（DAG）

        return fun1(numCourses, prerequisites);
    }

    /**
     * 参考代码：入度表、广度优先遍历
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    private boolean fun1(int numCourses, int[][] prerequisites) {
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
        int[] indegrees = new int[numCourses];
        for (int[] cp : prerequisites) {
            indegrees[cp[0]] += cp.length - 1;
        }
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegrees[i] == 0) queue.addLast(i);
        }
        while (!queue.isEmpty()) {
            // pre目前没有前置节点，那么删除pre并修改pre指向的后继节点的入度-1
            Integer pre = queue.removeFirst();
            numCourses--;
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
        return numCourses == 0;
    }
}
