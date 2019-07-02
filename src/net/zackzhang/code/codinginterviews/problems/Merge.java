package net.zackzhang.code.codinginterviews.problems;

/** 合并两个排序的链表 */
public class Merge {

    private static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    private ListNode merge(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        // 若 list1 不为空
        if (list2 == null) return list1;
        // 若 list1 和 list2 都不为空
        ListNode p1 = list1, p2 = list2;
        ListNode head = null, prev, cur = null;
        while (p1 != null && p2 != null) {
            prev = cur;
            if (p1.val < p2.val) {
                cur = p1;
                p1 = p1.next;
            } else {
                cur = p2;
                p2 = p2.next;
            }
            if (prev == null) {
                // 首次
                head = cur;
            } else {
                // 第二次或之后
                prev.next = cur;
            }
        }
        // 两个链表中有且仅有一个遍历完了
        // 此时 cur 指向遍历完那个链表的尾节点
        // 将它接到另一个链表的待遍历的节点上即可
        if (p1 == null) {
            cur.next = p2;
        } else {
            cur.next = p1;
        }
        return head;
    }

    // 使用递归
    private ListNode merge2(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;
        if (list1.val < list2.val) {
            list1.next = merge2(list1.next, list2);
            return list1;
        } else {
            list2.next = merge2(list1, list2.next);
            return list2;
        }
    }

    public static void test() {
        // 构造两个排序链表
        ListNode a1 = new ListNode(1);
        ListNode b1 = new ListNode(3);
        ListNode c1 = new ListNode(5);
        ListNode d1 = new ListNode(7);
        ListNode e1 = new ListNode(9);
        ListNode f1 = new ListNode(11);
        a1.next = b1;
        b1.next = c1;
        c1.next = d1;
        d1.next = e1;
        e1.next = f1;
        ListNode a2 = new ListNode(2);
        ListNode b2 = new ListNode(4);
        ListNode c2 = new ListNode(6);
        ListNode d2 = new ListNode(8);
        ListNode e2 = new ListNode(10);
        ListNode f2 = new ListNode(12);
        a2.next = b2;
        b2.next = c2;
        c2.next = d2;
        d2.next = e2;
        e2.next = f2;
        System.out.println(new Merge().merge2(a1, a2));
    }
}
