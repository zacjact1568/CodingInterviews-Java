package net.zackzhang.code.codinginterviews.problems;

public class GetNumberOfK {

    private int get(int[] array, int k) {
        int count = 0;
        for (int num : array) {
            if (num > k) break;
            if (num == k) {
                count++;
            }
        }
        return count;
    }

    // 二分查找
    private int get2(int[] array, int k) {
        int len = array.length;
        int firstK = getFirstK(array, k, 0, len - 1);
        int lastK = getLastK(array, k, 0, len - 1);
        if (firstK != -1 && lastK != -1) {
            return lastK - firstK + 1;
        }
        return 0;
    }

    // 寻找第一个 k 出现的位置（使用递归）
    private int getFirstK(int[] array, int k, int start, int end) {
        // 未找到，返回 -1
        if (start > end) return -1;
        int m = (start + end) / 2;
        int mNum = array[m];
        int s = start, e = end;
        if (mNum > k) {
            e = m - 1;
        } else if (mNum < k) {
            s = m + 1;
        } else {
            // mNum == k
            if (m > 0 && array[m - 1] == k) {
                // 若 m 前一个位置的数字还是 k，将结束位置设为 m 前一个位置
                e = m - 1;
            } else {
                // 若 m 前一个位置的数字不是 k，说明 m 就是第一个 k
                return m;
            }
        }
        return getFirstK(array, k, s, e);
    }

    // 寻找最后一个 k 出现的位置（使用循环）
    private int getLastK(int[] array, int k, int start, int end) {
        int s = start, e = end;
        while (s <= e) {
            int m = (s + e) / 2;
            int mNum = array[m];
            if (mNum > k) {
                e = m - 1;
            } else if (mNum < k) {
                s = m + 1;
            } else {
                // mNum == k
                if (m < array.length - 1 && array[m + 1] == k) {
                    // 若 m 后一个位置的数字还是 k，将起始位置设为 m 后一个位置
                    s = m + 1;
                } else {
                    // 若 m 后一个位置的数字不是 k，说明 m 就是最后一个 k
                    return m;
                }
            }
        }
        return -1;
    }

    public static void test() {
        System.out.println(new GetNumberOfK().get2(new int[]{1, 2, 4, 4, 5, 7}, 4));
    }
}
