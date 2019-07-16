package net.zackzhang.code.codinginterviews.problems;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

/** 滑动窗口的最大值 */
public class MaxInWindows {

    private ArrayList<Integer> max(int[] num, int size) {
        ArrayList<Integer> res = new ArrayList<>();
        if (size == 0) return res;
        for (int i = 0; i < num.length - size + 1; i++) {
            int miw = num[i];
            for (int j = i + 1; j < i + size; j++) {
                if (num[j] > miw) {
                    miw = num[j];
                }
            }
            res.add(miw);
        }
        return res;
    }

    private ArrayList<Integer> max2(int[] num, int size) {
        ArrayList<Integer> res = new ArrayList<>();
        if (size == 0) return res;
        // 双端队列，存储由头到尾降序的下标
        Deque<Integer> deq = new LinkedList<>();
        for (int i = 0; i < num.length; i++) {
            // i 代表当前窗口结束下标（aka 新加的数字）
            int start = i - size + 1;
            if (deq.isEmpty()) {
                // 如果队为空，直接放入（仅对于第一个）
                deq.add(i);
            } else if (start > deq.peekFirst()) {
                // 如果当前窗口起始下标大于队头元素
                // 说明队头元素已过期，弹出
                deq.pollFirst();
            }
            // 当队尾元素处的数字不大于新加的数字
            // 反复弹出队尾元素，因为已经有比它所在位置的数字更大的数字了
            // 相等也考虑是因为下标越靠后越好？
            while (!deq.isEmpty() && num[deq.peekLast()] <= num[i]) {
                deq.pollLast();
            }
            // 再放入新加数字的下标
            // 这样可以保持降序
            deq.add(i);
            if (start >= 0) {
                // 队头就是当前窗口中最大数字的下标
                res.add(num[deq.peekFirst()]);
            }
        }
        return res;
    }

    // 实际上与 max2 是一样的
    private ArrayList<Integer> max3(int[] num, int size) {
        ArrayList<Integer> res = new ArrayList<>();
        if (size == 0) return res;
        // 相当于双端队列，存储由头到尾降序的下标
        LinkedList<Integer> mq = new LinkedList<>();
        for (int i = 0; i < num.length; i++) {
            // i 代表当前窗口结束下标（aka 新加的数字）
            // 新加的数字不小于队尾元素处的数字，反复弹出队尾元素，直到小于为止
            while(!mq.isEmpty() && num[mq.peekLast()] <= num[i]) {
                mq.pollLast();
            }
            // 将新加数字的下标放入队尾
            mq.addLast(i);
            // 如果队头元素等于当前窗口结束下标减窗口大小
            // 说明队头元素已经过期
            // 只用判断一次，因为一次只能过期一个
            if (mq.peekFirst() == i - size) {
                mq.pollFirst();
            }
            // 当前下标已经可以作为一个窗口的结束
            // 说明已经有完整的窗口了
            // 可以将队头元素处的数字添加进结果列表了
            if (i >= size - 1) {
                res.add(num[mq.peekFirst()]);
            }
        }
        return res;
    }

    public static void test() {
        System.out.println(new MaxInWindows().max3(
                new int[]{2, 3, 4, 2, 6, 2, 5, 1},
                3
        ));
    }
}
