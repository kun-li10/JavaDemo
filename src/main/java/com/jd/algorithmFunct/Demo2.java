package com.jd.algorithmFunct;

import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author lk
 * @Date 2020/4/27 11:15
 * @Version 1.0
 */
public class Demo2 {
	private static final List<Integer> list = Lists.newArrayList();

	static {
		for (int i = 0, length = 100; i < length; i++) {
			list.add(new Random().nextInt(100));
		}
	}

	public static void main(String[] args) {
//        demo1();
//        demo2();
		demo3();
		System.out.println(list);
	}

	private static void demo1() {
		Collections.sort(list);
//        Collections.reverse(list);
	}

	private static void demo2() {
//        Collections.sort(list, new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return o1.compareTo(o2);
//            }
//        });
		Collections.sort(list, (o1, o2) -> {
			return o1.compareTo(o2);
		});
	}

	private static void demo3() {
//        list.stream().sorted(new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return o1.compareTo(o2);
//            }
//        });
//        list.sort((t1, t2) -> t1.compareTo(t2));
		list.stream().sorted(Comparator.comparingInt(Integer::intValue));
//        list.stream().sorted(Integer::compareTo);
//        list.parallelStream().reduce(Integer::sum).get();
//        list.parallelStream().reduce(0,Integer::sum);
	}
}
