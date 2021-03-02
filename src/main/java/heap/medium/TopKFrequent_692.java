package heap.medium;

import org.junit.Test;

import java.util.*;

/**
 * 692. 前K个高频单词
 * <p>
 * 给一非空的单词列表，返回前 k 个出现次数最多的单词。
 * <p>
 * 返回的答案应该按单词出现频率由高到低排序。如果不同的单词有相同出现频率，按字母顺序排序。
 * <p>
 * 示例 1：
 * <p>
 * 输入: ["i", "love", "leetcode", "i", "love", "coding"], k = 2 输出: ["i", "love"] 解析: "i" 和 "love" 为出现次数最多的两个单词，均为2次。
 * 注意，按字母顺序 "i" 在 "love" 之前。
 * <p>
 * 示例 2：
 * <p>
 * 输入: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4 输出: ["the", "is", "sunny", "day"]
 * 解析: "the", "is", "sunny" 和 "day" 是出现次数最多的四个单词， 出现次数依次为 4, 3, 2 和 1 次。   注意：
 * <p>
 * 假定 k 总为有效值， 1 ≤ k ≤ 集合元素数。 输入的单词均由小写字母组成。
 * <p>
 * 扩展练习：
 * <p>
 * 尝试以 O(n log k) 时间复杂度和 O(n) 空间复杂度解决。
 * <p>
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/top-k-frequent-words 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class TopKFrequent_692 {

    @Test
    public void test() {
        List<String> result = topKFrequent(new String[]{"i", "love", "leetcode", "i", "love", "coding"}, 2);
    }

    public List<String> topKFrequent(String[] words, int k) {
        return fun(words, k);
    }

    private List<String> fun(String[] words, int k) {
        Map<String, Data> map = new HashMap<>();
        for (String word : words) {
            if (!map.containsKey(word)) {
                map.put(word, new Data(word, 0));
            }
            map.get(word).num++;
        }
        PriorityQueue<Data> queue = new PriorityQueue<>(new Comparator<Data>() {
            @Override
            public int compare(Data o1, Data o2) {
                if (o1.num != o2.num) {
                    // 数字倒序排序
                    return o2.num - o1.num;
                } else {
                    // 字母升序排序
                    return o1.word.compareTo(o2.word);
                }
            }
        });
        for (Map.Entry<String, Data> entry : map.entrySet()) {
            queue.offer(entry.getValue());
        }
        List<String> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            result.add(queue.poll().word);
        }
        return result;
    }

    private class Data {

        public String word;
        public int num;

        public Data(String word, int num) {
            this.word = word;
            this.num = num;
        }
    }
}
