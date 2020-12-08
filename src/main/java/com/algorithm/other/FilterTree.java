package com.algorithm.other;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.util.ArrayList;
import java.util.List;

/**
 * 一个对一个树进行查询过滤
 *
 * @author lk
 * @version 1.0
 * @date 2020/11/19 18:04
 */
public class FilterTree {


  public static void main(String[] args) {
    ThreadLocal<FilterTree> local = new ThreadLocal<>();

  }

  public static List<TreeNode> fileNode2(TreeNode treeNode, List<String> targetNode) {
    List<TreeNode> nodes = treeNode.getChildren();
    List<TreeNode> newNodes = new ArrayList<>();
    ArrayList<TreeNode> removeNodes = new ArrayList<>();
    for (TreeNode node : nodes) {
      if (targetNode.contains(node)) {
        newNodes.add(node);
      }
      if (node.getChildren() != null && node.getChildren().size() > 0) {
        List<TreeNode> treeNodes = filterNode(node, targetNode);
        if (treeNodes.size() > 0) {
          node.setChildren(treeNodes);
        } else {
          node.setChildren(null);
          removeNodes.add(node);
        }
      }
    }
    nodes.removeAll(removeNodes);
    return newNodes;
  }

  public static List<TreeNode> filterNode(TreeNode treeNode, List<String> targetNode) {
    List<TreeNode> nodes = treeNode.getChildren();
    List<TreeNode> newNodes = Lists.newArrayList();
    List<TreeNode> tagNodes = Lists.newArrayList();

    for (TreeNode node : nodes) {
      if (targetNode.contains(node.getNodeName())) {
        newNodes.add(node);
      }
      if (node.getChildren() != null && node.getChildren().size() > 0) {
        List<TreeNode> retNodes = filterNode(node, targetNode);
        if (retNodes.size() > 0) {
          node.setChildren(retNodes);
        } else {
          // 没有子节点情况
          node.setChildren(null);
          // 标记,循环结束后删除
          tagNodes.add(node);
        }
      }
    }
    nodes.removeAll(tagNodes);
    return newNodes;
  }

}
