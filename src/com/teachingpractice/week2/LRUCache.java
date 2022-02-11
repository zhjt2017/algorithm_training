package com.teachingpractice.week2;

import java.util.HashMap;
import java.util.Map;

/**
 * LRU Cache (这里不考虑并发问题)
 * - 请你设计并实现一个满足  LRU (最近最少使用) 缓存 约束的数据结构。
 * - 实现 LRUCache 类：
 * - LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存
 * - int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * - void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，则向缓存中插入该组 key-value 。如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。
 * - 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
 * <p>
 * - 输入
 * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 * 输出
 * [null, null, null, 1, null, -1, null, -1, 3, 4]
 * <p>
 * 解释
 * LRUCache lRUCache = new LRUCache(2);
 * lRUCache.put(1, 1); // 缓存是 {1=1}
 * lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
 * lRUCache.get(1);    // 返回 1
 * lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
 * lRUCache.get(2);    // 返回 -1 (未找到)
 * lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
 * lRUCache.get(1);    // 返回 -1 (未找到)
 * lRUCache.get(3);    // 返回 3
 * lRUCache.get(4);    // 返回 4
 * <p>
 * 1 <= capacity <= 3000
 * 0 <= key <= 10000
 * 0 <= value <= 10^5
 * 最多调用 2 * 10^5 次 get 和 put
 * <p>
 * - 根据capacity与value的范围，可以发现只涉及int数值范围
 * - 思考: 任何系统的设计，都要考虑边界问题：如数据边界、服务边界
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-02-11 11:00:49
 */
public class LRUCache {

    /**
     * 采用双向链表的数据结构，来支持Least Recently Used特性
     * - 1、根据访问的先后来维护 (维护到tail上)
     * - 2、超出容量时删除 (head删除)
     */
    static class Node {
        private int key;
        private int value;
        private Node pre;
        private Node next;
    }

    /**
     * 采用HashMap，来支持get与put实现 O(1) 的时间复杂度 (主要是找到key / Node需要O(1))
     */
    private Map<Integer, Node> hash;
    /**
     * 定义容量为成员变量，在操作时随时维护，以支持LRU Cache在运作时不会超出该固定容量
     */
    private int capacity;

    /**
     * 双向链表中的head节点 (在双向链表为空时作为保护节点)
     */
    private Node head;
    /**
     * 双向链表中的tail节点 (在双向链表为空时作为保护节点)
     */
    private Node tail;

    /**
     * Last Recently Used 开一个缓存区， 只有capacity那么大
     *
     * @param capacity
     */
    public LRUCache(final int capacity) {
        this.capacity = capacity;
        hash = new HashMap<>();
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.pre = head;
    }

    public int get(final int key) {
        Node node = hash.get(key);
        if (node != null) {
            // (由于最新访问)节点移到尾部
            nodeRemove(node);
            nodeAdd(node);
            return node.value;
        }
        return -1;
    }

    public void put(final int key, final int value) {
        Node node = hash.get(key);
        if (node != null) {
            // 更新：(由于最新访问)节点移到尾部，hash中的键值对更新值引用对象的value属性
            nodeRemove(node);
            nodeAdd(node);
            node.value = value;
            return;
        }
        // 插入：队尾放入新节点 (判断如果容量超了，从队头删除一个，并删除该node的hash映射关系)，hash放入新的键值对
        if (hash.size() == capacity) {
            hash.remove(head.next.key);
            nodeRemove(head.next);
        }
        node = new Node();
        node.key = key;
        node.value = value;
        nodeAdd(node);
        hash.put(key, node);
    }

    /**
     * 队列删除一个节点
     *
     * @param node
     */
    private void nodeRemove(final Node node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    /**
     * 队列新增一个节点 (一律在队尾新增) (capacity不在这里进行判定，这里只考虑单纯的插入操作)
     *
     * @param node
     */
    private void nodeAdd(final Node node) {
        node.pre = tail.pre;
        node.next = tail;
        node.pre.next = node;
        tail.pre = node;
    }

    public static void main(String[] args) {
        int capacity = 2;
        final LRUCache lruCache = new LRUCache(capacity);
        System.out.println("Build LRU Cache, capacity : " + capacity);
        lruCache.put(1, 1);
        System.out.println("put 1=1");
        lruCache.put(2, 2);
        System.out.println("put 2=2");
        lruCache.get(1);
        System.out.println("get result (key=1) : " + lruCache.get(1));
        lruCache.put(3, 3);
        System.out.println("put 3=3");
        lruCache.get(2);
        System.out.println("get result (key=2) : " + lruCache.get(2));
        lruCache.put(4, 4);
        System.out.println("put 4=4");
        lruCache.get(1);
        System.out.println("get result (key=1) : " + lruCache.get(1));
//        lruCache.get(2);
//        System.out.println("get result (key=2) : " + lruCache.get(2));
        lruCache.get(3);
        System.out.println("get result (key=3) : " + lruCache.get(3));
        lruCache.get(4);
        System.out.println("get result (key=4) : " + lruCache.get(4));
    }

}
