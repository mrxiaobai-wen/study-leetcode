package daily_question.daily_question_2020;

/**
 * 1024. 视频拼接
 * <p>
 * 你将会获得一系列视频片段，这些片段来自于一项持续时长为 T 秒的体育赛事。这些片段可能有所重叠，也可能长度不一。
 * <p>
 * 视频片段 clips[i] 都用区间进行表示：开始于 clips[i][0] 并于 clips[i][1] 结束。我们甚至可以对这些片段自由地再剪辑，
 * 例如片段 [0, 7] 可以剪切成 [0, 1] + [1, 3] + [3, 7] 三部分。
 * <p>
 * 我们需要将这些片段进行再剪辑，并将剪辑后的内容拼接成覆盖整个运动过程的片段（[0, T]）。
 * 返回所需片段的最小数目，如果无法完成该任务，则返回 -1 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：clips = [[0,2],[4,6],[8,10],[1,9],[1,5],[5,9]], T = 10
 * 输出：3
 * 解释：
 * 我们选中 [0,2], [8,10], [1,9] 这三个片段。
 * 然后，按下面的方案重制比赛片段：
 * 将 [1,9] 再剪辑为 [1,2] + [2,8] + [8,9] 。
 * 现在我们手上有 [0,2] + [2,8] + [8,10]，而这些涵盖了整场比赛 [0, 10]。
 * 示例 2：
 * <p>
 * 输入：clips = [[0,1],[1,2]], T = 5
 * 输出：-1
 * 解释：
 * 我们无法只用 [0,1] 和 [1,2] 覆盖 [0,5] 的整个过程。
 * 示例 3：
 * <p>
 * 输入：clips = [[0,1],[6,8],[0,2],[5,6],[0,4],[0,3],[6,7],[1,3],[4,7],[1,4],[2,5],[2,6],[3,4],[4,5],[5,7],[6,9]], T = 9
 * 输出：3
 * 解释：
 * 我们选取片段 [0,4], [4,7] 和 [6,9] 。
 * 示例 4：
 * <p>
 * 输入：clips = [[0,4],[2,8]], T = 5
 * 输出：2
 * 解释：
 * 注意，你可能录制超过比赛结束时间的视频。
 *  
 * 提示：
 * <p>
 * 1 <= clips.length <= 100
 * 0 <= clips[i][0] <= clips[i][1] <= 100
 * 0 <= T <= 100
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/video-stitching
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class VideoStitching_1024 {

    public int videoStitching(int[][] clips, int T) {
        return fun(clips, T);
    }

    /**
     * todo 动态规划
     */
    private int fun2(int[][] clips, int t) {
        return 0;
    }

    /**
     * 贪心算法
     * <p>
     * 注意到对于所有左端点相同的子区间，其右端点越远越有利。且最佳方案中不可能出现两个左端点相同的子区间。
     * 于是我们预处理所有的子区间，对于每一个位置 ii，我们记录以其为左端点的子区间中最远的右端点，记为 \textit{maxn}[i]maxn[i]。
     * <p>
     * 具体地，我们枚举每一个位置，假设当枚举到位置 ii 时，记左端点不大于 ii 的所有子区间的最远右端点为 \textit{last}last。
     * 这样 \textit{last}last 就代表了当前能覆盖到的最远的右端点。
     * <p>
     * 每次我们枚举到一个新位置，我们都用 \textit{maxn}[i]maxn[i] 来更新 \textit{last}last。
     * 如果更新后 \textit{last} == ilast==i，那么说明下一个位置无法被覆盖，我们无法完成目标。
     * <p>
     * 同时我们还需要记录上一个被使用的子区间的结束位置为 \textit{pre}pre，每次我们越过一个被使用的子区间，
     * 就说明我们要启用一个新子区间，这个新子区间的结束位置即为当前的 \textit{last}last。
     * 也就是说，每次我们遇到 i == \textit{pre}i==pre，则说明我们用完了一个被使用的子区间。
     * 这种情况下我们让答案加 11，并更新 \textit{pre}pre 即可。
     */
    private int fun(int[][] clips, int t) {
        int[] max = new int[t];
        for (int[] temp : clips) {
            if (temp[0] < t) {
                max[temp[0]] = Math.max(max[temp[0]], temp[1]);
            }
        }
        int result = 0;
        int last = 0, pre = 0;
        for (int i = 0; i < t; i++) {
            last = Math.max(last, max[i]);
            if (i >= last) {
                return -1;
            }
            if (pre == i) {
                result++;
                pre = last;
            }

        }
        return result;
    }
}
