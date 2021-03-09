package sort.medium;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 853. 车队
 *
 * N  辆车沿着一条车道驶向位于 target 英里之外的共同目的地。
 *
 * 每辆车 i 以恒定的速度 speed[i] （英里/小时），从初始位置 position[i] （英里） 沿车道驶向目的地。
 *
 * 一辆车永远不会超过前面的另一辆车，但它可以追上去，并与前车以相同的速度紧接着行驶。
 *
 * 此时，我们会忽略这两辆车之间的距离，也就是说，它们被假定处于相同的位置。
 *
 * 车队 是一些由行驶在相同位置、具有相同速度的车组成的非空集合。注意，一辆车也可以是一个车队。
 *
 * 即便一辆车在目的地才赶上了一个车队，它们仍然会被视作是同一个车队。
 *
 * 会有多少车队到达目的地?
 *
 * 示例：
 *
 * 输入：target = 12, position = [10,8,0,5,3], speed = [2,4,1,1,3]
 * 输出：3
 * 解释：
 * 从 10 和 8 开始的车会组成一个车队，它们在 12 处相遇。
 * 从 0 处开始的车无法追上其它车，所以它自己就是一个车队。
 * 从 5 和 3 开始的车会组成一个车队，它们在 6 处相遇。
 * 请注意，在到达目的地之前没有其它车会遇到这些车队，所以答案是 3。
 *
 * 提示：
 *
 * 0 <= N <= 10 ^ 4
 * 0 < target <= 10 ^ 6
 * 0 < speed[i] <= 10 ^ 6
 * 0 <= position[i] < target
 * 所有车的初始位置各不相同。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/car-fleet
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CarFleet_853 {

    public int carFleet(int target, int[] position, int[] speed) {
        if (position == null || position.length <= 0) {
            return 0;
        }
        Car[] arr = new Car[position.length];
        for (int i = 0;i < position.length;i++) {
            arr[i] = new Car(position[i],(target - position[i]) * 1.0 / speed[i]);
        }
        Arrays.sort(arr, new Comparator<Car>() {
            @Override
            public int compare(Car o1, Car o2) {
                return o1.position - o2.position;
            }
        });
        int result = 1;
        for (int i = arr.length - 1;i > 0;i--) {
            if (arr[i].time >= arr[i - 1].time) {
                arr[i - 1] = arr[i];
            } else {
                result++;
            }
        }
        return result;
    }

    class Car {
        int position;
        double time;
        Car(int p, double t) {
            position = p;
            time = t;
        }
    }
}
