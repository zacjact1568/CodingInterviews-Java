package net.zackzhang.code.codinginterviews.problems;

/** 链表中倒数第k个结点 */
public class FindKthToTail {

    private static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    private ListNode find(ListNode head, int k) {
        ListNode a = head, b = head;
        // b 先走 k 步
        for (int i = 0; i < k; i++) {
            // 还没走到 k 步就走到链表末尾之后了
            // 说明链表长度不足 k
            if (b == null) return null;
            b = b.next;
        }
        // a、b 一起走
        while (b != null) {
            a = a.next;
            b = b.next;
        }
        // 最终 a 指向的就是倒数第 k 个节点
        return a;
    }

    public static void test() {
        // 构造链表
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(4);
        ListNode c = new ListNode(5);
        ListNode d = new ListNode(8);
        ListNode e = new ListNode(3);
        ListNode f = new ListNode(6);
        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        e.next = f;
        System.out.println(new FindKthToTail().find(a, 3).val);
    }
}
