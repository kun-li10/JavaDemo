package com.jd.algorithmFunct.tree;

/**
 * 判断平衡二叉树
 *
 * @author lk
 * @version 1.0
 * @date 2020/11/11 10:49
 */
public class ReteyBalanceTree {

  private boolean isBanlance = true;

  public boolean isBalance(TreeNode root) {
    getDepth(root);
    return isBanlance;
  }

  public int getDepth(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int leftLen = getDepth(root.left);
    int rightLen = getDepth(root.right);
    if (Math.abs(leftLen - rightLen) > 1) {
      isBanlance = false;
    }
    return leftLen > rightLen ? leftLen + 1 : rightLen;
  }
}
