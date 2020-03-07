package greedy_algorithm.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 406. 根据身高重建队列
 * 假设有打乱顺序的一群人站成一个队列。 每个人由一个整数对(h, k)表示，其中h是这个人的身高，k是排在这个人前面且身高大于或等于h的人数。
 * 编写一个算法来重建这个队列。
 * <p>
 * 注意：
 * 总人数少于1100人。
 * <p>
 * 示例
 * <p>
 * 输入:
 * [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
 * <p>
 * 输出:
 * [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 */
public class ReconstructQueue_406 {
    public int[][] reconstructQueue(int[][] people) {
        return fun1(people);
    }

    /**
     * 参考：
     * 思路：先按照身高从高到低进行排序，对于相同身高则按k值从小到大进行排序。
     * 然后讲排好序的结果顺序插入到结果列表中。
     * 可行原因：因为身高矮的相对于高的来说是不可见的，所以在后面顺序插入到结果列表的时候，即使身高矮的插入到了身高高的前面，
     * 依然不影响身高高的人。
     * </p>
     * 贪心思想体现：
     *
     * @param people
     * @return
     */
    private int[][] fun1(int[][] people) {
        int[][] result = new int[people.length][];
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] == o2[0] ? o1[1] - o2[1] : -1 * (o1[0] - o2[0]);
            }
        });
        List<int[]> tempList = new ArrayList<>();
        for (int i = 0; i < result.length; i++) {
            // 后面身高矮的人查到前面身高高的人前面，不影响后面身高高的人符合规则
            tempList.add(people[i][1], people[i]);
        }
        return tempList.toArray(result);
    }
}
