package com.algorithm.Tree.pre_mid_pos;

import com.algorithm.Tree.TreeNode;

import java.util.LinkedList;

/**
 * 树的后续遍历
 * 左-->右 -->根
 *
 * @author lk
 * @version 1.0
 * @date 2020/11/22 23:23
 */
public class TreePost {


  /**
   * 后续递归遍历
   *
   * @param root
   */
  public void postRecursion(TreeNode root) {
    if (root != null) {
      postRecursion(root.left);
      postRecursion(root.right);
      System.out.println(root.value);
    }
  }

  /**
   * 非递归后续遍历
   *
   * @param root
   */
  public void posNotRecursion(TreeNode root) {
    LinkedList<String> res = new LinkedList<>();
    LinkedList<TreeNode> stack = new LinkedList<>();
    TreeNode pNode = root;
    stack.push(pNode);
    while (!stack.isEmpty()) {
      pNode = stack.pollLast();
      res.addFirst(pNode.value);
      if (pNode.left != null) {
        stack.add(pNode.left);
      }
      if (pNode.right != null) {
        stack.add(pNode.right);
      }
    }
  }
}
