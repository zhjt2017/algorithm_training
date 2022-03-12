package com.dailytraining.month202203;

import java.util.*;

/**
 * 算法训练(2022-03-12) N叉树的后续遍历
 * - https://leetcode-cn.com/problems/n-ary-tree-postorder-traversal/ (590题)
 * <p>
 * 给定一个n叉树的根节点root ，返回其节点值的后序遍历 。
 * n叉树在输入中按层序遍历进行序列化表示，每组子节点由空值null分隔（请参见示例）。
 * <p>
 * - 输入：root = [1,null,3,2,4,null,5,6]
 * 输出：[5,6,3,2,4,1]
 * <p>
 * - 输入：root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * 输出：[2,6,14,11,7,3,12,8,4,13,9,10,5,1]
 * <p>
 * 节点总数在范围 [0, 10^4] 内
 * 0 <= Node.val <= 10^4
 * n 叉树的高度小于或等于 1000
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-03-12 01:56:45
 */
public class TreePostOrderSolution {
    public static void main(String[] args) {
        final TreePostOrderSolution solution = new TreePostOrderSolution();

        Node root = new Node(1);
        root.children = Arrays.asList(new Node(3), new Node(2), new Node(4));
        root.children.get(0).children = Arrays.asList(new Node(5), new Node(6));
        System.out.println("Input tree : " + root);
        System.out.println("Output postorder (dfs) : " + solution.postorder(root));
        System.out.println("Output postorder (bfs) : " + solution.postorderIteration(root));
        System.out.println("Output postorder (bfs reverse) : " + solution.postorderIterationReverse(root));
        System.out.println();

        root = new Node(1);
        root.children = Arrays.asList(new Node(2), new Node(3), new Node(4), new Node(5));
        root.children.get(1).children = Arrays.asList(new Node(6), new Node(7));
        root.children.get(2).children = Arrays.asList(new Node(8));
        root.children.get(3).children = Arrays.asList(new Node(9), new Node(10));
        root.children.get(1).children.get(1).children = Arrays.asList(new Node(11));
        root.children.get(2).children.get(0).children = Arrays.asList(new Node(12));
        root.children.get(3).children.get(0).children = Arrays.asList(new Node(13));
        root.children.get(1).children.get(1).children.get(0).children = Arrays.asList(new Node(14));
        System.out.println("Input tree : " + root);
        System.out.println("Output postorder (dfs) : " + solution.postorder(root));
        System.out.println("Output postorder (bfs) : " + solution.postorderIteration(root));
        System.out.println("Output postorder (bfs reverse) : " + solution.postorderIterationReverse(root));
        System.out.println();
    }

    /**
     * 基本实现：dfs直接后序遍历
     * - 时间复杂度 O(n)
     * - 空间复杂度 最大O(n) (空间表现在root的最大深度)
     *
     * @param root
     * @return
     */
    List<Integer> postorder(final Node root) {
        ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        dfs(root);
        return ans;
    }

    private void dfs(final Node node) {
        if (node.children != null) {
            for (Node item : node.children) {
                dfs(item);
            }
        }
        ans.add(node.val);
    }

    private List<Integer> ans;

    /**
     * bfs直接后续遍历 (进出栈的逻辑略微复杂一些) (性能不如postorderIterationReverse)
     * - 时间复杂度 O(n)
     * - 空间复杂度 最大O(n) (空间表现在root的最大宽度)
     *
     * @param root
     * @return
     */
    List<Integer> postorderIteration(final Node root) {
        final List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }

        Node node;
        final HashSet<Node> visited = new HashSet<>();
        final Deque<Node> stack = new LinkedList<>();
        stack.offerLast(root);
        while (!stack.isEmpty()) {
            node = stack.peekLast();
            if (node.children == null || visited.contains(node)) {
                ans.add(node.val);
                stack.pollLast();
                continue;
            }
            for (int i = node.children.size() - 1; i >= 0; i--) {
                stack.offerLast(node.children.get(i));
            }
            visited.add(node);
        }
        return ans;
    }

    /**
     * bfs前序(子节点从右向左)遍历 (进出栈的逻辑简单一些, 最后再O(n)进行reverse得到最终结果)
     * - 时间复杂度 O(n)
     * - 空间复杂度 最大O(n) (空间表现在root的最大宽度)
     *
     * @param root
     * @return
     */
    List<Integer> postorderIterationReverse(final Node root) {
        final List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }

        Node node;
        final Deque<Node> stack = new LinkedList<>();
        stack.offerLast(root);
        while (!stack.isEmpty()) {
            node = stack.pollLast();
            ans.add(node.val);
            if (node.children == null) {
                continue;
            }
            for (Node item : node.children) {
                stack.offerLast(item);
            }
        }
        Collections.reverse(ans);
        return ans;
    }


    static class Node {
        private int val;
        private List<Node> children;

        private Node(final int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return String.format("{\"val\":%d,\"children\":%s}", val, children);
        }
    }
}
