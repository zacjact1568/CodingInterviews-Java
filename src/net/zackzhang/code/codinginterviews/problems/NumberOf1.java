package net.zackzhang.code.codinginterviews.problems;

public class NumberOf1 {

    // 做复杂了
    public int num(int n) {
        if (n == 0) return 0;
        int remainder;
        int quotient = n;
        int count = 0;
        // 二进制位数，仅用于负数
        int bit = 0;
        while (quotient != 0) {
            remainder = quotient % 2;
            if (n >= 0) {
                // 正数，按 1 算
                if (remainder == 1) {
                    count++;
                }
            } else {
                // 负数
                if (count == 0) {
                    // 还没出现 1，按 1 算
                    if (remainder == -1) {
                        count++;
                    }
                } else {
                    // 已经出现 1 个 1，按 0 算
                    if (remainder == 0) {
                        count++;
                    }
                }
                bit++;
            }
            quotient /= 2;
        }
        // 负数加上多出来的一位符号位和前面的 1
        return n > 0 ? count : count + (32 - bit);
    }

    public int num2(int n) {
        int count = 0;
        while (n != 0) {
            // 与 1 相与，可检测出第一位是否为 1
            count += n & 1;
            // 补码无符号右移一位
            n >>>= 1;
        }
        return count;
    }

    public int num3(int n) {
        int count = 0;
        while (n != 0) {
            // n - 1 总是将补码最低位的 1 变成 0
            // 统计进行了几次这样的操作即可
            n &= (n - 1);
            count++;
        }
        return count;
    }

    public static void test() {
        System.out.println(new NumberOf1().num3(-2147483648));
    }
}
