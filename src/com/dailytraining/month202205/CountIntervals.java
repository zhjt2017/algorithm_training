package com.dailytraining.month202205;

public class CountIntervals {

    public static void main(String[] args) {
        CountIntervals countIntervals = new CountIntervals();
        countIntervals.add(2, 3);
        countIntervals.add(7, 10);
        System.out.println(countIntervals.count());
        countIntervals.add(5, 8);
        System.out.println(countIntervals.count());
    }

    private Node head;
    /**
     * tail在2个场景下会发生变更：1、在tail后面新增一个数值更大的区间作为新的tail 2、tail被合并前面一个变成tail
     */
    private Node tail;
    private int count;

    public CountIntervals() {
        head = null;
        tail = null;
        count = 0;
    }

    public void add(int left, int right) {
        if (head == null) {
            head = new Node(left, right);
            tail = head;
            head.next = null;
            count = count - left + right + 1;
            return;
        }
        if (head.start > right + 1) {
            Node n = new Node(left, right);
            n.next = head;
            head = n;
            count = count - left + right + 1;
            return;
        }
        if (tail.end + 1 < left) {
            tail.next = new Node(left, right);
            tail = tail.next;
            count = count - left + right + 1;
            return;
        }
        // 1、过滤掉end<left的即没有交集的
        Node node = head;
        Node pre = head;
        while (node.end + 1 < left) {
            pre = node;
            node = node.next;
            continue;
        }
        // 2、夹在中间没有交集
        if (right + 1 < node.start) {
            count = count - left + right + 1;
            Node mid = new Node(left, right);
            mid.next = node;
            pre.next = mid;
            // System.out.println(String.format("left : %d, right : %d, count : %d, head : %s", left, right, count, head));
            return;
        }
        // 3、有交集，先处理左边界
        if (left < node.start) {
            count = count - left + node.start;
            node.start = left;
        }
        // 3.1、右边界不够长, 则不用处理右边界
        if (right <= node.end) {
            return;
        }
        // 3.2、只要跨了右边界，就合并右节点
        while (node.next != null && right + 1 >= node.next.start) {
            // 3.3、count每次跨越的range
            count = count - node.end + node.next.start - 1;
            node.end = node.next.end;
            node.next = node.next.next;
            if (node.next == null) {
                tail = node;
            }
            if (right <= node.end) {
                return;
            }
        }
        // 3.4、不能再合并更右的节点，处理右边界
        count = count - node.end + right;
        node.end = right;
    }

    public int count() {
        // System.out.println("get count : " + count);
        return count;
    }

    static class Node {
        int start;
        int end;
        Node next;

        Node(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return String.format("start : %d, end : %d, next : %s", start, end, next);
        }
    }
}
