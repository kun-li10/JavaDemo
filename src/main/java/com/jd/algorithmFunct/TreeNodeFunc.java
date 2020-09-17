package com.jd.algorithmFunct;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author lk
 * @version 1.0
 * @date 2020/9/4 12:21
 */
public class TreeNodeFunc {

  CopyOnWriteArrayList list= new CopyOnWriteArrayList();
  public List<String> binaryTreePaths(TreeNode root) {
    if(root == null){
      return list;
    }
    process(root,new ArrayList<>());
    return list;
  }

  private void process(TreeNode root,List<Integer> nodes){
    if(root == null){
      return;
    }
    nodes.add(root.val);
    if(root.left == null && root.right == null){
      StringBuffer sb = new StringBuffer();
      for (int i = 0; i < nodes.size()-1 ; i++) {
        sb.append(nodes.get(i)+"->");
      }
      sb.append(nodes.get(nodes.size()-1));
      list.add(sb.toString());
    }
    if(root.left != null){
      process(root.left,nodes);
      nodes.remove(nodes.size()-1);
    }
    if(root.right != null){
      process(root.right,nodes);
      nodes.remove(nodes.size()-1);
    }
  }

  /**
   * 二叉树
   */
  public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
}
