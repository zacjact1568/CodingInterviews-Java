package net.zackzhang.code.codinginterviews.problems;

/** 删除链表中重复的结点 */
public class DeleteDuplication {

    private static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    // 自做
    private ListNode delete(ListNode pHead) {
        ListNode node = pHead, pre = null;
        boolean hasDup = false;
        while (node != null) {
            while (node.next != null && node.val == node.next.val) {
                hasDup = true;
                // 删掉下一个重复节点
                node.next = node.next.next;
            }
            if (hasDup) {
                // 删除剩余的重复节点（就是 node）
                if (pre == null) {
                    pHead = node.next;
                } else {
                    pre.next = node.next;
                }
                hasDup = false;
                // 删除后，pre 不变
            } else {
                // 若没有删除任何节点，pre 置为当前节点（node）
                pre = node;
            }
            // 若删除了节点，下一当前节点是最后删除的剩余重复节点（node）的 next
            // 若未删除节点，下一当前节点是当前节点（node）的 next
            node = node.next;
        }
        return pHead;
    }

    // 更好的
    private ListNode delete2(ListNode pHead) {
        // 新建辅助头节点
        ListNode head = new ListNode(-1);
        head.next = pHead;
        // pre 指向新建辅助头节点，cur 指向第一个节点
        ListNode pre = head, cur = pHead;
        while (cur != null) {
            if (cur.next != null && cur.val == cur.next.val) {
                // 如果 cur 与它下一节点值相同，说明需要删除
                // 从 cur 开始，若值相同，一直向后查找
                while (cur.next != null && cur.val == cur.next.val) {
                    cur = cur.next;
                }
                // cur 指向重复区间的最后一个节点
                // 它的下一节点就是值不同的节点
                cur = cur.next;
                pre.next = cur;
            } else {
                // 如果 cur 与它下一节点值不同，不需要删除
                // pre 和 cur 都后移一步
                pre = cur;
                cur = cur.next;
            }
        }
        return head.next;
    }

    public static void test() {
        // 构造排序链表
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(2);
        ListNode d = new ListNode(4);
        ListNode e = new ListNode(4);
        ListNode f = new ListNode(5);
        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        e.next = f;
        System.out.println(new DeleteDuplication().delete2(a));
    }
}
