package com.algorithm.Tree.pre_mid_pos;

import com.algorithm.Tree.TreeNode;

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
}
