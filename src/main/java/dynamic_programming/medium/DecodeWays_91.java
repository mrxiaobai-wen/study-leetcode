package dynamic_programming.medium;

import org.junit.Test;

/**
 * 91. 解码方法
 * 一条包含字母 A-Z 的消息通过以下方式进行了编码：
 * <p>
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 给定一个只包含数字的非空字符串，请计算解码方法的总数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "12"
 * 输出: 2
 * 解释: 它可以解码为 "AB"（1 2）或者 "L"（12）。
 * 示例 2:
 * <p>
 * 输入: "226"
 * 输出: 3
 * 解释: 它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
 */
public class DecodeWays_91 {
    @Test
    public void test() {
        System.out.println(numDecodings("12120"));
    }

    public int numDecodings(String s) {
        return fun(s);
    }

    private int fun(String s) {
        if (s.startsWith("0")) {
            return 0;
        }
        char[] arr = s.toCharArray();
        if (arr.length == 0) {
            return 0;
        } else if (arr.length == 1) {
            return 1;
        }
        int len = arr.length;
        int[] result = new int[len + 1];
        result[0] = 1;
        if (arr[1] == '0') {
            if (arr[0] != '1' && arr[0] != '2') {
                return 0;
            }
            result[1] = 1;
        } else if (arr[1] >= '7') {
            if (arr[0] == '1') {
                result[1] = 2;
            } else {
                result[1] = 1;
            }
        } else {
            if (arr[0] >= '3') {
                result[1] = 1;
            } else {
                result[1] = 2;
            }
        }
        for (int i = 2; i < arr.length; i++) {
            if (arr[i] == '0') {
                if (arr[i - 1] != '1' && arr[i - 1] != '2') {
                    return 0;
                }
                result[i] = result[i - 2];
            } else {
                if (arr[i - 1] == '1') {
                    result[i] = result[i - 1] + result[i - 2];
                } else if (arr[i - 1] == '2') {
                    if (arr[i] >= '7') {
                        result[i] = result[i - 1];
                    } else {
                        result[i] = result[i - 1] + result[i - 2];
                    }
                } else {
                    result[i] = result[i - 1];
                }
            }
        }
        return result[len - 1];
    }
}
