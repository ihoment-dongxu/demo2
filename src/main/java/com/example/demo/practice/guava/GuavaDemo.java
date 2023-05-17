package com.example.demo.practice.guava;

import com.google.common.collect.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author dongxu
 * @create 2023-05-16 下午2:20
 */
public class GuavaDemo {

    public static void main(String[] args) {
//        testTable();
//        testBiMap();
//        testMultiMap();
        testRangeMap();

    }

    /**
     * RangeMap：范围Map
     */
    private static void testRangeMap() {
        RangeMap<Integer, String> rangeMap = TreeRangeMap.create();
        rangeMap.put(Range.closed(10, 20), "10-20之间闭区间");
        rangeMap.put(Range.closedOpen(30, 40), "30-40之间左闭右开");
        rangeMap.put(Range.open(50, 70), "50-70之间开区间");
        rangeMap.put(Range.openClosed(80, 90), "80-90之间左开右闭");
        // 移除一段空间
        rangeMap.remove(Range.closed(70,85));

        String v1 = rangeMap.get(85);
        System.out.println(v1);


    }

    /**
     * Multimap：多值Map
     */
    private static void testMultiMap() {
        ArrayListMultimap<String, Integer> multiMap = ArrayListMultimap.create();
        multiMap.put("dongxu", 1);
        multiMap.put("dongxu", 1);
        multiMap.put("dongxu", 1);
        multiMap.put("zy", 2);
        System.out.println(multiMap);
        List<Integer> collection = multiMap.get("dongxu");
        System.out.println(collection);

        // 可以理解为新List与原Map是一种视图上的关联，所以操作get后的集合，会影响原Map的值
        collection.add(0, 10);
        System.out.println(multiMap);

        // 使用asMap方法，可以将Multimap转换为Map<K,Collection>的形式，同样这个Map也可以看做一个关联的视图，在这个Map上的操作会作用于原始的Multimap
        Map<String, Collection<Integer>> stringCollectionMap = multiMap.asMap();

    }

    /**
     * BiMap - 双向Map
     */
    private static void testBiMap() {
        HashBiMap<String, String> hashBiMap = HashBiMap.create();
        hashBiMap.put("dongxu", "972334151");
        hashBiMap.put("zhangyu", "1441489559");
        hashBiMap.put("xiaoming", "12345678");

        //使用key获取value
        System.out.println("使用key获取value:" + hashBiMap.get("dongxu"));

        /**
         * 反转后操作的影响：对新Map的所有操作都会作用到原Map，value不可重复
         */
        // 反转Map
        BiMap<String, String> inverse = hashBiMap.inverse();
        //使用key获取value
        System.out.println("使用key获取value:" + inverse.get("972334151"));

    }

    private static void testTable() {
        // Table
        Table<String, String, String> basedTable = HashBasedTable.create();
        basedTable.put("a", "b", "dongxu");
        String value = basedTable.get("a", "b");
        System.out.println(value);
        System.out.println("basedTable = " + basedTable);

        // 转为嵌套的Map
        Map<String, Map<String, String>> rowMap = basedTable.rowMap();
        Map<String, Map<String, String>> columnMap = basedTable.columnMap();

        // 行列转换
        Table<String, String, String> transposeTable = Tables.transpose(basedTable);
        System.out.println("行列转换:" + transposeTable.toString());
    }

}
