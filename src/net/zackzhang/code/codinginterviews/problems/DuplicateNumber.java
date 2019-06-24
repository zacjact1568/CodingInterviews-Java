package net.zackzhang.code.codinginterviews.problems;

import java.util.HashSet;

/** 数组中重复的数字 */
public class DuplicateNumber {

    private boolean find(int[] numbers, int length, int[] duplication) {
        if (numbers != null) {
            HashSet<Integer> hs = new HashSet<>();
            for (int num : numbers) {
                if (!hs.add(num)) {
                    // hs 中已经有 num 了，说明出现重复
                    duplication[0] = num;
                    return true;
                }
            }
        }
        duplication[0] = -1;
        return false;
    }

    private boolean find2(int[] numbers, int length, int[] duplication) {
        if (numbers != null) {
            // length 为 numbers 的长度，numbers 中的数字在 0 到 length-1 的范围内
            boolean[] b = new boolean[length];
            for (int num : numbers) {
                // 如果之前遍历过 num，b 中 num 的位置应被置为 true
                if (b[num]) {
                    duplication[0] = num;
                    return true;
                }
                b[num] = true;
            }
        }
        duplication[0] = -1;
        return false;
    }

    private boolean find3(int[] numbers, int length, int[] duplication) {
        if (numbers != null) {
            for (int i = 0; i < length; i++) {
                while (i != numbers[i]) {
                    // 重复直到 i 处的下标与值相等
                    int num = numbers[i];
                    if (num == numbers[num]) {
                        // 若 i 和 numbers[i] 处的元素相等，则找到重复
                        duplication[0] = num;
                        return true;
                    }
                    // 交换 i 和 numbers[i] 处的元素
                    numbers[i] = numbers[num];
                    numbers[num] = num;
                    // 此时，num 处的下标与值相等
                }
            }
        }
        duplication[0] = -1;
        return false;
    }

    public static void test() {
        int[] duplication = new int[1];
        new DuplicateNumber().find3(new int[]{2, 3, 1, 0, 2, 5, 3}, 7, duplication);
        System.out.println(duplication[0]);
    }
}
