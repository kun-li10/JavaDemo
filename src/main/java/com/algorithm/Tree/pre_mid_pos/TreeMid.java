package com.algorithm.Tree.pre_mid_pos;

import com.algorithm.Tree.TreeNode;

import java.util.LinkedList;
import java.util.Stack;

/**
 * 树的中序遍历
 * 左--> 根--> 右
 *
 * @author lk
 * @version 1.0
 * @date 2020/11/22 23:11
 */
public class TreeMid {


  /**
   * 递归遍历
   *
   * @param root
   */
  public void miRecursion(TreeNode root) {
    if (root != null) {
      miRecursion(root.left);
      System.out.println(root.value);
      miRecursion(root.right);
    }
  }

  /**
   * 非递归遍历
   *
   * @param root
   */
  public void midNotRecursion(TreeNode root) {
    Stack<TreeNode> stack = new Stack<>();
    TreeNode pNode = root;
    while (pNode != null || !stack.empty()) {
      if (pNode != null) {
        stack.push(pNode);
        pNode = root.left;
      } else {
        TreeNode node = stack.pop();
        System.out.println(node.value);
        pNode = node.right;
      }
    }
  }

  /**
   * 官方解题
   * @param root
   */
  public void mid2(TreeNode root) {
    LinkedList<TreeNode> stack = new LinkedList<>();
    TreeNode pNode = root;
    while (pNode != null || !stack.isEmpty()) {
      while (pNode != null) {
        stack.push(pNode);
        pNode = pNode.left;
      }
      TreeNode node = stack.pop();
      System.out.println(node.value);
      pNode = node.right;
    }
  }
}
