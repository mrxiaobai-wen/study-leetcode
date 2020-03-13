package daily_question;

/**
 * 169. 多数元素
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 * <p>
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,2,3]
 * 输出: 3
 * 示例 2:
 * <p>
 * 输入: [2,2,1,1,1,2,2]
 * 输出: 2
 */
public class MajorityElement_169 {
    public int majorityElement(int[] nums) {
        return fun(nums);
    }

    private int fun(int[] nums) {
        int curNum = 0;
        Integer cur = null;
        for (int temp : nums) {
            if (cur == null) {
                cur = temp;
                curNum++;
            } else if (cur == temp) {
                curNum++;
            } else {
                if (curNum > 1) {
                    curNum--;
                } else {
                    curNum = 0;
                    cur = null;
                }
            }
        }
        return cur;
    }
}
