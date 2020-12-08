package com.algorithm.Tree.pre_mid_pos;

import com.algorithm.Tree.TreeNode;

import java.util.LinkedList;

/**
 * 树层次遍历,肯定要用到队列
 * 也就是水平遍历
 *
 * @author lk
 * @version 1.0
 * @date 2020/11/22 23:32
 */
public class levelTraverse {

  /**
   * 树的层次遍历
   *
   * @param root
   */
  public void levelTraverseTree(TreeNode root) {
    LinkedList<TreeNode> linkedList = new LinkedList<>();
    if (root == null) {
      return;
    }
    //压入队列
    linkedList.offer(root);
    while (!linkedList.isEmpty()) {
      TreeNode pop = linkedList.poll();
      System.out.println(pop.value);
      if (pop.left != null) {
        linkedList.offer(pop.left);
      }
      if (pop.right != null) {
        linkedList.offer(pop.right);
      }
    }
  }

  public static void main(String[] args) {
    LinkedList<Integer> list = new LinkedList<Integer>();
    for (int i = 0; i < 10; i++) {
      list.offer(Integer.valueOf(i));
    }
//    System.out.println("POP:" + list.pop());
    System.out.println("PULL:" + list.poll());
    System.out.println(list);
  }

}
