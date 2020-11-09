package com.jd.algorithmFunct.tree;

/**
 * 后续遍历方法 判断二叉树是否是平衡二叉树
 *
 * @author lk
 * @version 1.0
 * @date 2020/10/30 15:22
 */
public class BalanceTree {

  // 全局的判断
  private boolean balance = true;

  public boolean isBalance(TreeNode root) {
    depth(root);
    return balance;
  }

  public int depth(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int left = depth(root.left);
    int right = depth(root.right);
    if (Math.abs(left - right) > 1) {
      balance = false;
    }
    return (right > left) ? right + 1 : left + 1;
  }
}
