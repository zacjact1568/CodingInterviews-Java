package net.zackzhang.code.codinginterviews.problems;

import java.util.Arrays;

/** 扑克牌顺子 */
public class IsContinuous {

    private boolean isContinuous(int[] numbers) {
        if (numbers == null || numbers.length == 0) return false;
        // 排序
        Arrays.sort(numbers);
        // 统计 0 的个数
        int zeroCount = 0;
        // 间隔的个数
        int gapCount = 0;
        for (int i = 0; i < numbers.length - 1; i++) {
            if (numbers[i] == 0) {
                zeroCount++;
            } else if (numbers[i] == numbers[i + 1]) {
                // 相邻两非 0 数相等
                // 说明有两个相同的数，不是顺子
                return false;
            } else {
                // 相邻两非 0 数不等
                // 计算它们的间隔
                gapCount += numbers[i + 1] - numbers[i] - 1;
            }
        }
        // 由于 0 能代表任意数
        // 则若有足够的 0 能填补间隔，则是顺子
        return gapCount <= zeroCount;
    }

    private boolean isContinuous2(int[] numbers) {
        // 限制 5 个数字
        if (numbers.length != 5) return false;
        int flag = 0;
        // 最大为 13，最小为 0
        // 这样初始化保证第一个数能赋值给 max 和 min
        int max = -1, min = 14;
        for (int num : numbers) {
            // 数字超出范围
            if (num < 0 || num > 13) return false;
            if (num == 0) continue;
            // 判断是否有重复数字
            // 判断当前遍历的数字在 flag 对应位上是否为 1
            // 如果为 1，则该数字已出现过
            if (((flag >> num) & 1) == 1) return false;
            // 将 flag 对应位置为 1
            flag |= (1 << num);
            // 更新当前的最大值
            if (num > max) {
                max = num;
            }
            // 更新当前的最小值
            if (num < min) {
                min = num;
            }
            // 如果已发现的非 0 最大值与最小值之间相差大于等于 5
            // 说明连 0 都不够填充间隔了（最多 3 个 0）
            if (max - min >= 5) return false;
            // 如果已发现的非 0 最大值与最小值之间相差小于 5
            // 设差为 4，则中间相差 3 个数字
            // 若其他三个数字都是 0，由 0 来凑这三个数
            // 若只有两个数字为 0，那另一个一定是在最大值与最小值之间的
            // 则由 0 来凑空缺的两个数
            // 以此类推
        }
        return true;
    }

    public static void test() {
        System.out.println(new IsContinuous().isContinuous2(new int[]{4, 3, 0, 5, 6}));
    }
}
