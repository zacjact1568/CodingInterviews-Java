package net.zackzhang.code.codinginterviews.problems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

/** 最小的K个数 */
public class GetLeastNumbers {

    // 小顶堆
    private ArrayList<Integer> get(int[] input, int k) {
        ArrayList<Integer> res = new ArrayList<>();
        if (k > input.length) return res;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int num : input) {
            pq.offer(num);
        }
        for (int i = 0; i < k; i++) {
            res.add(pq.poll());
        }
        return res;
    }

    // 快速排序的分割思想
    private ArrayList<Integer> get2(int[] input, int k) {
        ArrayList<Integer> res = new ArrayList<>();
        if (k > input.length) return res;
        int start = 0, end = input.length - 1;
        // 先分割一次，得到第一次的枢轴位置
        int pi = partition(input, start, end);
        while (pi != k - 1) {
            // 循环直到枢轴等于 k - 1
            // 则 k - 1 位置之前的数都比它小，之后的数都比它大
            // 则 0 ~ k - 1 就是最小的 k 个数
            if (pi > k - 1) {
                // 若枢轴位置在 k - 1 之后，将结束位置设为枢轴的前一个位置
                end = pi - 1;
            } else {
                // 若枢轴位置在 k - 1 之前，将起始位置设为枢轴的后一个位置
                start = pi + 1;
            }
            // 再次分割
            pi = partition(input, start, end);
        }
        // 前 k 个元素就是所求（可能无序）
        for (int i = 0; i < k; i++) {
            res.add(input[i]);
        }
        return res;
    }

    // 返回一次分割的枢轴位置
    // input 中在该位置之前的都小于该位置的值，之后的都大于
    private int partition(int[] input, int start, int end) {
        int i = start, j = end;
        int pivot = input[start];
        while (i < j) {
            while (i < j && input[j] > pivot) {
                j--;
            }
            input[i] = input[j];
            while (i < j && input[i] < pivot) {
                i++;
            }
            input[j] = input[i];
        }
        input[i] = pivot;
        return i;
    }

    // 冒泡排序
    private ArrayList<Integer> get3(int[] input, int k) {
        ArrayList<Integer> res = new ArrayList<>();
        if (k > input.length) return res;
        // 冒泡排序每趟确定一个元素的最终位置
        // 因此只用进行 k 趟，确定前 k 个元素的最终位置，就是最小的 k 个数
        for (int i = 0; i < k; i++) {
            // 从后往前冒泡
            for (int j = input.length - 1; j > i; j--) {
                if (input[j] < input[j - 1]) {
                    int temp = input[j];
                    input[j] = input[j - 1];
                    input[j - 1] = temp;
                }
            }
        }
        // 前 k 个元素就是所求（有序）
        for (int i = 0; i < k; i++) {
            res.add(input[i]);
        }
        return res;
    }

    // 大顶堆
    private ArrayList<Integer> get4(int[] input, int k) {
        ArrayList<Integer> res = new ArrayList<>();
        if (k > input.length) return res;
        // 构造大顶堆
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int num : input) {
            if (pq.size() != k) {
                // 前 k 个元素，直接入堆
                pq.offer(num);
            } else if (pq.peek() > num) {
                // 后面的元素，如果小于堆顶（堆中最大值）就删除堆顶，入堆
                // 也就是说，保持堆中是目前遍历到的最小 k 个元素
                pq.poll();
                pq.offer(num);
            }
        }
        // 堆中所有元素就是所求（倒序）
        for (int i = 0; i < k; i++) {
            res.add(pq.poll());
        }
        return res;
    }

    public static void test() {
        System.out.println(new GetLeastNumbers().get4(
                new int[]{4, 5, 1, 6, 2, 7, 3, 8},
                4
        ));
    }
}
