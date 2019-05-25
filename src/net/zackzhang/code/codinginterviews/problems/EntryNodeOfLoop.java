package net.zackzhang.code.codinginterviews.problems;

import java.util.HashSet;

public class EntryNodeOfLoop {

    private static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    private HashSet<ListNode> mWalkedNodes = new HashSet<>();

    // 利用 HashSet 的快取特性
    private ListNode find(ListNode pHead) {
        ListNode p = pHead;
        while (p != null) {
            if (mWalkedNodes.contains(p)) return p;
            mWalkedNodes.add(p);
            p = p.next;
        }
        return null;
    }

    // 快慢指针（如果有环，最终快指针肯定能追上慢指针）
    private ListNode find2(ListNode pHead) {
        ListNode pSlow = pHead, pFast = pHead;
        // 快指针一定比慢指针走得快，因此判断快指针是否为空即可
        // 并且快指针一次走两步，因此还要判断其下个结点是否为空
        while (pFast != null && pFast.next != null) {
            // 慢指针走 1 步
            pSlow = pSlow.next;
            // 快指针走 2 步
            pFast = pFast.next.next;
            // 当两个指针相遇
            if (pSlow == pFast) {
                // 快指针回到起点
                pFast = pHead;
                // 两个指针再次一起走，相遇点就是第一个入口结点
                while (pSlow != pFast) {
                    pSlow = pSlow.next;
                    pFast = pFast.next;
                }
                return pSlow;
            }
        }
        return null;
    }

    public static void test() {
        // 构造有环链表（入口节点为 b）
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(4);
        ListNode c = new ListNode(5);
        ListNode d = new ListNode(8);
        a.next = b;
        b.next = c;
        c.next = d;
        d.next = b;
        System.out.println(new EntryNodeOfLoop().find2(a));
    }
}
