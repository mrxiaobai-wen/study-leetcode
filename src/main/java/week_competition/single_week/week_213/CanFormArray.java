package week_competition.single_week.week_213;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CanFormArray {

    @Test
    public void test() {
        Assert.assertEquals(true,canFormArray(new int[]{85},new int[][]{{85}}));
        Assert.assertEquals(true,canFormArray(new int[]{15,88},new int[][]{{88},{15}}));
        Assert.assertEquals(false,canFormArray(new int[]{49,18,16},new int[][]{{16,18,49}}));
        Assert.assertEquals(true,canFormArray(new int[]{91,4,64,78},new int[][]{{78},{4,64},{91}}));
        Assert.assertEquals(false,canFormArray(new int[]{1,3,5,7},new int[][]{{2,4,6,8}}));
    }

    public boolean canFormArray(int[] arr, int[][] pieces) {
        // key:数字  value:在pieces中的下标
        Map<Integer,Integer> map = new HashMap<>();
        int pNums = 0;
        for (int i = 0;i < pieces.length;i++) {
            int[] temp = pieces[i];
            pNums += temp.length;
            map.put(temp[0],i);
        }
        if (pNums < arr.length) {
            return false;
        }
        for (int fast = 0;fast < arr.length;) {
            if (!map.containsKey(arr[fast])) {
                return false;
            }
            int[] temp = pieces[map.get(arr[fast])];
            for (int i = 0;i < temp.length;i++) {
                if (temp[i] != arr[fast + i]) {
                    return false;
                }
            }
            fast = fast + temp.length;
        }
        return true;
    }


}
