package com.jd.AsyEny;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class EncryptUtil {

  public static String base64Encode(byte[] key) {
    return Base64.getEncoder().encodeToString(key);
  }

  private static byte[] base64Decode(String key) {
    return Base64.getDecoder().decode(key);
  }

  public static String aesEncript(String content, String key) {
    AES aes = SecureUtil.aes(base64Decode(key));
    return aes.encryptHex(content);
  }

  public static String aesDecript(String encryptHex, String key) {
    AES aes = SecureUtil.aes(base64Decode(key));
    return aes.decryptStr(encryptHex, CharsetUtil.CHARSET_UTF_8);
  }

  /**
   * 对map进行加签，添加 signature 属性 加签规则: 加签前添加：uuid,timestamp字段
   * 将map的value按照key的字典顺序排序，合成一个待签名字符串toSignStr toSignStr+signKey 将该字符串使用sha-1(md5())计算出签名 put
   *
   * @param map
   */
  public static void signatureMap(Map<String, String> map, String signKey) {
    if (map != null) {
      map.put("timestamp", System.currentTimeMillis() + "");
      map.put("uuid", UUID.randomUUID().toString());
      String signature = signMap(map, signKey);
      map.put("signature", signature);
    }
  }

  /**
   * 验证签名串
   *
   * @param map
   * @param signKey
   * @return
   */
  public static Boolean validSignaturedMap(Map<String, String> map, String signKey) {
    Boolean ressult = true;
    if (map != null) {
      String signaturedStr = signMap(map, signKey);
      String signature = map.get("signature");
      ressult = signature != null && signature.equals(signaturedStr);
    }
    return ressult;
  }

  private static String signMap(Map<String, String> map, String signKey) {
    String toSignStr = getToSignStr(map);
    return signStr(toSignStr, signKey);
  }

  /** 签名 */
  private static String signStr(String toSignStr, String signKey) {
    return SecureUtil.sha1(SecureUtil.md5(toSignStr + signKey));
  }

  private static String getToSignStr(Map<String, String> map) {
    Set<String> keySet = map.keySet();
    List<String> list = new ArrayList<>();
    list.addAll(keySet);
    // 字典排序
    Collections.sort(list);
    // value相加
    StringBuilder sbd = new StringBuilder();
    for (String key : list) {
      // 忽略signature 字段
      if ("signature".equals(key)) {
        continue;
      }
      String value = map.get(key);
      sbd.append(value);
    }
    return sbd.toString();
  }
}
