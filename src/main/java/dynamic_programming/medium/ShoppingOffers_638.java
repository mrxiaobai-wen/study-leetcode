package dynamic_programming.medium;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * 638. 大礼包
 * <p>
 * 在LeetCode商店中， 有许多在售的物品。
 * <p>
 * 然而，也有一些大礼包，每个大礼包以优惠的价格捆绑销售一组物品。
 * <p>
 * 现给定每个物品的价格，每个大礼包包含物品的清单，以及待购物品清单。请输出确切完成待购清单的最低花费。
 * <p>
 * 每个大礼包的由一个数组中的一组数据描述，最后一个数字代表大礼包的价格，其他数字分别表示内含的其他种类物品的数量。
 * <p>
 * 任意大礼包可无限次购买。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [2,5], [[3,0,5],[1,2,10]], [3,2]
 * 输出: 14
 * 解释:
 * 有A和B两种物品，价格分别为¥2和¥5。
 * 大礼包1，你可以以¥5的价格购买3A和0B。
 * 大礼包2， 你可以以¥10的价格购买1A和2B。
 * 你需要购买3个A和2个B， 所以你付了¥10购买了1A和2B（大礼包2），以及¥4购买2A。
 * 示例 2:
 * <p>
 * 输入: [2,3,4], [[1,1,0,4],[2,2,1,9]], [1,2,1]
 * 输出: 11
 * 解释:
 * A，B，C的价格分别为¥2，¥3，¥4.
 * 你可以用¥4购买1A和1B，也可以用¥9购买2A，2B和1C。
 * 你需要买1A，2B和1C，所以你付了¥4买了1A和1B（大礼包1），以及¥3购买1B， ¥4购买1C。
 * 你不可以购买超出待购清单的物品，尽管购买大礼包2更加便宜。
 * 说明:
 * <p>
 * 最多6种物品， 100种大礼包。
 * 每种物品，你最多只需要购买6个。
 * 你不可以购买超出待购清单的物品，即使更便宜。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shopping-offers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ShoppingOffers_638 {

    @Test
    public void test1() {
        List<Integer> price = new ArrayList<>();
        price.add(2);
        price.add(5);
        List<List<Integer>> special = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        list1.add(3);
        list1.add(0);
        list1.add(5);
        List<Integer> list2 = new ArrayList<>();
        list2.add(1);
        list2.add(2);
        list2.add(10);
        special.add(list1);
        special.add(list2);
        List<Integer> needs = new ArrayList<>();
        needs.add(3);
        needs.add(2);
        Assert.assertEquals(14, fun1(price, special, needs));
    }

    @Test
    public void test2() {
        List<Integer> price = new ArrayList<>();
        price.add(2);
        price.add(3);
        price.add(4);
        List<List<Integer>> special = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(1);
        list1.add(0);
        list1.add(4);
        List<Integer> list2 = new ArrayList<>();
        list2.add(2);
        list2.add(2);
        list2.add(1);
        list2.add(9);
        special.add(list1);
        special.add(list2);
        List<Integer> needs = new ArrayList<>();
        needs.add(1);
        needs.add(2);
        needs.add(1);
        Assert.assertEquals(11, fun1(price, special, needs));
    }

    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        //return fun1(price,special,needs);

        return fun2(price, special, needs, new HashMap<>());
    }

    /**
     * 递归
     */
    private int fun1(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        int result = calculate(price, needs);
        int len = price.size();
        for (List<Integer> specialList : special) {
            List<Integer> tempList = new ArrayList<>(needs);
            int j = 0;
            for (j = 0; j < len; j++) {
                if (tempList.get(j) >= specialList.get(j)) {
                    tempList.set(j, tempList.get(j) - specialList.get(j));
                } else {
                    break;
                }
            }
            if (j == len) {
                result = Math.min(result, specialList.get(j) + fun1(price, special, tempList));
            }
        }
        return result;
    }

    private int calculate(List<Integer> price, List<Integer> needs) {
        int result = 0;
        int len = price.size();
        for (int i = 0; i < len; i++) {
            result += price.get(i) * needs.get(i);
        }
        return result;
    }

    /**
     * 记忆化搜索
     * 知：每次变动的知识needs
     */
    private int fun2(List<Integer> price, List<List<Integer>> special, List<Integer> needs, Map<String, Integer> resultMap) {
        String key = needs.toString();
        if (resultMap.containsKey(key)) {
            return resultMap.get(key);
        }
        int result = calculate(price, needs);
        int len = price.size();
        for (List<Integer> specialList : special) {
            List<Integer> needsClone = new ArrayList<>(needs);
            int j = 0;
            for (j = 0; j < len; j++) {
                if (needsClone.get(j) >= specialList.get(j)) {
                    needsClone.set(j, needsClone.get(j) - specialList.get(j));
                } else {
                    break;
                }
            }
            if (j == len) {
                result = Math.min(result, specialList.get(len) + fun2(price, special, needsClone, resultMap));
            }
        }
        resultMap.put(key, result);
        return result;
    }
}
