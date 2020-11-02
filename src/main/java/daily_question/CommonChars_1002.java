package daily_question;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 1002. 查找常用字符
 * <p>
 * 给定仅有小写字母组成的字符串数组 A，返回列表中的每个字符串中都显示的全部字符（包括重复字符）组成的列表。例如，如果一个字符在每个字符串中出现 3 次，
 * 但不是 4 次，则需要在最终答案中包含该字符 3 次。
 * <p>
 * 你可以按任意顺序返回答案。
 * <p>
 * 示例 1：
 * <p>
 * 输入：["bella","label","roller"]
 * 输出：["e","l","l"]
 * 示例 2：
 * <p>
 * 输入：["cool","lock","cook"]
 * 输出：["c","o"]
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= A.length <= 100
 * 1 <= A[i].length <= 100
 * A[i][j] 是小写字母
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-common-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CommonChars_1002 {

    @Test
    public void test() {
        List<String> list1 = commonChars(new String[]{"bella", "label", "roller"});
        System.out.println(list1);
        List<String> list2 = commonChars(new String[]{"cool", "lock", "cook"});
        System.out.println(list2);
    }

    public List<String> commonChars(String[] A) {
        int[] arr = new int[26];
        for (char temp : A[0].toCharArray()) {
            arr[temp - 'a']++;
        }
        for (int i = 1; i < A.length; i++) {
            int[] temp = new int[26];
            for (char tempChar : A[i].toCharArray()) {
                temp[tempChar - 'a']++;
            }
            for (int j = 0; j < 26; j++) {
                arr[j] = Math.min(temp[j], arr[j]);
            }
        }
        List<String> result = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            for (int j = 1; j <= arr[i]; j++) {
                result.add("" + (char) ('a' + i));
            }
        }
        return result;
    }
}
