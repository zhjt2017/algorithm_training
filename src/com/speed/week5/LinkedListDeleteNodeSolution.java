package com.speed.week5;

/**
 * 算法实现: 删除链表的节点
 * <p>
 * - 给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。
 * - 返回删除后的链表的头节点。
 * <p>
 * - 输入: head = [4,5,1,9], val = 5
 * 输出: [4,1,9]
 * <p>
 * - 输入: head = [4,5,1,9], val = 1
 * 输出: [4,5,9]
 * <p>
 * - 题目保证链表中节点的值互不相同
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-01-31 11:22:46
 */
public class LinkedListDeleteNodeSolution {
    public static void main(String[] args) {
        final LinkedListDeleteNodeSolution solution = new LinkedListDeleteNodeSolution();

        int val = 5;
        ListNode head = new ListNode(4);
        head.next = new ListNode(5);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(9);
        System.out.println("Input head : " + head + ", val : " + val);
        System.out.println("Output head : " + solution.deleteNode(head, val));

        val = 1;
        head = new ListNode(4);
        head.next = new ListNode(5);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(9);
        System.out.println("Input head : " + head + ", val : " + val);
        System.out.println("Output head : " + solution.deleteNode(head, val));
    }

    /**
     * 设计思想: 逃不掉2点 (查看jdk的LinkedList, 其实现也是一样的)
     * 1. O(N)找到待删除节点 (base case : node.next == null || node.next.val = val, 删除node.next节点)
     * 2. O(1)删除节点
     * 总体, 时间复杂度 O(N) 空间复杂度 O(1)
     * 特例:
     * 1. 实际要删除的是第一个节点, 直接返回head.next
     * 2. 实际要删除的是最后一个节点, 其前一个节点设置为null (这也是待删除节点的next, 所以这不算特例)
     * 3. 没有找到待删除节点, 直接返回head (返回head是一致的, 只是不用执行删除那一步)
     *
     * @param head
     * @param val
     * @return
     */
    private ListNode deleteNode(final ListNode head, final int val) {
        if (head == null) {
            return null;
        }
        if (head.val == val) {
            return head.next;
        }
        ListNode node = head;
        while (node.next != null && node.next.val != val) {
            node = node.next;
        }
        if (node.next != null) {
            node.next = node.next.next;
        }
        return head;
    }

    static class ListNode {
        private int val;
        private ListNode next;

        ListNode(final int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return String.format("{\"val\": %d, \"next\": %s}", this.val, this.next);
        }
    }
}
