package week_competition.single_week.week_214;

import org.junit.Assert;
import org.junit.Test;

public class CreateSortedArray {

    @Test
    public void test() {
        Assert.assertEquals(1, createSortedArray(new int[]{1, 5, 6, 2}));
        Assert.assertEquals(3, createSortedArray(new int[]{1, 2, 3, 6, 5, 4}));
        Assert.assertEquals(4, createSortedArray(new int[]{1, 3, 3, 3, 2, 4, 2, 1, 2}));
    }

    public int createSortedArray(int[] instructions) {
        return fun(instructions);
    }

    private int fun(int[] instructions) {
        int[] nums = new int[instructions.length];
        int result = 0;
        for (int i = 0; i < instructions.length; i++) {
            int curNum = instructions[i];
            if (i == 0) {
                nums[i] = curNum;
            } else {
                int maxNum = 0;
                int index = i;
                while (index > 0 && curNum <= nums[index - 1]) {
                    if (nums[index - 1] != curNum) {
                        maxNum++;
                    }
                    nums[index] = nums[index - 1];
                    index--;
                }
                nums[index] = curNum;
                int minNum = index;
                result += Math.min(maxNum, minNum);
            }
        }
        return result;
    }
}
