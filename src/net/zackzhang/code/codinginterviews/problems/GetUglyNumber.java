package net.zackzhang.code.codinginterviews.problems;

/** 丑数 */
public class GetUglyNumber {

    private int get(int index) {
        if (index <= 0) return 0;
        // 储存由小到大的丑数
        int[] uns = new int[index];
        // 第一个丑数
        uns[0] = 1;
        // 下标阈值
        // uns 中小于这个下标的丑数乘以 2/3/5 会小于 uns 中最大的丑数
        // 等于这个下标的丑数乘以 2/3/5 刚好是比 uns 中最大的丑数大的丑数
        // 大于这个下标的丑数乘以 2/3/5 会过大（中间漏掉了一个丑数）
        int th2 = 0, th3 = 0, th5 = 0;
        for (int i = 1; i < index; i++) {
            // 比 uns 中最大的丑数大的三个候选丑数
            int cand2 = uns[th2] * 2;
            int cand3 = uns[th3] * 3;
            int cand5 = uns[th5] * 5;
            // 选三者最小的，就是下一个丑数
            int min = Math.min(cand2, Math.min(cand3, cand5));
            uns[i] = min;
            // 判断这个新丑数来自哪个候选者
            // 对应的阈值增加 1
            if (cand2 == min) {
                th2++;
            }
            if (cand3 == min) {
                th3++;
            }
            if (cand5 == min) {
                th5++;
            }
        }
        // uns 最末元素就是第 index 个丑数
        return uns[index - 1];
    }

    public static void test() {
        System.out.println(new GetUglyNumber().get(10));
    }
}
