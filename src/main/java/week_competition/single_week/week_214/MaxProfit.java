package week_competition.single_week.week_214;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class MaxProfit {

    @Test
    public void test() {
        Assert.assertEquals(14, maxProfit(new int[]{2, 5}, 4));
        Assert.assertEquals(19, maxProfit(new int[]{3, 5}, 6));
        Assert.assertEquals(110, maxProfit(new int[]{2, 8, 4, 10, 6}, 20));
        Assert.assertEquals(21, maxProfit(new int[]{1000000000}, 1000000000));
    }

    private int fun(int[] inventory, int orders) {
        if (inventory.length == 0) {
            return 0;
        } else if (inventory.length == 1) {
            return cal(inventory[0], orders);
        }
        int MOD = 1000000000 + 7;
        int result = 0;
        int len = inventory.length;
        int index = len - 1;
        while (orders > 0) {
            Arrays.sort(inventory);
            if (inventory[index] == inventory[index - 1]) {
                orders--;
                result = (result + inventory[index] % MOD) % MOD;
                inventory[index]--;
            } else {
                int canSel = inventory[index] - inventory[index - 1];
                if (canSel >= orders) {
                    result = (result + cal(inventory[index], orders)) % MOD;
                    orders = 0;
                } else {
                    result = (result + cal(inventory[index], canSel)) % MOD;
                    inventory[index] -= canSel;
                    orders -= canSel;
                }
            }
        }
        return result;
    }

    private int cal(int target, int num) {
        int MOD = 1000000000 + 7;
        long temp = target - num + 1;
        return (int) ((num * (temp + target) / 2) % MOD);
    }

    public int maxProfit(int[] inventory, int orders) {
        /*int MOD = 1000000000 + 7;
        Arrays.sort(inventory);
        int len = inventory.length;
        int result = 0;
        int index = len - 1;
        while (orders > 0) {
            orders--;
            result = (result + (inventory[index] % MOD)) % MOD;
            inventory[index]--;
            if (index == 0) {
                if (inventory[index] < inventory[index + 1] && inventory[index + 1] > 0) {
                    index++;
                }
            } else if (index == len - 1) {
                if (inventory[index] <= inventory[index - 1] && inventory[index - 1] > 0) {
                    index--;
                }
            } else {
                if (inventory[index + 1] > inventory[index]) {
                    index++;
                } else if (inventory[index - 1] >= inventory[index]) {
                    index--;
                }
            }
        }
        return result;*/

        return fun(inventory, orders);
    }

}
