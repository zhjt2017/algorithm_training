package com.teachingpractice.week3.treetraversal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 算法实现：树的遍历-N叉树的层序遍历
 * <p>
 * - 输入：root = [1,null,3,2,4,null,5,6]
 * 输出：[[1],[3,2,4],[5,6]]
 * <p>
 * - 输入：root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * 输出：[[1],[2,3,4,5],[6,7,8,9,10],[11,12,13],[14]]
 * <p>
 * 树的高度不会超过 1000
 * 树的节点总数在 [0, 10^4] 之间
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-01-20 05:07:13
 */
public class TreeLevelOrderTraversalSolution {
    public static void main(String[] args) {
        Node root = new Node(1);
        root.buildChildren(new Node(3), new Node(2), new Node(4));
        root.children.get(0).buildChildren(new Node(5), new Node(6));
        System.out.println("Input tree : " + root);
        System.out.println("Level order result : " + levelOrder(root));

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
        System.out.println("Level order result : " + levelOrder(root));
    }

    private static List<List<Integer>> levelOrder(final Node root) {
        final List<List<Integer>> result = new LinkedList<>();
        return result;
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
}
