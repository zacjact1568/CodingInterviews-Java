package net.zackzhang.code.codinginterviews.problems;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/** 数据流中的中位数 */
public class Median {

    // ********* 解 1 *********

    // 有序数字列表
    private ArrayList<Integer> mNumList = new ArrayList<>();

    // 向流中送入数字，get 函数即处理所有送入的数据
    private void insert(int num) {
        int len = mNumList.size();
        for (int i = 0; i <= len; i++) {
            if (len == 0 || i == len || num < mNumList.get(i)) {
                mNumList.add(i, num);
                break;
            }
        }
    }

    private double get() {
        int len = mNumList.size();
        if ((len & 1) == 1) {
            // 奇数个
            return mNumList.get(len / 2);
        } else {
            // 偶数个
            return (mNumList.get(len / 2 - 1) + mNumList.get(len / 2)) / 2;
        }
    }

    // ********* 解 2 *********

    // 数据流中数字个数
    private int mCount = 0;
    // 大顶堆（默认是小顶堆，通过参数指定为大顶堆），储存较小数字部分
    private PriorityQueue<Integer> mMaxTopHeap = new PriorityQueue<>(Comparator.reverseOrder());
    // 小顶堆，储存较大数字部分
    private PriorityQueue<Integer> mMinTopHeap = new PriorityQueue<>();

    private void insert2(int num) {
        if ((mCount & 1) == 0) {
            // 加入前数据流中有偶数个数字
            // 初始数字是这种情况，但不执行下面的 if，直接插入小顶堆
            if (!mMaxTopHeap.isEmpty() && num < mMaxTopHeap.peek()) {
                // 加入的数字小于大顶堆堆顶元素（最大值）
                // 说明该数字属于较小数字部分，应插入大顶堆
                // 但插入后为保持平衡（两堆中元素个数差不超过 1）
                // 要将大顶堆最大的元素（堆顶）移至小顶堆
                mMaxTopHeap.offer(num);
                num = mMaxTopHeap.poll();
            }
            mMinTopHeap.offer(num);
        } else {
            // 加入前数据流中有奇数个数字
            if (!mMinTopHeap.isEmpty() && num > mMinTopHeap.peek()) {
                // 加入的数字大于小顶堆堆顶元素（最小值）
                // 说明该数字属于较大数字部分，应插入小顶堆
                // 同理，要将小顶堆最小的元素移至大顶堆
                mMinTopHeap.offer(num);
                num = mMinTopHeap.poll();
            }
            mMaxTopHeap.offer(num);
        }
        mCount++;
    }

    private double get2() {
        // 自行演示可知，两个堆只有两种情况
        // 元素个数相同（mCount 为偶），或小顶堆比大顶堆多 1（mCount 为奇）
        // 这是插入规则决定的
        if ((mCount & 1) == 1) {
            // 奇数个数字，直接取小顶堆堆顶元素
            return mMinTopHeap.peek();
        } else {
            // 偶数个数字，两堆堆顶元素取平均
            return (mMaxTopHeap.peek() + mMinTopHeap.peek()) / 2;
        }
    }

    public static void test() {
        Median m = new Median();
        m.insert2(2);
        m.insert2(5);
        m.insert2(6);
        m.insert2(1);
        m.insert2(8);
        m.insert2(0);
        m.insert2(3);
        System.out.println(m.get2());
    }
}
