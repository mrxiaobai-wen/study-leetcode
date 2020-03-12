package greedy_algorithm.medium;

import java.util.*;

/**
 * 55. 跳跃游戏
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * <p>
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * <p>
 * 判断你是否能够到达最后一个位置。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [2,3,1,1,4]
 * 输出: true
 * 解释: 我们可以先跳 1 步，从位置 0 到达 位置 1, 然后再从位置 1 跳 3 步到达最后一个位置。
 * 示例 2:
 * <p>
 * 输入: [3,2,1,0,4]
 * 输出: false
 * 解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
 */
public class CanJump_55 {
    public boolean canJump(int[] nums) {
        return fun1(nums);
    }

    /**
     * 思路：
     * 假定从某一起点出发，记录一次能到的位置；
     * 在能到的位置中，依次重复上述步骤；
     * 知道点中包含终点，则返回true；
     * 若遍历完，依然不包含终点，则返回false。
     *
     * @param nums
     * @return
     */
    private boolean fun1(int[] nums) {
        int len = nums.length;
        if (len <= 1) {
            return true;
        }

        // 执行超时
        /*Queue<Integer> canReach = new LinkedList<>();
        canReach.add(0);
        while (!canReach.isEmpty()) {
            // 思想：数据分段
            int size = canReach.size();
            for (int i = 0;i < size;i++) {
                int curIndex = canReach.poll();
                for (int j = 1;j <= nums[curIndex];j++) {
                    int nextCanReach = curIndex + j;
                    if (nextCanReach == len - 1) {
                        return true;
                    } else if (nextCanReach < len - 1 && !canReach.contains(nextCanReach)) {
                        canReach.add(nextCanReach);
                    }
                }
            }
        }
        return false;*/

        int[] canReach = new int[nums.length];
        canReach[0] = 1;
        for (int cur = 0; cur < len; cur++) {
            if (canReach[cur] <= 0) {
                continue;
            }
            for (int i = 1; i <= nums[cur]; i++) {
                if (cur + i == len - 1) {
                    return true;
                } else if (cur + i < len - 1) {
                    canReach[cur + i]++;
                }
            }
        }
        return false;
    }

    // todo 执行最快用时参考
    /*
    public boolean canJump(int[] nums) {
		 int stride = 0;
		 for(int i=0;i<nums.length-1;i++){
			 stride = Math.max(stride-1, nums[i]);
			 if(stride == 0) return false;
		 }
		 return true;
	 }
     */
}
