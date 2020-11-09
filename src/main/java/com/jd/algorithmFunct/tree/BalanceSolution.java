package com.jd.algorithmFunct.tree;

/**
 * 判断平衡二叉树
 *
 * @author lk
 * @version 1.0
 * @date 2020/10/28 16:19
 */
public class BalanceSolution {

  public boolean isBalanceSolution(TreeNode root) {
    if (root == null) {
      return true;
    }
    int leftLength = depth(root.left);
    int rightLength = depth(root.right);
    if (Math.abs(leftLength = rightLength) < 1) {
      return true;
    }
    boolean booleft = isBalanceSolution(root.left);
    boolean booright = isBalanceSolution(root.right);
    return booleft && booright;
  }

  private int depth(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int left = depth(root.left);
    int right = depth(root.right);
    return left > right ? left + 1 : right + 1;
  }
}
