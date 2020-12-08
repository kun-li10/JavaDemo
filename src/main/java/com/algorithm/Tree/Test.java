package com.algorithm.Tree;

import com.sun.xml.internal.fastinfoset.algorithm.BooleanEncodingAlgorithm;

import java.util.LinkedList;

/**
 * @author lk
 * @version 1.0
 * @date 2020/12/2 14:55
 */
public class Test {

  public LinkedList<TreeNode> pre(TreeNode root) {
    LinkedList<TreeNode> stack = new LinkedList<>();
    LinkedList<TreeNode> result = new LinkedList<>();
    stack.push(root);
    while (!stack.isEmpty()) {
      TreeNode node = stack.poll();
      result.add(node);
      if (node.right != null) {
        stack.push(node.right);
      }
      if (node.left != null) {
        stack.push(node.left);
      }
    }
    return result;
  }

  public LinkedList<TreeNode> mid(TreeNode root) {
    LinkedList<TreeNode> stack = new LinkedList<>();
    LinkedList<TreeNode> result = new LinkedList<>();
    TreeNode pNode = root;
    while (pNode != null || !stack.isEmpty()) {
      while (pNode != null) {
        stack.push(pNode);
        pNode = pNode.left;
      }
      TreeNode node = stack.pop();
      result.add(node);
      pNode = node.right;
    }
    return result;
  }

  public LinkedList<TreeNode> post(TreeNode root) {
    LinkedList<TreeNode> stack = new LinkedList<>();
    LinkedList<TreeNode> list = new LinkedList<>();
    stack.add(root);
    while (!stack.isEmpty()) {
      TreeNode node = stack.pollLast();
      list.addFirst(node);
      if (node.left != null) {
        stack.add(node.left);
      }
      if (node.right != null) {
        stack.add(node.right);
      }
    }
    return list;
  }

  public void depth(TreeNode root) {
    if (root == null) {
      return;
    }
    LinkedList<TreeNode> stack = new LinkedList<>();
    stack.push(root);
    while (!stack.isEmpty()) {
      TreeNode node = stack.pop();
      System.out.println(node.value);
      if (node.right != null) {
        stack.push(node.right);
      }
      if (node.left != null) {
        stack.push(node.left);
      }
    }
  }

  public void level(TreeNode root) {
    if (root == null) {
      return;
    }
    LinkedList<TreeNode> list = new LinkedList<>();
    list.offer(root);
    while (!list.isEmpty()) {
      TreeNode node = list.poll();
      System.out.println(node.value);
      if (node.left != null) {
        list.offer(node.left);
      }
      if (node.right != null) {
        list.offer(node.right);
      }
    }
  }


  /**
   * 判断是不是平衡二叉树
   */
  Boolean flag = true;

  public Boolean balanceSolution(TreeNode root) {
    solution(root);
    return flag;
  }

  private int solution(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int leftLen = solution(root.left);
    int rightLen = solution(root.right);
    if (Math.abs(leftLen = rightLen) > 1) {
      flag = false;
    }
    return leftLen > rightLen ? leftLen + 1 : rightLen + 1;
  }

}
