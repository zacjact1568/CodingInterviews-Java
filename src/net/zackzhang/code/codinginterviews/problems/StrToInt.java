package net.zackzhang.code.codinginterviews.problems;

/** 把字符串转换成整数 */
public class StrToInt {

    private int converse(String str) {
        if (str.isEmpty()) return 0;
        boolean negative = false;
        int start = 0;
        if (str.charAt(0) == '+') {
            start = 1;
        } else if (str.charAt(0) == '-') {
            negative = true;
            start = 1;
        }
        int result = 0;
        for (int i = start; i < str.length(); i++) {
            char ch = str.charAt(i);
            // 判断是否存在非数字
            if (ch < '0' || ch > '9') return 0;
            int digit = ch - '0';
            if (negative) {
                // 负数
                // 试探是否溢出
                if (result < (Integer.MIN_VALUE + digit) / 10) return 0;
                result = (result * 10) - digit;
            } else {
                // 正数
                // 试探是否溢出
                if (result > (Integer.MAX_VALUE - digit) / 10) return 0;
                result = (result * 10) + digit;
            }
        }
        return result;
    }

    public static void test() {
        System.out.println(new StrToInt().converse("-2147483648"));
    }
}
