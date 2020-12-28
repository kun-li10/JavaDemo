package com.algorithm.Tree.pre_mid_pos;

import com.algorithm.Tree.TreeNode;
import com.google.common.collect.Lists;
import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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

  public LinkedList<String> postT(TreeNode root) {
    LinkedList<TreeNode> stack = new LinkedList<>();
    LinkedList<String> list = new LinkedList<>();
    stack.push(root);
    while (!stack.isEmpty()) {
      TreeNode node = stack.poll();
      list.addFirst(node.value);
      if (node.left != null) {
        stack.push(node.left);
      }
      if (node.right != null) {
        stack.push(node.right);
      }
    }
    return list;
  }
}
