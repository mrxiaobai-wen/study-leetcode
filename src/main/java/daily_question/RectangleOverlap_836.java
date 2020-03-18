package daily_question;

/**
 * 836. 矩形重叠
 * 矩形以列表 [x1, y1, x2, y2] 的形式表示，其中 (x1, y1) 为左下角的坐标，(x2, y2) 是右上角的坐标。
 * <p>
 * 如果相交的面积为正，则称两矩形重叠。需要明确的是，只在角或边接触的两个矩形不构成重叠。
 * <p>
 * 给出两个矩形，判断它们是否重叠并返回结果。
 * <p>
 * 示例 1：
 * <p>
 * 输入：rec1 = [0,0,2,2], rec2 = [1,1,3,3]
 * 输出：true
 * 示例 2：
 * <p>
 * 输入：rec1 = [0,0,1,1], rec2 = [1,0,2,1]
 * 输出：false
 * 说明：
 * <p>
 * 两个矩形 rec1 和 rec2 都以含有四个整数的列表的形式给出。
 * 矩形中的所有坐标都处于 -10^9 和 10^9 之间。
 */
public class RectangleOverlap_836 {
    /**
     * 方法一：固定一个矩形，检查位置：上下左右
     * 方法二：检查重叠区域；检查x轴、y重叠线段
     *
     * @param rec1
     * @param rec2
     * @return
     */
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        return fun(rec1, rec2);
    }

    private boolean fun(int[] rec1, int[] rec2) {
        // 按照上下左右的顺序检测
        if (rec1[1] >= rec2[3]
                || rec1[3] <= rec2[1]
                || rec1[2] <= rec2[0]
                || rec1[0] >= rec2[2]) {
            return false;
        }

        return true;
    }
}
