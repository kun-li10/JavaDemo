package com.jd.Test;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import java.util.concurrent.ConcurrentMap;

/**
 * @author lk
 * @version 1.0
 * @date 2020/8/20 20:55
 */
public class ImmutableMapDemo {
	public static void main(String[] args) {
		ImmutableMap<String, String> build =
				new ImmutableMap.Builder<String, String>().put("1", "1").put("2", "2").build();
		System.out.println(build);

		ConcurrentMap<Object, Object> newConcurrentMap = Maps.newConcurrentMap();
		newConcurrentMap.put("3","3");
		newConcurrentMap.put("4","4");
		System.out.println(newConcurrentMap);
	}
}
