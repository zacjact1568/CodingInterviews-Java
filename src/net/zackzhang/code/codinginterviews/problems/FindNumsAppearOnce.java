package net.zackzhang.code.codinginterviews.problems;

import java.util.HashSet;

public class FindNumsAppearOnce {

    private void find(int[] array, int[] num1, int[] num2) {
        HashSet<Integer> hs = new HashSet<>();
        for (int num : array) {
            if (!hs.add(num)) {
                hs.remove(num);
            }
        }
        Object[] rems = hs.toArray();
        num1[0] = (int) rems[0];
        num2[0] = (int) rems[1];
    }

    private void find2(int[] array, int[] num1, int[] num2) {
        // 0 和任何数异或都等于原数
        int temp0 = 0;
        for (int num : array) {
            temp0 ^= num;
        }
        // temp0 等于 num1^num2
        // first1Mask 由 temp0 中第一个 1 的位置为 1，其余为 0 构成
        // num1 和 num2 在该位上肯定不同（一个为 0 一个为 1）
        int first1Mask = temp0 & (~temp0 + 1);
        int temp1 = 0;
        for (int num : array) {
            if ((num & first1Mask) != 0) {
                // num 在 temp0 中第一个 1 的位置上也为 1
                // num1 和 num2 只可能同时有一个符合
                // 也就是说，符合该条件的数字中有有且仅有一个只出现了一次（num1 或 num2）
                temp1 ^= num;
            }
        }
        // temp1 等于 num1 或 num2
        num1[0] = temp1;
        // 另一个数字再异或一次求出
        num2[0] = temp0^temp1;
    }

    public static void test() {
        int[] num1 = new int[1];
        int[] num2 = new int[1];
        new FindNumsAppearOnce().find2(new int[]{5, 3, 2, 6, 5, 4, 2, 6}, num1, num2);
        System.out.println(num1[0] + ", " + num2[0]);
    }
}
