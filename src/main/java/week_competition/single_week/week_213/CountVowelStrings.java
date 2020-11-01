package week_competition.single_week.week_213;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class CountVowelStrings {

    @Test
    public void test() {
        Assert.assertEquals(5,countVowelStrings(1));
        Assert.assertEquals(15,countVowelStrings(2));
        Assert.assertEquals(66045,countVowelStrings(33));
        countVowelStrings(50);
    }

    public int countVowelStrings(int n) {
        return fun(n,0,5);
    }

    /**
     * 使用递归
     */
    private int fun(int n,int curIndex,int choice) {
        if (curIndex >= n) {
            return 1;
        }
        if (choice <= 0) {
            return 0;
        }
        int result = 0;
        for (int i = choice;i >= 1;i--) {
            result += fun(n,curIndex + 1,i);
        }
        return result;
    }
}
