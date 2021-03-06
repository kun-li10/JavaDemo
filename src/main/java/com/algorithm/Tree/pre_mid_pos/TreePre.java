package com.algorithm.Tree.pre_mid_pos;

import com.algorithm.Tree.TreeNode;

import java.util.LinkedList;
import java.util.Stack;

/**
 * 树的前序遍历
 * 根--> 左 -->右
 *
 * @author lk
 * @version 1.0
 * @date 2020/11/22 22:58
 */
public class TreePre {


  /**
   * 前序递归遍历
   *
   * @param root
   */
  public void preRecursion(TreeNode root) {
    if (root != null) {
      System.out.println(root.value);
      preRecursion(root.left);
      preRecursion(root.right);
    }
  }


  /**
   * 非递归遍历，因为左子树访问完后需要访问右子树,所以需要栈这种结构
   *
   * @param root
   */
  public void preNotRecursion(TreeNode root) {
    Stack<TreeNode> stack = new Stack<>();
    TreeNode pNode = root;
    while (pNode != null || !stack.empty()) {
      if (pNode != null) {
        //打印当前节点的值
        System.out.println(pNode.value);
        //左子树压栈
        stack.push(root.left);
        pNode = root.left;
      } else {
        TreeNode node = stack.pop();
        //开始遍历右子树
        pNode = node.right;
      }
    }
  }

  /**
   * 更容易理解
   * 1.先把右节点压入栈低，左节点紧跟压入
   * 2.每次获取栈顶的元素,肯定是各个节点左树先处理
   * @param root
   */
  public void prNotRecursion2(TreeNode root) {
    LinkedList<TreeNode> stack = new LinkedList<>();
    stack.add(root);
    while (!stack.isEmpty()) {
      //获取尾部
      TreeNode node = stack.pollLast();
      System.out.println(node.value);
      if (node.right != null) {
        stack.add(node.right);
      }
      if (node.left != null) {
        stack.add(node.left);
      }
    }
  }
}
