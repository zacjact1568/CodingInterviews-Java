package net.zackzhang.code.codinginterviews.problems;

public class FindFirstCommonNode {

    private static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    private ListNode find(ListNode pHead1, ListNode pHead2) {
        int len1 = length(pHead1);
        int len2 = length(pHead2);
        ListNode p1 = pHead1, p2 = pHead2;
        if (len1 > len2) {
            p1 = walk(pHead1, len1 - len2);
        } else {
            p2 = walk(pHead2, len2 - len1);
        }
        while (p1 != null) {
            if (p1 == p2) break;
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1;
    }

    private int length(ListNode head) {
        ListNode p = head;
        int count = 0;
        while (p != null) {
            p = p.next;
            count++;
        }
        return count;
    }

    private ListNode walk(ListNode head, int step) {
        ListNode p = head;
        while (step != 0) {
            p = p.next;
            step--;
        }
        return p;
    }

    public static void test() {
        // 构造两个链表（相交节点值为 4）
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(4);
        ListNode c = new ListNode(5);
        ListNode d = new ListNode(8);
        ListNode e = new ListNode(3);
        ListNode f = new ListNode(6);
        a.next = b;
        b.next = c;
        c.next = d;
        e.next = f;
        f.next = b;
        System.out.println(new FindFirstCommonNode().find(a, e));
    }
}
