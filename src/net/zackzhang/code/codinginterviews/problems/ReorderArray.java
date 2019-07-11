package net.zackzhang.code.codinginterviews.problems;

import java.util.ArrayList;
import java.util.Arrays;

/** 调整数组顺序使奇数位于偶数前面 */
public class ReorderArray {

    private void reorder(int[] array) {
        int[] array2 = array.clone();
        int seq = 0;
        // 奇数
        for (int num : array2) {
            if (num % 2 == 1) {
                array[seq++] = num;
            }
        }
        // 偶数
        for (int num : array2) {
            if (num % 2 == 0) {
                array[seq++] = num;
            }
        }
    }

    private void reorder2(int[] array) {
        ArrayList<Integer> odd = new ArrayList<>();
        ArrayList<Integer> even = new ArrayList<>();
        for (int num : array) {
            if (num % 2 == 0) {
                // 偶数
                even.add(num);
            } else {
                // 奇数
                odd.add(num);
            }
        }
        // 将偶数添加到奇数后面
        odd.addAll(even);
        // 修改原数组
        for (int i = 0; i < array.length; i++) {
            array[i] = odd.get(i);
        }
    }

    // 仿冒泡排序
    private void reorder3(int[] array) {
        for (int i = 0; i < array.length; i++) {
            // 一趟排序，确定最后一个元素
            for (int j = 0; j < array.length - 1 - i; j++) {
                // 前偶后奇就交换
                if (array[j] % 2 == 0 && array[j + 1] % 2 == 1) {
                    int temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                }
            }
        }
    }

    public static void test() {
        int[] array = new int[]{1, 5, 6, 8, 9, 2, 6, 4, 3, 7};
        new ReorderArray().reorder3(array);
        System.out.println(Arrays.toString(array));
    }
}
