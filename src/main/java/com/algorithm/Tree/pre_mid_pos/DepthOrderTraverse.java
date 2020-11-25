package com.algorithm.Tree.pre_mid_pos;

import com.algorithm.Tree.TreeNode;

import java.util.LinkedList;

/**
 * 深度优先遍历
 *
 * @author lk
 * @version 1.0
 * @date 2020/11/23 21:12
 */
public class DepthOrderTraverse {

  public void depthOrderTraverse(TreeNode root) {
    if (root == null) {
      return;
    }
    LinkedList<TreeNode> stack = new LinkedList<>();
    stack.push(root);
    while (!stack.isEmpty()) {
      TreeNode node = stack.pop();
      System.out.print(node.value + "  ");
      if (node.right != null) {
        stack.push(node.right);
      }
      if (node.left != null) {
        stack.push(node.left);
      }
    }
  }

}
