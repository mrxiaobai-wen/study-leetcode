package depth_first_search;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格，计算岛屿的数量。一个岛被水包围，并且它是通过水平方向或垂直方向上相邻的陆地连接而成的。你可以假设网格的四个边均被水包围。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * 11110
 * 11010
 * 11000
 * 00000
 * <p>
 * 输出: 1
 * 示例 2:
 * <p>
 * 输入:
 * 11000
 * 11000
 * 00100
 * 00011
 * <p>
 * 输出: 3
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-islands
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NumIslands_200 {
    public int numIslands(char[][] grid) {
        return fun1(grid);
    }

    char land = '1';

    /**
     * 深度优先
     *
     * @return
     */
    private int fun1(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int rowNum = grid.length;
        int colNum = grid[0].length;
        int result = 0;
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                if (grid[i][j] == land) {
                    result++;
                    dfs(grid, i, j);
                }
            }
        }

        return result;
    }

    /**
     * 深度优先遍历
     *
     * @param grid
     * @param row
     * @param col
     */
    private void dfs(char[][] grid, int row, int col) {
        int rowNum = grid.length;
        int colNum = grid[0].length;

        if (row < 0 || row >= rowNum || col < 0 || col >= colNum) {
            // 过界
            return;
        }
        if (grid[row][col] != land) {
            return;
        }
        grid[row][col] = '0';
        dfs(grid, row - 1, col);
        dfs(grid, row + 1, col);
        dfs(grid, row, col - 1);
        dfs(grid, row, col + 1);
    }

    /**
     * 广度优先搜索
     *
     * @param grid
     * @return
     */
    private int fun2(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int rowNum = grid.length;
        int colNum = rowNum > 0 ? grid[0].length : 0;
        int result = 0;
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                if (grid[i][j] == land) {
                    result++;
                    Queue<Integer> queue = new LinkedList<>();
                    queue.add(i * colNum + j);
                    while (!queue.isEmpty()) {
                        int num = queue.poll();
                        int row = num / colNum;
                        int col = num % colNum;
                        grid[row][col] = '0';
                        if (row - 1 >= 0 && grid[row - 1][col] == land) {
                            queue.add((row - 1) * colNum + col);
                        }
                        if (row + 1 < rowNum && grid[row + 1][col] == land) {
                            queue.add((row + 1) * colNum + col);
                        }
                        if (col - 1 >= 0 && grid[row][col - 1] == land) {
                            queue.add(row * colNum + (col - 1));
                        }
                        if (col + 1 < colNum && grid[row][col + 1] == land) {
                            queue.add(row * colNum + (col + 1));
                        }
                    }
                }
            }
        }

        return result;
    }

    /**
     * 参考代码：并查集运算
     *
     * @param grid
     * @return
     */
    private int fun3(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int nr = grid.length;
        int nc = grid[0].length;
        int num_islands = 0;
        UnionFind uf = new UnionFind(grid);
        for (int r = 0; r < nr; ++r) {
            for (int c = 0; c < nc; ++c) {
                if (grid[r][c] == '1') {
                    grid[r][c] = '0';
                    if (r - 1 >= 0 && grid[r - 1][c] == '1') {
                        uf.union(r * nc + c, (r - 1) * nc + c);
                    }
                    if (r + 1 < nr && grid[r + 1][c] == '1') {
                        uf.union(r * nc + c, (r + 1) * nc + c);
                    }
                    if (c - 1 >= 0 && grid[r][c - 1] == '1') {
                        uf.union(r * nc + c, r * nc + c - 1);
                    }
                    if (c + 1 < nc && grid[r][c + 1] == '1') {
                        uf.union(r * nc + c, r * nc + c + 1);
                    }
                }
            }
        }

        return uf.getCount();
    }

    class UnionFind {
        int count; // # of connected components
        int[] parent;
        int[] rank;

        public UnionFind(char[][] grid) { // for problem 200
            count = 0;
            int m = grid.length;
            int n = grid[0].length;
            parent = new int[m * n];
            rank = new int[m * n];
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (grid[i][j] == '1') {
                        parent[i * n + j] = i * n + j;
                        ++count;
                    }
                    rank[i * n + j] = 0;
                }
            }
        }

        public void union(int x, int y) { // union with rank
            int rootx = find(x);
            int rooty = find(y);
            if (rootx != rooty) {
                if (rank[rootx] > rank[rooty]) {
                    parent[rooty] = rootx;
                } else if (rank[rootx] < rank[rooty]) {
                    parent[rootx] = rooty;
                } else {
                    parent[rooty] = rootx;
                    rank[rootx] += 1;
                }
                --count;
            }
        }

        public int find(int i) { // path compression
            if (parent[i] != i) parent[i] = find(parent[i]);
            return parent[i];
        }

        public int getCount() {
            return count;
        }
    }

}
