package net.zackzhang.code.codinginterviews.problems;

public class Power {

    private double power(double base, int exponent) {
        double result = 1;
        int e = Math.abs(exponent);
        while (e != 0) {
            result *= base;
            e--;
        }
        if (exponent < 0) {
            result = 1 / result;
        }
        return result;
    }

    // 快速幂运算（递归）
    private double power2(double base, int exponent) {
        if (exponent == 0) return 1;
        double result;
        int e = Math.abs(exponent);
        result = power2(base, e >> 1);
        result *= result;
        if ((e & 1) == 1) {
            // 如果指数为奇数，需要再乘一次底数
            result *= base;
        }
        if (exponent < 0) {
            result = 1 / result;
        }
        return result;
    }

    // 快速幂运算（循环）
    private double power3(double base, int exponent) {
        if (exponent == 0) return 1;
        double result = 1;
        double temp = base;
        int e = Math.abs(exponent);
        while (e > 0) {
            if ((e & 1) == 1) {
                // 最后一次循环，e 一定为 1，因此一定会走到这个分支
                result *= temp;
            }
            // 最后一次循环，这条语句无用
            temp *= temp;
            e >>= 1;
        }
        if (exponent < 0) {
            result = 1 / result;
        }
        return result;
    }

    public static void test() {
        System.out.println(new Power().power3(2, -3));
    }
}
