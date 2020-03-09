package greedy_algorithm.medium;

import org.junit.Test;

/**
 * Dota2 的世界里有两个阵营：Radiant(天辉)和 Dire(夜魇)
 * <p>
 * Dota2 参议院由来自两派的参议员组成。现在参议院希望对一个 Dota2 游戏里的改变作出决定。他们以一个基于轮为过程的投票进行。在每一轮中，每一位参议员都可以行使两项权利中的一项：
 * <p>
 * 禁止一名参议员的权利：
 * <p>
 * 参议员可以让另一位参议员在这一轮和随后的几轮中丧失所有的权利。
 * <p>
 * 宣布胜利：
 * <p>
 *           如果参议员发现有权利投票的参议员都是同一个阵营的，他可以宣布胜利并决定在游戏中的有关变化。
 * <p>
 *  
 * <p>
 * 给定一个字符串代表每个参议员的阵营。字母 “R” 和 “D” 分别代表了 Radiant（天辉）和 Dire（夜魇）。然后，如果有 n 个参议员，给定字符串的大小将是 n。
 * <p>
 * 以轮为基础的过程从给定顺序的第一个参议员开始到最后一个参议员结束。这一过程将持续到投票结束。所有失去权利的参议员将在过程中被跳过。
 * <p>
 * 假设每一位参议员都足够聪明，会为自己的政党做出最好的策略，你需要预测哪一方最终会宣布胜利并在 Dota2 游戏中决定改变。输出应该是 Radiant 或 Dire。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: "RD"
 * 输出: "Radiant"
 * 解释:  第一个参议员来自  Radiant 阵营并且他可以使用第一项权利让第二个参议员失去权力，因此第二个参议员将被跳过因为他没有任何权利。然后在第二轮的时候，第一个参议员可以宣布胜利，因为他是唯一一个有投票权的人
 * 示例 2:
 * <p>
 * 输入: "RDD"
 * 输出: "Dire"
 * 解释:
 * 第一轮中,第一个来自 Radiant 阵营的参议员可以使用第一项权利禁止第二个参议员的权利
 * 第二个来自 Dire 阵营的参议员会被跳过因为他的权利被禁止
 * 第三个来自 Dire 阵营的参议员可以使用他的第一项权利禁止第一个参议员的权利
 * 因此在第二轮只剩下第三个参议员拥有投票的权利,于是他可以宣布胜利
 *  
 * <p>
 * 注意:
 * <p>
 * 给定字符串的长度在 [1, 10,000] 之间.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/dota2-senate
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class PredictPartyVictory_649 {
    @Test
    public void test() {
        System.out.println(predictPartyVictory("RDD"));
    }

    public String predictPartyVictory(String senate) {
        // 统计两房数字想的太简单，例如： "DDRRR"，结果应该是D获胜
        //return fun1(senate);
        // 投票计数法，行不通
        //return fun2(senate);

        // 模拟投票
        //return fun3(senate);

        return fun4(senate);
    }

    private String fun2(String senate) {
        char[] senateArr = senate.toCharArray();
        int countR = 0, countD = 0;
        while (true) {
            countD = countR = 0;
            boolean endFlag = true;
            int voteR = 0, voteD = 0;
            for (int i = 0; i < senateArr.length; i++) {
                if (senateArr[i] == 0) {
                    continue;
                }
                if (senateArr[i] == 'R') {
                    if (voteD > 0) {
                        endFlag = false;
                        voteD--;
                        senateArr[i] = 0;
                    } else {
                        countR++;
                        voteR++;
                    }
                } else {
                    if (voteR > 0) {
                        endFlag = false;
                        voteR--;
                        senateArr[i] = 0;
                    } else {
                        countD++;
                        voteD++;
                    }
                }
            }
            if (endFlag) {
                break;
            }
        }
        return countR > countD ? "Radiant" : "Dire";
    }

    private String fun3(String senate) {
        char[] arr = senate.toCharArray();
        int len = senate.length();
        while (true) {
            // r和v投票起始位置起始位置
            int rvIndex = -1, dvIndex = -1;
            for (int i = 0; i < len; i++) {
                if (arr[i] == 0) {
                    continue;
                } else if (arr[i] == 'R') {
                    while (rvIndex < len) {
                        rvIndex++;
                        if (rvIndex >= len) {
                            return "Radiant";
                        }
                        if (arr[rvIndex] == 'D') {
                            arr[rvIndex] = 0;
                            break;
                        }
                    }
                } else {
                    while (dvIndex < len) {
                        dvIndex++;
                        if (dvIndex >= len) {
                            return "Dire";
                        }
                        if (arr[dvIndex] == 'R') {
                            arr[dvIndex] = 0;
                            break;
                        }
                    }
                }
            }
        }
    }

    /**
     * 参考代码：
     *
     * @param senate
     * @return
     */
    private String fun4(String senate) {
        boolean r = true, d = true;
        // person 大于0表示R，小于表示d
        int person = 0;
        char[] arr = senate.toCharArray();
        while (r && d) {
            r = d = false;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == 'R') {
                    r = true;
                    if (person < 0) {
                        arr[i] = 0;
                    }
                    person++;
                } else if (arr[i] == 'D') {
                    d = true;
                    if (person > 0) {
                        arr[i] = 0;
                    }
                    person--;
                }
            }
        }
        return person > 0 ? "Radiant" : "Dire";
    }
}
