package com.speed.week1;

import java.util.Arrays;

/**
 * 环形链表
 * -判断是否有环
 * -如果有环, 给出环入口
 *
 * @author bruce.zhu@snowballtech.com
 * @since 2021-12-30 06:37:01
 */
public class CircularLinkedList {
    public static void main(String[] args) {
        // 方法1：直接使用HashSet, 时间复杂度O(n), 空间复杂度O(n), 这里就不赘言了
        // 方法2：双指针 (快慢相遇) (如果要计算环的长度, 2次相遇中间slow走过的长度, 这个就像是绕着操场长跑比赛一样)
        final int[] sourceIndexes = new int[]{1, 2, 3, 4, 5, 6, 7, 4, 5, 6};
        System.out.println("hasCircle: " + hasCircle(input(sourceIndexes)));
        // 进一步：计算环入口
        System.out.println("circleInlet: " + circleInlet(input(sourceIndexes)));
    }

    /**
     * 双指针方法判定是否有环
     *
     * @param head
     * @return 如果有环, 返回环的index值 (如果有, 单向链表也有且仅有一个环), 如果没有, 返回null
     */
    private static boolean hasCircle(final LinkedNode head) {
        LinkedNode fast = head;
        LinkedNode slow = head;
        // 快慢相遇, 时间复杂度最大O(n), 空间复杂度O(1)
        // 当然, 如果时间复杂度直接撑到O(n), 可以先找到trailer节点, 如果其有next, 即表示有环
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast.equals(slow)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 双指针方法找到环入口
     *
     * @param head
     * @return 如果没有环, 返回null
     */
    private static LinkedNode circleInlet(final LinkedNode head) {
        /*
         * slow每个时间走一步, fast每个时间走2步
         * 首次相遇时, 套了一圈, 继续走下去, 则相遇点不变, 则无结果
         * 设环入口为第a个节点, slow到达入口后, 又走了b的距离与fast相遇, 而环剩余长度为c, 则a+b+n(b+c)=2*(a+b), a = (n-1)(b+c) + c
         * 而当前位于b的位置, 即再走c就可以到达环入口, 然后一圈一圈等待, 只要二者已同样的速度, 一定可以在环入口处相遇
         */
        LinkedNode fast = head;
        LinkedNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast.equals(slow)) {
                fast = head;
                while (!fast.equals(slow)) {
                    fast = fast.next;
                    slow = slow.next;
                }
                return fast;
            }
        }
        return null;
    }

    /**
     * 为了方便由int[]构建初始化链表, 覆写hashCode方法与equals方法, 使用equals判定是否有环
     * 真实的, 直接使用==判定LinkedNode对象是否是同一个进行判定, 但是初始化的过程比较麻烦
     */
    static class LinkedNode {
        int index;
        LinkedNode next;

        LinkedNode(int index) {
            this.index = index;
        }

        @Override
        public String toString() {
            return String.format("{index: %d, next: %s}", index, next);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }
            LinkedNode that = (LinkedNode) o;
            return index == that.index;
        }

        @Override
        public int hashCode() {
            return this.index;
        }
    }

    private static LinkedNode input(final int[] source) {
        System.out.println("CircularLinkedList input : " + Arrays.toString(source));
        final LinkedNode head = new LinkedNode(source[0]);
        LinkedNode current = head;
        for (int i = 1; i < source.length; i++) {
            current.next = new LinkedNode(source[i]);
            current = current.next;
        }
        return head;
    }
}
