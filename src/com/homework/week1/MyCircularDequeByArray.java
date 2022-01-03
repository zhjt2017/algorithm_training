package com.homework.week1;

/**
 * 实现我的循环双端队列 (这里不考虑线性安全的问题)
 * - 数组与链表皆可实现，这里使用数组实现，总体空间复杂度 O(n)
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-01-03 11:01:39
 */
public class MyCircularDequeByArray {

    public static void main(String[] args) {
        final MyCircularDequeByArray circularDeque = new MyCircularDequeByArray(3);
        System.out.println(circularDeque.insertLast(1));
        System.out.println(circularDeque.insertLast(2));
        System.out.println(circularDeque.insertFront(3));
        System.out.println(circularDeque.insertFront(4));
        System.out.println(circularDeque.getRear());
        System.out.println(circularDeque.isFull());
        System.out.println(circularDeque.deleteLast());
        System.out.println(circularDeque.insertFront(4));
        System.out.println(circularDeque.getFront());
    }

    private int capacity;
    private int size;
    private int[] arr;
    private int headIndex;
    private int tailIndex;

    /**
     * 初始化：构造双端队列，给定容量大小
     *
     * @param capacity
     */
    MyCircularDequeByArray(int capacity) {
        if (capacity <= 0) {
            throw new RuntimeException("capacity invalid");
        }
        // 总有一个位置不填
        this.capacity = capacity;
        this.arr = new int[this.capacity];
        this.size = 0;
        // 初始在index=0的位置上，我们让尾部插入先移动index再赋值，让头部插入先赋值再移动index，就可以保证错开
        // 这样的话，读取head的值时，当读取headIndex+1处时，刚好满足边界 (即使tail没有插入过，也可以读取到head插入的数据
        this.headIndex = 0;
        this.tailIndex = 0;
    }

    /**
     * 添加一个元素，在双端队列头部
     * - 添加元素前，先要确保capacity足够
     *
     * @param value
     * @return 是否添加成功
     */
    public boolean insertFront(int value) {
        if (isFull()) {
            return false;
        }
        this.arr[this.headIndex] = value;
        this.headIndex = (this.headIndex - 1 + this.capacity) % this.capacity;
        this.size++;
        return true;
    }

    /**
     * 添加一个元素，在双端队列尾部
     * - 时间复杂度：O(1)
     *
     * @param value
     * @return
     */
    public boolean insertLast(int value) {
        if (isFull()) {
            return false;
        }
        this.tailIndex = (this.tailIndex + 1) % this.capacity;
        this.arr[this.tailIndex] = value;
        this.size++;
        return true;
    }

    /**
     * 删除一个元素，其位于双端队列头部
     * - 时间复杂度：O(1)
     *
     * @return
     */
    public boolean deleteFront() {
        if (isEmpty()) {
            return false;
        }
        this.headIndex = (this.headIndex + 1) % this.capacity;
        this.size--;
        return true;
    }

    /**
     * 删除一个元素，其位于双端队列尾部
     * - 时间复杂度：O(1)
     *
     * @return
     */
    public boolean deleteLast() {
        if (isEmpty()) {
            return false;
        }
        this.tailIndex = (this.tailIndex - 1 + this.capacity) % this.capacity;
        this.size--;
        return true;
    }

    /**
     * 获取头部元素
     * - 时间复杂度：O(1)
     *
     * @return 当双端队列为空时返回-1
     */
    public int getFront() {
        if (isEmpty()) {
            return -1;
        }
        return this.arr[(this.headIndex + 1) % this.capacity];
    }

    /**
     * 获取尾部元素
     * - 时间复杂度：O(1)
     *
     * @return 当双端队列为空时返回-1
     */
    public int getRear() {
        if (isEmpty()) {
            return -1;
        }
        return this.arr[this.tailIndex];
    }

    /**
     * 检查双端队列是否为空
     * - 时间复杂度：O(1)
     *
     * @return
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * 检查双端队列是否已满
     * - 时间复杂度：O(1)
     *
     * @return
     */
    public boolean isFull() {
        return this.size == this.capacity;
    }
}
