package net.zackzhang.code.codinginterviews.problems;

public class Fibonacci {

    // 递归（由大至小）（存在重复计算，效率低）
    private int fibonacci(int n) {
        if (n == 0 || n == 1) return n;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    // 循环（由小至大）
    private int fibonacci2(int n) {
        if (n == 0 || n == 1) return n;
        int a = 0, b = 1;
        int res = 0;
        for (int i = 2; i <= n; i++) {
            res = a + b;
            a = b;
            b = res;
        }
        return res;
    }

    public static void test() {
        System.out.println(new Fibonacci().fibonacci(14));
    }
}
