package com.teachingpractice.week4;

import com.homework.week3.MergeMultiSortedListSolution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 算法实现: 二叉堆-多个升序链表的合并
 * <p>
 * 将时间复杂度优化为n*k*logk
 * 之前的做法: 不断对合并操作本身进行分治, 当最底层的2个升序链表合并, 将其结果回溯到上一层, 继续进行合并
 * 这里, 我们将合并操作直接在所有待合并链表上一次性展开,
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
 * @see MergeMultiSortedListSolution
 * @since 2022-01-28 02:23:21
 */
public class MergeMultiSortedListByHeapSolution {
    public static void main(String[] args) {
        final MergeMultiSortedListByHeapSolution solution = new MergeMultiSortedListByHeapSolution();

        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(4);
        head1.next.next = new ListNode(5);

        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(3);
        head2.next.next = new ListNode(4);

        ListNode head3 = new ListNode(2);
        head3.next = new ListNode(6);

        ListNode[] lists = initListNodes();
        System.out.println("input: " + Arrays.asList(lists));
        System.out.println("merge result (mergeMultiByPriorityQueue) : " + solution.mergeMultiByPriorityQueue(lists));
        lists = initListNodes();
        System.out.println("merge result (mergeMultiByHeap) : " + solution.mergeMultiByHeap(lists));

        lists = new ListNode[0];
        System.out.println("input: " + Arrays.asList(lists));
        System.out.println("merge result (mergeMultiByPriorityQueue) : " + solution.mergeMultiByPriorityQueue(lists));
        lists = new ListNode[0];
        System.out.println("merge result (mergeMultiByHeap) : " + solution.mergeMultiByHeap(lists));

        lists = new ListNode[]{null};
        System.out.println("input: " + Arrays.asList(lists));
        System.out.println("merge result (mergeMultiByPriorityQueue) : " + solution.mergeMultiByPriorityQueue(lists));
        lists = new ListNode[]{null};
        System.out.println("merge result (mergeMultiByHeap) : " + solution.mergeMultiByHeap(lists));
    }

    private static ListNode[] initListNodes() {
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(4);
        head1.next.next = new ListNode(5);

        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(3);
        head2.next.next = new ListNode(4);

        ListNode head3 = new ListNode(2);
        head3.next = new ListNode(6);

        return new ListNode[]{head1, head2, head3};
    }


    static class ListNode implements Comparable {
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

        @Override
        public int compareTo(Object o) {
            if (o == null || !(o instanceof ListNode)) {
                throw new RuntimeException("ListNode compareTo target type invalid");
            }
            // Java提供的PriorityQueue默认小根堆
            final ListNode another = (ListNode) o;
            if (this.val == another.val) {
                return 1;
            }
            return this.val - another.val;
        }
    }

    /**
     * 利用程序库自带的优先队列(priority queue)数据结构 (默认大根堆)
     * C++ (重载运算符小于号)
     * bool operator < (const Node& a, const Node& b) {
     * return a.key > b.key;
     * }
     * Java (重写Comparator接口的compare方法) (PriorityQueue无界优先队列) (PriorityQueue非线性安全, PriorityBlockingQueue线性安全)
     * <p>
     * 时间复杂度: O(n*k*log(k)) (共n*k个节点放入优先队列, 优先队列中始终保持k个元素, 每次放入会经历一次删除和插入操作, log(k))
     * 空间复杂度: O(k)
     *
     * @param lists
     * @return
     */
    private ListNode mergeMultiByPriorityQueue(final ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        if (lists.length == 1) {
            return lists[0];
        }

        final int k = lists.length;
        final PriorityQueue<ListNode> pq = new PriorityQueue<>(k);
        for (final ListNode root : lists) {
            if (root != null) {
                pq.offer(root);
            }
        }

        final ListNode protection = new ListNode();
        ListNode tail = protection;
        while (!pq.isEmpty()) {
            tail.next = pq.poll();
            tail = tail.next;
            if (tail.next != null) {
                pq.offer(tail.next);
            }
        }
        return protection.next;
    }

    /**
     * 我们自行构建一个小根二叉堆, 提供功能方法: argument constructor, isEmpty, insert, deleteMin, getMin
     *
     * @param lists
     * @return
     */
    private ListNode mergeMultiByHeap(final ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        if (lists.length == 1) {
            return lists[0];
        }

        final int k = lists.length;
        final MyMinBinaryHeap heap = new MyMinBinaryHeap(k);
        for (final ListNode root : lists) {
            if (root != null) {
                heap.add(root);
            }
        }

        final ListNode protection = new ListNode();
        ListNode tail = protection;
        while (!heap.isEmpty()) {
            tail.next = heap.removeMin();
            tail = tail.next;
            if (tail.next != null) {
                heap.add(tail.next);
            }
        }
        return protection.next;
    }

    /**
     * 自行构建一个小根二叉堆
     * 实现方法: argument constructor, isEmpty, insert, deleteMin, getMin
     */
    static class MyMinBinaryHeap {
        private ArrayList<ListNode> list;
        private ListNode temp;

        MyMinBinaryHeap(final int capacity) {
            this.list = new ArrayList<>(capacity + 1);
            this.list.add(0, null);
        }

        boolean isEmpty() {
            return list.size() == 1;
        }

        void add(final ListNode node) {
            this.list.add(node);
            this.heapifyUp(this.list.size() - 1);
        }

        ListNode removeMin() {
            final ListNode removed = this.list.get(1);
            this.list.set(1, this.list.get(this.list.size() - 1));
            this.list.remove(this.list.size() - 1);
            this.heapifyDown(1);
            return removed;
        }

        ListNode getMin() {
            return this.list.get(1);
        }

        /**
         * 核心逻辑: 小根二叉堆向上调整一个节点
         *
         * @param p 节点位置 (第一个节点从位置1开始, 左节点或者右节点为p, 父节点为p>>1)
         */
        private void heapifyUp(final int p) {
            int next = p;
            while (next > 1) {
                if (this.list.get(next).val >= this.list.get(next >> 1).val) {
                    break;
                }
                swap(next, next >> 1);
                next = next >> 1;
            }
        }

        /**
         * 核心逻辑: 小根二叉堆向下调整一个节点
         *
         * @param p 节点位置 (第一个节点从位置1开始, 父节点为p, 左节点为p<<1, 右节点为(p<<1)+1)
         */
        private void heapifyDown(final int p) {
            int child = p;
            while ((child = child << 1) < this.list.size()) {
                if (child + 1 < this.list.size() && this.list.get(child).val > this.list.get(child + 1).val) {
                    child = child + 1;
                }
                if (this.list.get(child).val >= this.list.get(child >> 1).val) {
                    break;
                }
                swap(child, child >> 1);
            }
        }

        private void swap(final int firstIndex, final int secondIndex) {
            temp = this.list.get(firstIndex);
            this.list.set(firstIndex, this.list.get(secondIndex));
            this.list.set(secondIndex, temp);
        }
    }
}
