package net.zackzhang.code.codinginterviews.problems;

import java.util.ArrayList;

/** 和为S的连续正数序列 */
public class FindContinuousSequence {

    private ArrayList<ArrayList<Integer>> find(int sum) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        // i 表示分解数字的个数
        // i 从 sum 开始减小，这样才能保证起始数从大到小
        for (int i = sum; i > 1; i--) {
            if (i % 2 == 1 && sum % i == 0 || i % 2 == 0 && sum % i == i / 2) {
                // i 为奇数时，如果 sum / i 能整除，说明存在中间数为商的连续分解
                // i 为偶数时，如果 sum / i 为 *.5，说明存在中间两数平均为商的连续分解
                // 计算起始数时，若 i 为偶数，需再加 1
                int start = sum / i - i / 2 + (i % 2 == 0 ? 1 : 0);
                if (start > 0) {
                    // 若起始数为正数
                    ArrayList<Integer> oneCase = new ArrayList<>();
                    for (int j = 0; j < i; j++) {
                        oneCase.add(start + j);
                    }
                    result.add(oneCase);
                }
            }
        }
        return result;
    }

    private ArrayList<ArrayList<Integer>> find2(int sum) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        int small = 1, big = 2;
        // 若 small 等于 (sum + 1) / 2，就不可能与比它更大的数的和为 sum 了
        // e.g. sum 为 99，那 small 最大只能为 49，不能为 50
        while (small < (sum + 1) / 2) {
            int curSum = rangeSum(small, big);
            if (curSum == sum) {
                // 找到一组数
                ArrayList<Integer> oneCase = new ArrayList<>();
                for (int i = small; i <= big; i++) {
                    oneCase.add(i);
                }
                result.add(oneCase);
                // 增大 big，找下一组
                big++;
            } else if (curSum > sum) {
                // small 到 big 的和大于所需和
                // 增大最小数（只前进不后退），就可以减小和
                small++;
            } else {
                // small 到 big 的和小于所需和
                // 增大最大数（只前进不后退），就可以增大和
                big++;
            }
        }
        return result;
    }

    // small 到 big 的和
    private int rangeSum(int small, int big) {
        int result = 0;
        for (int i = small; i <= big; i++) {
            result += i;
        }
        return result;
    }

    public static void test() {
        System.out.println(new FindContinuousSequence().find2(99));
    }
}
