package com.algorithm.Tree.pre_mid_pos;

import com.algorithm.Tree.TreeNode;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Stack;

/**
 * @author lk
 * @version 1.0
 * @date 2020/11/25 12:09
 */
public class TreeTest {

  /**
   * 前序
   * @param root
   * @return
   */
  static LinkedList<String> preRecusion(TreeNode root) {
    LinkedList<String> list = new LinkedList<>();
    LinkedList<TreeNode> stack = new LinkedList<>();
    if (Objects.nonNull(root)) {
      return list;
    }
    stack.push(root);
    while (!list.isEmpty()) {
      TreeNode node = stack.poll();
      list.add(node.value);
      if (node.right != null) {
        stack.push(node.right);
      }
      if (node.left != null) {
        stack.push(node.left);
      }
    }
    return list;
  }

  /**
   * 中序
   * @param root
   * @return
   */
  static LinkedList<String> midNotRecusion(TreeNode root) {
    LinkedList<String> list = new LinkedList<>();
    Stack<TreeNode> stack = new Stack<>();
    if (Objects.isNull(root)) {
      return list;
    }
    TreeNode pNode = root;
    while (pNode != null || !stack.empty()) {
      while (pNode.left != null) {
        stack.push(pNode);
        pNode = pNode.left;
      }
      TreeNode node = stack.pop();
      list.add(node.value);
      pNode = pNode.right;
    }
    return list;
  }

  /**
   * 后徐
   * @param root
   * @return
   */
  static LinkedList<String> posRecusion(TreeNode root) {
    Stack<TreeNode> stack = new Stack<>();
    LinkedList<String> list = new LinkedList<>();
    if (Objects.isNull(root)) {
      return list;
    }
    stack.push(root);
    while (!stack.empty()) {
      TreeNode node = stack.pop();
      list.addFirst(node.value);
      if (node.left != null) {
        stack.push(root.left);
      }
      if (node.right != null) {
        stack.push(root.right);
      }
    }
    return list;
  }

  /**
   * 水皮遍历
   * @param root
   * @return
   */
  static LinkedList<String> levelRecusion(TreeNode root){
    LinkedList<String> list = new LinkedList<>();
    LinkedList<TreeNode> nodes = new LinkedList<>();
    if (Objects.isNull(root)){
      return list;
    }
    nodes.add(root);
    while (!nodes.isEmpty()){
      TreeNode node = nodes.poll();
      list.add(node.value);
      if (node.left!=null){
        nodes.push(node.left);
      }
      if (node.right!=null){
        nodes.push(node.right);
      }
    }
    return list;
  }
}
