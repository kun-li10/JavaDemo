package com.jd.stream;

import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * java7 java8 sort排序区别
 * 前提:type = 1,value降序,获取id集合
 *
 * @Author lk
 * @Date 2020/2/28 21:14
 * @Version 1.0
 */
public class SortCompare {

    public static void main(String[] args) {
        List<Transaction> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new Transaction(i, i + "", i + "", i));
        }
        list.add(new Transaction(13, "1", "aaa", 10));
        list.add(new Transaction(11, "1", "bbb", 20));
        list.add(new Transaction(10, "1", "ccc", 30));
        //java7
        List<Transaction> transactions = new ArrayList<>();
        list.forEach(transaction -> {    //过滤条件
            if (transaction.getType().equals(Transaction.GROCERY))
                transactions.add(transaction);
        });
        Collections.sort(transactions, new Comparator<Transaction>() {
            @Override                      // value 值降序
            public int compare(Transaction o1, Transaction o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        List<Integer> transactionsIDS = new ArrayList<>(); //id集合
        for (Transaction transaction : transactions) {
            transactionsIDS.add(transaction.getId());
        }
        System.out.println(transactionsIDS.toString());

        System.out.println("--------------分割线-------------------");

        //java8 stream
        List<Integer> idList = list.parallelStream()
                .filter(transaction -> transaction.getValue().equals(Transaction.GROCERY))
                .sorted(Comparator.comparing(Transaction::getValue).reversed())
                .map(Transaction::getId).collect(Collectors.toList());
        System.out.println(idList.toString());
        System.out.println("-----------------分割线2-----------------------");

        Comparator<Transaction> comparator = Comparator.comparingInt(Transaction::getId);
        Comparator<Transaction> comparator1 = Comparator.comparing(Transaction::getName);
        list.sort(comparator.reversed().thenComparing(comparator1));
        System.out.println(list);
        System.out.println("----------------分割线3------------------------");

        list.sort(Comparator.comparing(Transaction::getId).reversed());  //根据id倒叙
        list.sort((t1, t2) -> t2.getId().compareTo(t1.getId()));   //决定于compare前后的变量
        System.out.println(list);
    }

    static class Transaction {
        Integer id;
        static String GROCERY = "1";
        String type;
        String name;
        Integer value;

        public Transaction(Integer id, String type, String name, Integer value) {
            this.id = id;
            this.type = type;
            this.name = name;
            this.value = value;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Transaction{" +
                    "id=" + id +
                    ", type='" + type + '\'' +
                    ", name='" + name + '\'' +
                    ", value=" + value +
                    '}';
        }
    }
}
