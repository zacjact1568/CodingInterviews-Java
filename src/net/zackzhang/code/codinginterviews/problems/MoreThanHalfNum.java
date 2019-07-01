package net.zackzhang.code.codinginterviews.problems;

import java.util.HashMap;
import java.util.Map;

/** 数组中出现次数超过一半的数字 */
public class MoreThanHalfNum {

    private int find(int[] array) {
        Map<Integer, Integer> hm = new HashMap<>();
        for (int num : array) {
            hm.put(num, hm.getOrDefault(num, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : hm.entrySet()) {
            if (entry.getValue() > array.length / 2) {
                return entry.getKey();
            }
        }
        return 0;
    }

    private int find2(int[] array) {
        // 候选数（动态更新）
        int result = 0;
        // 候选数的出现次数
        int times = 0;
        for (int num : array) {
            if (times == 0) {
                result = num;
                times = 1;
            } else if (num == result) {
                // 如果当前数与候选数相同，出现次数 + 1
                times++;
            } else {
                // 如果当前数与候选数不同，出现次数 - 1（相当于同时删掉一对不同的数）
                times--;
            }
        }
        // 按对删除不同的数后，剩下的数是超过一半的
        // 但也不一定（e.g. 1, 2, 3），因此还需验证 result 是否确实满足条件
        times = 0;
        for (int num : array) {
            if (num == result) {
                times++;
            }
        }
        if (times > array.length / 2) {
            return result;
        }
        return 0;
    }

    public static void test() {
        System.out.println(new MoreThanHalfNum().find2(
                new int[]{1, 2, 3, 2, 2, 2, 5, 4, 2}
        ));
    }
}
