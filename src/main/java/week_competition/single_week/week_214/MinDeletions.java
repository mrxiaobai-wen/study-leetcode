package week_competition.single_week.week_214;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MinDeletions {

    @Test
    public void test() {
        Assert.assertEquals(0, minDeletions("aab"));
        Assert.assertEquals(2, minDeletions("aaabbbcc"));
        Assert.assertEquals(2, minDeletions("ceabaacb"));

    }

    public int minDeletions(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char temp : s.toCharArray()) {
            map.put(temp, map.getOrDefault(temp, 0) + 1);
        }
        Set<Integer> set = new HashSet<>();
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (!set.contains(entry.getValue())) {
                set.add(entry.getValue());
                entry.setValue(0);
            }
        }
        int result = 0;
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            int num = entry.getValue();
            if (num <= 0) {
                continue;
            }
            while (set.contains(num) && num > 0) {
                num--;
                result++;
            }
            if (num > 0) {
                set.add(num);
            }
        }
        return result;
    }
}
