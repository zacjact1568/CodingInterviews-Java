package net.zackzhang.code.codinginterviews.problems;

public class LastRemaining {

    // 模拟环法
    private int lastRemaining(int n, int m) {
        // 已经退出的孩子为 true
        boolean[] kids = new boolean[n];
        int iCount = 0, jCount = 0;
        // i: 0 ~ n-1, j: 0 ~ m-1
        int i, j;
        // 剩余的孩子数
        int rem = n;
        while (rem > 0) {
            i = iCount % n;
            j = jCount % m;
            if (!kids[i]) {
                // 遍历到未退出的孩子
                if (j == m - 1) {
                    // 若这个孩子报到 m-1，标为退出
                    kids[i] = true;
                    rem--;
                    // 若剩余的孩子数减 1 后已经没有孩子剩余
                    // 说明当前遍历的孩子就是最后一个，返回
                    if (rem == 0) return i;
                }
                jCount++;
            }
            // 遍历到已退出的孩子，继续遍历
            iCount++;
        }
        return -1;
    }

    // 递推公式法
    // TODO private int lastRemaining2(int n, int m) {}

    public static void test() {
        System.out.println(new LastRemaining().lastRemaining(10, 4));
    }
}
