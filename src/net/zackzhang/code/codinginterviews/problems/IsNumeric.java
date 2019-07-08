package net.zackzhang.code.codinginterviews.problems;

/** 表示数值的字符串 */
public class IsNumeric {

    private boolean isNumeric(char[] str) {
        int len = str.length;
        // 找出第一个 e 的位置
        int e = -1;
        for (int i = 0; i < len; i++) {
            char ch = str[i];
            if (ch == 'E' || ch == 'e') {
                e = i;
                break;
            }
        }
        if (e == -1) {
            // 没有 e
            return isNumericNoExponent(str, 0, len, true);
        } if (e == 0 || e == len - 1) {
            // e 在首位和末位，排除
            return false;
        } else {
            // 分成两部分
            // 第一部分跟没有 e 一样
            boolean isNumericPt1 = isNumericNoExponent(str, 0, e, true);
            // 第二部分只能是正负整数
            boolean isNumericPt2 = isNumericNoExponent(str, e + 1, len, false);
            return isNumericPt1 && isNumericPt2;
        }
    }

    // 是否是没有 e 的数值（包含 start，不包含 end）
    private boolean isNumericNoExponent(char[] str, int start, int end, boolean point) {
        // 防止出现两次小数点
        boolean hasPoint = false;
        for (int i = start; i < end; i++) {
            char ch = str[i];
            // 如果首位是正或负号，可以
            if (i == start && (ch == '+' || ch == '-')) continue;
            // 如果不是首位，或不是正负号
            // 如果还没出现过小数点，且允许小数点，且是小数点，可以
            if (!hasPoint && point && ch == '.') {
                hasPoint = true;
                continue;
            }
            // 如果出现过小数点了，或不允许小数点，或不是小数点
            // 如果不是数字，不行
            if (ch < '0' || ch > '9') return false;
        }
        return true;
    }

    private boolean isNumeric2(char[] str) {
        int len = str.length;
        // 正负号、小数点、e 是否出现过
        boolean sign = false, point = false, exponent = false;
        for (int i = 0; i < len; i++) {
            char ch = str[i];
            if (ch == 'e' || ch == 'E') {
                // e 在首位或末位，不行
                if (i == 0 || i == len - 1) return false;
                // 已经出现过 e 了，不行
                if (exponent) return false;
                // 将 e 标记为出现过
                exponent = true;
            } else if (ch == '+' || ch == '-') {
                // 如果正负号未出现过，且既不是在首位，也不是在 e 之后，不行
                if (!sign && i > 0 && str[i - 1] != 'e' && str[i - 1] != 'E') return false;
                // 如果正负号出现过，且上一位不是 e，不行
                // 注意要先判断 sign，因为 i=0 时 sign 一定为 false，就不会在 str[i-1] 时越界了
                if (sign && str[i - 1] != 'e' && str[i - 1] != 'E') return false;
                // 将正负号标记为出现过
                sign = true;
            } else if (ch == '.') {
                // 如果小数点或 e 已经出现过，不行
                if (point || exponent) return false;
                // 将小数点标记为出现过
                point = true;
            } else if (ch < '0' || ch > '9') {
                // 其他字符，只能为数字
                return false;
            }
        }
        return true;
    }

    public static void test() {
        System.out.println(new IsNumeric().isNumeric2("1.2.3".toCharArray()));
    }
}
