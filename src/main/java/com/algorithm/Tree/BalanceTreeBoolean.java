package com.algorithm.Tree;

/**
 * 判断平衡二叉树 使用全局Boolean,判断平衡二叉树
 *
 * @author lk
 * @version 1.0
 * @date 2020/11/15 22:40
 */
public class BalanceTreeBoolean {

  private static Boolean isBoolean = true;

  public Boolean BolanceSolution(TreeNode root) {
    isBalance(root);
    return isBoolean;
  }

  private int isBalance(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int leftLen = isBalance(root.left);
    int rightLen = isBalance(root.right);
    if (Math.abs(leftLen - rightLen) > 1) {
      isBoolean = false;
    }
    return leftLen > rightLen ? leftLen + 1 : rightLen + 1;
  }
}
