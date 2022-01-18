package com.homework.week3;

/**
 * 算法实现：多个升序链表的合并
 * - 返回合并后的链表
 * <p>
 * - 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * <p>
 * - 输入：lists = []
 * 输出：[]
 * <p>
 * - 输入：lists = [[]]
 * 输出：[]
 * <p>
 * k == lists.length
 * 0 <= k <= 10^4
 * 0 <= lists[i].length <= 500
 * -10^4 <= lists[i][j] <= 10^4
 * lists[i] 按 升序 排列
 * lists[i].length 的总和不超过 10^4
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-01-16 03:09:09
 */
public class MergeMultiSortedListSolution {
    public static void main(String[] args) {
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(4);
        head1.next.next = new ListNode(5);

        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(3);
        head2.next.next = new ListNode(4);

        ListNode head3 = new ListNode(2);
        head3.next = new ListNode(6);

        ListNode[] lists = new ListNode[]{head1, head2, head3};
        System.out.println("input: " + lists);
        System.out.println("merge result : " + mergetK(lists));

        lists = new ListNode[0];
        System.out.println("input: " + lists);
        System.out.println("merge result : " + mergetK(lists));

        lists = new ListNode[]{null};
        System.out.println("input: " + lists);
        System.out.println("merge result : " + mergetK(lists));
    }

    static class ListNode {
        private int val;
        private ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return String.format("{\"val\":%d, \"next\":%s}", this.val, this.next);
        }
    }

    private static ListNode mergetK(final ListNode[] lists) {
        if (lists.length == 0) {
            return new ListNode();
        }
        if (lists.length == 1) {
            return null;
        }
        return mergeMulti(lists, 0, lists.length - 1);
    }

    private static ListNode mergeMulti(final ListNode[] lists, final int start, final int end) {
        /*
         直接方法：第一个与第二个合并后，其结果与第三个合并，如此一直迭代下去 (时间复杂度O(k^2*n)空间复杂度O(1))，这里就不赘言了
         这里主要考虑“分治”的思想，来降低时间复杂度，将k^2降低为k*log(k)，则需要分层(每层自己的逻辑：merge2个合并后的链表)，使用递归实现
          (基于题目中k最大为10^4, 每次分层时mid=(start+end)>>1)
         直到base case: start==end, start不可能大于end, 此时不用合并，直接返回其链表本身
         */
        if (start == end) {
            return lists[start];
        }
        final int mid = (start + end) >> 1;
        return merge(mergeMulti(lists, start, mid), mergeMulti(lists, mid + 1, end));
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
