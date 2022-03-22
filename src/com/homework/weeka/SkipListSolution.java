package com.homework.weeka;

/**
 * 算法实现：跳表 - 设计跳表
 * - https://leetcode-cn.com/problems/design-skiplist/ (1206题)
 * <p>
 * 不使用任何库函数，设计一个 跳表 。
 * <p>
 * 跳表 是在 O(log(n)) 时间内完成增加、删除、搜索操作的数据结构。
 * 跳表相比于树堆与红黑树，其功能与性能相当，并且跳表的代码长度相较下更短，其设计思想与链表相似。
 * <p>
 * 例如，一个跳表包含 [30, 40, 50, 60, 70, 90] ，然后增加 80、45 到跳表中，以下图的方式操作：
 * Artyom Kalinin [CC BY-SA 3.0], via Wikimedia Commons
 * <p>
 * 跳表中有很多层，每一层是一个短的链表。
 * 在第一层的作用下，增加、删除和搜索操作的时间复杂度不超过 O(n)。跳表的每一个操作的平均时间复杂度是 O(log(n))，空间复杂度是 O(n)。
 * <p>
 * 了解更多 : https://en.wikipedia.org/wiki/Skip_list
 * <p>
 * 在本题中，你的设计应该要包含这些函数：
 * bool search(int target) : 返回target是否存在于跳表中。
 * void add(int num): 插入一个元素到跳表。
 * bool erase(int num): 在跳表中删除一个值，如果 num 不存在，直接返回false. 如果存在多个 num ，删除其中任意一个即可。
 * 注意，跳表中可能存在多个相同的值，你的代码需要处理这种情况。
 * <p>
 * - 输入
 * ["Skiplist", "add", "add", "add", "search", "add", "search", "erase", "erase", "search"]
 * [[], [1], [2], [3], [0], [4], [1], [0], [1], [1]]
 * 输出
 * [null, null, null, null, false, null, true, false, true, false]
 * <p>
 * 解释
 * Skiplist skiplist = new Skiplist();
 * skiplist.add(1);
 * skiplist.add(2);
 * skiplist.add(3);
 * skiplist.search(0);   // 返回 false
 * skiplist.add(4);
 * skiplist.search(1);   // 返回 true
 * skiplist.erase(0);    // 返回 false，0 不在跳表中
 * skiplist.erase(1);    // 返回 true
 * skiplist.search(1);   // 返回 false，1 已被擦除
 * <p>
 * 0 <= num, target <= 2 * 10^4
 * 调用search, add,  erase操作次数不大于 5 * 10^4
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-03-07 03:21:30
 */
public class SkipListSolution {
    public static void main(String[] args) {
        final SkipList skipList = new SkipList();
        System.out.println("skipList create instance...");
        skipList.add(1);
        System.out.println("skipList add : 1");
        skipList.add(2);
        System.out.println("skipList add : 2");
        skipList.add(3);
        System.out.println("skipList add : 3");
        System.out.println("skipList search (0) : " + skipList.search(0));
        skipList.add(4);
        System.out.println("skipList add : 4");
        System.out.println("skipList search (1) : " + skipList.search(1));
        System.out.println("skipList erase (0) : " + skipList.erase(0));
        System.out.println("skipList erase (1) : " + skipList.erase(1));
        System.out.println("skipList search (1) : " + skipList.search(1));
    }

    static class SkipList {

        private static final float SKIPLIST_P = 0.25f;
        /**
         * 足够满足20000的范围
         */
        private static final int MAX_LEVEL = 1 << 4;

        static class Node {
            /**
             * 节点的值
             */
            private int val;
            /**
             * 节点在不同层的下一个节点
             */
            private Node[] next;

            Node(final int val, final int layers) {
                this.val = val;
                this.next = new Node[layers];
            }

            @Override
            public String toString() {
                return String.format("{\"val\" : %d, \"next layers\" : %d}", this.val, this.next.length);
            }
        }

        private int levelCount;
        private Node head;

        public SkipList() {
            levelCount = 1;
            head = new Node(-1, MAX_LEVEL);
        }

        /**
         * O(logn)实现查询
         *
         * @param target
         * @return
         */
        public boolean search(final int target) {
            Node node = head;
            for (int i = levelCount - 1; i >= 0; i--) {
                node = findClosest(node, i, target);
                if (node.next[i] != null && node.next[i].val == target) {
                    return true;
                }
            }
            return false;
        }

        /**
         * 这是最关键的方法：
         * - 先随机出来一个层数，这就是要插入的新节点的层数
         * - 根据跳表实际的总层数从上往下分析，要插入一个节点newNode时，先找到节点在该层的位置：
         * - 因为是链表，所以需要O(logn)找到一个节点node，满足newNode的值刚好不大于node的下一个节点值，当然，如果node的下个节点为空，那么也是满足的。
         * - 确定插入节点newNode在该层的位置后，先判断下newNode的随机层数是否小于当前跳表的总层数，如果是，则用链表的插入方法将newNode插入即可。
         * - 如此循环，直到最底层插入newNode完毕。
         * - 循环完毕后，还需要判断下newNode随机出来的层数是否比跳表的实际层数还要大，如果是，直接将超过实际层数的跳表的头节点指向newNode即可，该跳表的实际层数也就变为newNode的随机层数了。
         *
         * @param num
         */
        public void add(final int num) {
            int level = dial();
            Node updateNode = head;
            Node newNode = new Node(num, level);
            // 计算出当前num 索引的实际层数，从该层开始添加索引
            for (int i = levelCount - 1; i >= 0; i--) {
                // 找到本层最近离num最近的list
                updateNode = findClosest(updateNode, i, num);
                if (i >= level) {
                    continue;
                }
                if (updateNode.next[i] == null) {
                    updateNode.next[i] = newNode;
                    continue;
                }
                Node temp = updateNode.next[i];
                updateNode.next[i] = newNode;
                newNode.next[i] = temp;
            }
            if (level > levelCount) {
                for (int i = levelCount; i < level; i++) {
                    head.next[i] = newNode;
                }
                levelCount = level;
            }
        }

        public boolean erase(final int num) {
            boolean flag = false;
            Node searchNode = head;
            for (int i = levelCount - 1; i >= 0; i--) {
                searchNode = findClosest(searchNode, i, num);
                if (searchNode.next[i] != null && searchNode.next[i].val == num) {
                    searchNode.next[i] = searchNode.next[i].next[i];
                    flag = true;
                    continue;
                }
            }
            return flag;
        }

        /**
         * 扔色子，获得random level
         *
         * @return
         */
        private int dial() {
            int level = 1;
            while (Math.random() < SKIPLIST_P && level < MAX_LEVEL) {
                level++;
            }
            return level;
        }

        /**
         * 找到level层value大于等于node的节点
         *
         * @param node
         * @param levelIndex
         * @param value
         * @return
         */
        private Node findClosest(final Node node, final int levelIndex, final int value) {
            Node p = node;
            while (p.next[levelIndex] != null && value > p.next[levelIndex].val) {
                p = p.next[levelIndex];
            }
            return p;
        }
    }
}
