package net.zackzhang.code.codinginterviews.problems;

import java.util.ArrayList;
import java.util.Arrays;

/** 和为S的两个数字 */
public class FindNumbersWithSum {

    private ArrayList<Integer> find(int[] array, int sum) {
        ArrayList<Integer> result = new ArrayList<>(2);
        for (int i = 0; i < array.length; i++) {
            int num1 = array[i];
            int num2 = sum - num1;
            // 只需在后面的数字中查找 num2（前面的之前已经找过了）
            // 如果 num2 小于 num1，直接不用找了，因为数组递增，根本找不到
            if (num1 < num2) {
                for (int j = i; j < array.length; j++) {
                    if (array[j] == num2) {
                        // 只要找到符合条件的两个数，直接就可返回
                        // 因为数组递增，所以最先找到的两个数，num1 肯定较小，num2 肯定较大
                        // 相差较大的两个数，乘积较小，因此最先找到的两个数乘积肯定较小
                        result.add(0, num1);
                        result.add(1, num2);
                        return result;
                    }
                }
            }
        }
        return result;
    }

    private ArrayList<Integer> find2(int[] array, int sum) {
        ArrayList<Integer> result = new ArrayList<>(2);
        // i、j 分别表示两个数的位置
        // 两个数初始为第一个和最后一个数
        int i = 0, j = array.length - 1;
        int num1, num2, tempSum;
        while (i < j) {
            num1 = array[i];
            num2 = array[j];
            tempSum = num1 + num2;
            if (tempSum == sum) {
                result.add(0, num1);
                result.add(1, num2);
                return result;
            } else if (tempSum < sum) {
                // 如果此时选择的两个数之和小于 sum，应增大和
                // 由于数组递增，则第一个数的位置后移
                i++;
            } else {
                // 如果此时选择的两个数之和大于 sum，应减小和
                // 由于数组递增，则第二个数的位置前移
                j--;
            }
        }
        return result;
    }

    public static void test() {
        System.out.println(Arrays.toString(new FindNumbersWithSum().find2(
                new int[]{1, 2, 4, 5, 7, 9},
                9
        ).toArray()));
    }
}
