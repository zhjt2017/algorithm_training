package com.speed.week1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 反转单向链表
 *
 * @author bruce.zhu@snowballtech.com
 * @since 2021-12-28 11:45:35
 */
public class SingleLinkedListReverse {
    public static void main(String[] args) {
        LinkedNode head = input(new int[]{1, 2, 3, 4, 5, 6});
        output(reverse(head));
        head = input(new int[]{1, 2, 3, 4, 5, 6});
        output(reverse2(head));

        head = input(new int[]{1});
        output(reverse(head));
        head = input(new int[]{1});
        output(reverse2(head));
    }

    static class LinkedNode {
        int value;
        LinkedNode next;

        LinkedNode(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return String.format("{value: %d, next: %s}", value, next);
        }
    }

    /**
     * 算法：反转 (输入与输出均以head结点表示整个链表)
     * 方法一：迭代+递归
     *
     * @param head
     * @return
     */
    private static LinkedNode reverse(final LinkedNode head) {
        // 递归终止条件是没有下一个节点
        if (head.next == null) {
            return head;
        }
        LinkedNode reverseHead = reverse(head.next);
        // 建立反转后1->2的关系, 出栈后, 建立反转后的2->3的关系... (递归时head逐层收回)
        head.next.next = head;
        // 建立反转后的最后一个元素, 其next=null (避免成为循环列表, 导致StackOverflowError)
        head.next = null;
        // 递归结果每次都返回反转后的头节点
        return reverseHead;
    }

    /**
     * 算法：反转 (输入与输出均以head结点表示整个链表)
     * 方法二：迭代+双指针
     *
     * @param head
     * @return
     */
    private static LinkedNode reverse2(final LinkedNode head) {
        /* 原先左边是head, 现在整体往右推移, 直到最右边那个作为head */
        LinkedNode pre = null;
        LinkedNode cur = head;
        LinkedNode tmp;
        while (cur != null) {
            tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        return pre;
    }

    private static LinkedNode input(final int[] source) {
        System.out.println("input : " + Arrays.toString(source));
        final LinkedNode head = new LinkedNode(source[0]);
        LinkedNode current = head;
        for (int i = 1; i < source.length; i++) {
            current.next = new LinkedNode(source[i]);
            current = current.next;
        }
        System.out.println("input LinkedNode: " + head);
        return head;
    }

    private static List<Integer> output(final LinkedNode reversedHead) {
        System.out.println("reversed head LinkedNode: " + reversedHead);
        final List<Integer> list = new ArrayList<>();
        list.add(reversedHead.value);
        LinkedNode current = reversedHead;
        while (current.next != null) {
            current = current.next;
            list.add(current.value);
        }
        System.out.println("reversed result: " + list);
        return list;
    }
}
