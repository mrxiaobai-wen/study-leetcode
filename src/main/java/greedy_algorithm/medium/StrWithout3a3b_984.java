package greedy_algorithm.medium;

/**
 * 984. 不含 AAA 或 BBB 的字符串
 * 给定两个整数 A 和 B，返回任意字符串 S，要求满足：
 * <p>
 * S 的长度为 A + B，且正好包含 A 个 'a' 字母与 B 个 'b' 字母；
 * 子串 'aaa' 没有出现在 S 中；
 * 子串 'bbb' 没有出现在 S 中。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：A = 1, B = 2
 * 输出："abb"
 * 解释："abb", "bab" 和 "bba" 都是正确答案。
 * 示例 2：
 * <p>
 * 输入：A = 4, B = 1
 * 输出："aabaa"
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= A <= 100
 * 0 <= B <= 100
 * 对于给定的 A 和 B，保证存在满足要求的 S。
 */
public class StrWithout3a3b_984 {
    public String strWithout3a3b(int A, int B) {
        return fun(A, B);
    }

    private String fun(int aNum, int bNum) {
        char charA = 'a';
        char charB = 'b';
        StringBuilder sb = new StringBuilder();
        char preChar = 0;
        while (aNum > 0 && bNum > 0) {
            if (aNum > bNum) {
                sb.append("aa").append("b");
                preChar = 0;
                aNum -= 2;
                bNum--;
            } else if (bNum > aNum) {
                sb.append("bb").append("a");
                preChar = charA;
                bNum -= 2;
                aNum--;
            } else {
                if (preChar == charA) {
                    sb.append("b").append("a");
                } else {
                    sb.append("a").append("b");
                }
                aNum--;
                bNum--;
            }
        }
        for (int i = 0; i < aNum; i++) {
            sb.append(charA);
        }
        for (int j = 0; j < bNum; j++) {
            sb.append(charB);
        }
        return sb.toString();
    }
}
