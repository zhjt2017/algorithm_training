package com.homework.week1;

/**
 * 算法实现：合并2个有序链表 (不妨认为是2个升序链表，合并为一个新的升序链表，单向链表即可)
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-01-03 09:37:07
 */
public class MergeSortedListsSolution {
    public static void main(String[] args) {
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1 = head1.next;
        head1.next = new ListNode(4);
        head1 = head1.next;
        head1.next = new ListNode(7);

        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(3);
        head2 = head2.next;
        head2.next = new ListNode(5);
        head2 = head2.next;
        head2.next = new ListNode(5);
        head2 = head2.next;
        head2.next = new ListNode(6);
        head2 = head2.next;
        head2.next = new ListNode(7);
        head2 = head2.next;
        head2.next = new ListNode(8);

        System.out.println(head1);
        System.out.println(head2);
        System.out.println(merge(head1, head2));
    }

    static class ListNode {
        private int val;
        private ListNode next;

        ListNode(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return String.format("{\"val\":%d, \"next\":%s}", this.val, this.next);
        }
    }

    private static ListNode merge(final ListNode head1, final ListNode head2) {
        /*
         * 迭代+双指针
         * 指针处，将较小的那个插入
         * 边界情况：当1个链表先迭代完成时，插入另一个链表的剩余部分
         * 保护节点：增加保护节点，可以在返回时快速定位到head
         * 时间复杂度：O(m+n)
         * 空间复杂度：O(1) (由于是链表，不用新开一个链表)
         */
        ListNode node = head1;
        ListNode anotherNode = head2;
        final ListNode protection = new ListNode(0);
        ListNode mergedNode = protection;
        while (node != null && anotherNode != null) {
            if (node.val <= anotherNode.val) {
                mergedNode.next = node;
                node = node.next;
            } else {
                mergedNode.next = anotherNode;
                anotherNode = anotherNode.next;
            }
            mergedNode = mergedNode.next;
        }

        mergedNode.next = node != null ? node : anotherNode;
        return protection.next;
    }
}
