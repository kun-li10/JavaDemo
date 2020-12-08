package com.algorithm.other;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author lk
 * @version 1.0
 * @date 2020/11/26 12:31
 */
@Data
@ToString
public class TreeNode {
  private String nodeName;
  private String nodeCode;
  private List<TreeNode> children;
}
