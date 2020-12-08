package com.algorithm.Tree.TreeRing;

import com.google.common.collect.Lists;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author lk
 * @version 1.0
 * @date 2020/12/8 9:55
 */
public class RingTree {

  private static boolean isCircle(String id, List<ResRelation> resRelationList) {
    boolean isCircle = false;
    /* 队列*/
    LinkedList<String> queue = Lists.newLinkedList(Lists.newArrayList(id));
    /* 标记集合*/
    Set<ResRelation> color=new HashSet<>();
    while (queue.size() > 0) {
      String parentId = queue.poll();
      for (ResRelation i : resRelationList) {
        /* 找到节点的对应的关系，也就是边*/
        if (parentId.equals(i.getStart_id())) {
          /* 查看边是否被访问过*/
          if(!color.contains(i)){
            color.add(i);
            if(!queue.contains(i.getEnd_id())){
              /*加入队列 */
              queue.add(i.getEnd_id());
            }
          }else{
            /* 如果重复访问，则有环*/
            isCircle=true;
            queue.clear();
            break;
          }
        }
      }
    }
    return isCircle;
  }
}
