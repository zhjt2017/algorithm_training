package com.teachingpractice.week3.treetraversal;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 算法实现；树的遍历-N叉树的前序遍历
 * <p>
 * - 给定一个 N 叉树，返回其节点值的 前序遍历 。
 * - N 叉树 在输入中按层序遍历进行序列化表示，每组子节点由空值 null 分隔（请参见示例）
 * <p>
 * - 输入：root = [1,null,3,2,4,null,5,6]
 * 输出：[1,3,5,6,2,4]
 * <p>
 * - 输入：root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * 输出：[1,2,3,6,7,11,14,4,8,12,5,9,13,10]
 * <p>
 * - N 叉树的高度小于或等于 1000，节点总数在范围 [0, 10^4] 内
 * <p>
 * 基本实现方式：(递归，时间复杂度O(n) 空间复杂度最多O(n))
 * - 每层自身逻辑：先读取自身节点，再依次读取子节点，base case为该节点没有任何子节点
 * <p>
 * 进阶实现方式：迭代 时间复杂度O(n) 空间复杂度最多O(n)
 * <p>
 * 关于执行效能，递归更优
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-01-20 12:10:12
 */
public class TreePreorderTraversalSolution {
    public static void main(String[] args) {
        Node root = new Node(1);
        root.buildChildren(new Node(3), new Node(2), new Node(4));
        root.children.get(0).buildChildren(new Node(5), new Node(6));
        System.out.println("Input tree : " + root);
        System.out.println("Preorder result : " + preorder(root));
        System.out.println("Preorder result : " + preorderIteration(root));

        root = new Node(1);
        root.buildChildren(new Node(2), new Node(3), new Node(4), new Node(5));
        root.children.get(1).buildChildren(new Node(6), new Node(7));
        root.children.get(2).buildChildren(new Node(8));
        root.children.get(3).buildChildren(new Node(9), new Node(10));
        root.children.get(1).children.get(1).buildChildren(new Node(11));
        root.children.get(2).children.get(0).buildChildren(new Node(12));
        root.children.get(3).children.get(0).buildChildren(new Node(13));
        root.children.get(1).children.get(1).children.get(0).buildChildren(new Node(14));
        System.out.println("Input tree : " + root);
        System.out.println("Preorder result : " + preorder(root));
        System.out.println("Preorder result : " + preorderIteration(root));
    }

    private static List<Integer> preorder(final Node root) {
        final TreePreorderTraversalSolution solution = new TreePreorderTraversalSolution();
        solution.result = new LinkedList<>();
        if (root != null) {
            solution.recur(root);
        }
        return solution.result;
    }

    private static List<Integer> preorderIteration(final Node root) {
        if (root == null) {
            return new LinkedList<>();
        }

        final List<Integer> result = new LinkedList<>();
        final Deque<Node> stack = new LinkedList<>();
        stack.push(root);

        Node node;
        while (!stack.isEmpty()) {
            node = stack.pop();
            result.add(node.val);
            for (int i = node.children.size() - 1; i >= 0; i--) {
                stack.push(node.children.get(i));
            }
        }
        return result;
    }

    private void recur(final Node root) {
        // 读取自身节点
        this.result.add(root.val);

        // base case 没有子节点
        if (root.children == null || root.children.isEmpty()) {
            return;
        }

        // 依次(从左往右)读取子节点
        for (final Node node : root.children) {
            this.recur(node);
        }
    }

    static class Node {
        private int val;
        private List<Node> children;

        Node(final int val) {
            this.val = val;
            this.children = new ArrayList<>();
        }

        void buildChildren(final Node... children) {
            this.children = new ArrayList<>(children.length);
            for (final Node node : children) {
                this.children.add(node);
            }
        }

        @Override
        public String toString() {
            return String.format("\"val\":%d, \"children\":%s", this.val, this.children);
        }
    }

    private List<Integer> result;
}
