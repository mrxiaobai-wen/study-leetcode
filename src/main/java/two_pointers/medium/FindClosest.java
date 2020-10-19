package two_pointers.medium;

import org.junit.Assert;
import org.junit.Test;

/**
 * 面试题 17.11. 单词距离
 *
 * 有个内含单词的超大文本文件，给定任意两个单词，找出在这个文件中这两个单词的最短距离(相隔单词数)。如果寻找过程在这个文件中会重复多次，
 * 而每次寻找的单词不同，你能对此优化吗?
 *
 * 示例：
 *
 * 输入：words = ["I","am","a","student","from","a","university","in","a","city"], word1 = "a", word2 = "student"
 * 输出：1
 * 提示：
 *
 * words.length <= 100000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-closest-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindClosest {

    @Test
    public void test() {
        Assert.assertEquals(1,findClosest(new String[]{"I","am","a","student","from","a","university","in","a","city"},
                "a","student"));
    }

    public int findClosest(String[] words, String word1, String word2) {
        return fun(words,word1,word2);
    }

    private int fun(String[] words,String word1,String word2) {
        int index1 = -1,index2 = -1;
        int result = Integer.MAX_VALUE;
        for (int i = 0;i < words.length;i++) {
            if (word1.equals(words[i])) {
                index1 = i;
            } else if (word2.equals(words[i])) {
                index2 = i;
            }
            if (index1 == - 1 || index2 == -1) {
                continue;
            }
            result = Math.min(result,Math.abs(index1 - index2));
        }
        return result;
    }
}
