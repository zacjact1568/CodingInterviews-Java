package net.zackzhang.code.codinginterviews.problems;

public class FindGreatestSumOfSubArray {

    // 这个解法，简单问题复杂化了
    public int find(int[] array) {
        int len = array.length;
        int sum, maxSum = Integer.MIN_VALUE;
        // i 为起始点，j 为结束点
        for (int j = 0; j < len; j++) {
            for (int i = 0; i <= j; i++) {
                sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += array[k];
                }
                if (sum > maxSum) {
                    maxSum = sum;
                }
            }
        }
        return maxSum;
    }

    public int find2(int[] array) {
        // array 至少有一个元素
        // 当前连续子向量的累计值
        int sum = 0;
        // 最大累计值
        int maxSum = Integer.MIN_VALUE;
        for (int val : array) {
            // 根据上一个累计值（不包含 val）判断
            // val 一定要被计算在内，否则就可能不连续了
            // 因此有两个选择：累加或重新算（丢弃上一个累计值）
            if (sum > 0) {
                // 如果上一个累计值为正数，无论 val 的正负性，累加 val 的值必定比 val 大，因此累加
                sum += val;
            } else {
                // 如果上一个累计值为非正数，无论 val 的正负性，val 必定不会比累加 val 的值小（大于等于），因此重新算
                sum = val;
            }
            // 如果新的累计值大于之前最大的累计值，更新
            if (sum > maxSum) {
                maxSum = sum;
            }
        }
        return maxSum;
    }

    // 动态规划
    public int find3(int[] array) {
        int len = array.length;
        // sums[i] 表示以 array[i] 结尾的连续子数组最大和
        int[] sums = new int[len];
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            if (i == 0) {
                sums[i] = array[i];
            } else {
                // 累加或重新算，看谁结果大就选择谁
                // 关键看上一个累计值 sums[i - 1]
                // 如果其为正数，累加后肯定比重新算大，如果为负则小
                // 这样就可以和 find2 中的思想对应起来了
                sums[i] = Math.max(sums[i - 1] + array[i], array[i]);
            }
            if (sums[i] > maxSum) {
                maxSum = sums[i];
            }
        }
        return maxSum;
    }

    public static void test() {
        System.out.println(new FindGreatestSumOfSubArray().find3(new int[]{6, -3, -2, 7, -15, 1, 2, 2}));
    }
}
