package net.zackzhang.code.codinginterviews.problems;

public class NumberOf1Between1AndN {

    private int num(int n) {
        int count = 0;
        // 高位段（包括当前考虑的位以及更高位）
        int highDigits = n;
        // 当前考虑的位
        int base = 1;
        while (highDigits != 0) {
            // 当前考虑的位上的数字
            int digit = highDigits - highDigits / 10 * 10;
            if (digit == 0) {
                // 当前位上的数字为 0
                // 则当前位为 1 的个数为【高位段 / 10 * base】
                count += highDigits / 10 * base;
            } else if (digit == 1) {
                // 当前位上的数字为 1
                // 则当前位为 1 的个数为【高位段 / 10 * base】加【低位段 + 1】
                count += highDigits / 10 * base + (n % base) + 1;
            } else {
                // 当前位上的数字大于 1
                // 则当前位为 1 的个数为【(高位段 / 10 + 1) * base】
                count += (highDigits / 10 + 1) * base;
            }
            // 向高位迭代
            base *= 10;
            highDigits /= 10;
        }
        return count;
    }

    public static void test() {
        System.out.println(new NumberOf1Between1AndN().num(1144));
    }
}
