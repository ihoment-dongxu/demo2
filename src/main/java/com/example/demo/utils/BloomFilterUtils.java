package com.example.demo.utils;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.nio.charset.Charset;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 布隆过滤器
 *
 * @author dongxu
 * @create 2023-05-15 下午9:22
 */
public class BloomFilterUtils {
    public static void main(String[] args) {
        // 创建布隆过滤器，设置存储的数据类型，预期数据量，误判率 (必须大于0，小于1)
        int insertions = 10000000;
        double fpp = 0.0001;
        BloomFilter<String> bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charset.defaultCharset()), insertions, fpp);

        // 随机生成数据，并添加到布隆过滤器中（将预期数据量全部塞满）
        // 同时也创建一个List集合，将布隆过滤器中预期数据的十分之一存储到该List中
        List<String> lists1 = new ArrayList<>();
        for (int i = 0; i < insertions; i++) {
            String uid = UUID.randomUUID().toString();
            bloomFilter.put(uid);
            if (i < insertions / 10) {
                lists1.add(uid);
            }
        }

        // 再创建一个List集合，用来存储另外 五分之一 不存在布隆过滤器中的数据
        List<String> lists2 = new ArrayList<>();
        for (int i = 0; i < insertions / 5; i++) {
            String uid = UUID.randomUUID().toString();
            lists2.add(uid);
        }

        // 对已存在布隆过滤器中的lists_1中的数据进行判断，看是否在布隆过滤器中
        int result1 = 0;
        for (String s : lists1) {
            if (bloomFilter.mightContain(s)) result1++;
        }
        System.out.println("在 <已存在> 布隆过滤器中的" + lists1.size() + "条数据中，布隆过滤器认为存在的数量为：" + result1);

        // 对不存在布隆过滤器中的lists_2中的数据进行判断，看是否在布隆过滤器中
        int result2 = 0;
        for (String s : lists2) {
            if (bloomFilter.mightContain(s)) result2++;
        }
        System.out.println("在 <不存在> 布隆过滤器中的" + lists2.size() + "条数据中，布隆过滤器认为存在的数量为：" + result2);

        // 对数据进行整除，求出百分率
        NumberFormat percentFormat = NumberFormat.getPercentInstance();
        percentFormat.setMaximumFractionDigits(2);
        float percent = (float) result1 / lists1.size();
        float bingo = (float) result2 / lists2.size();
        System.out.println("命中率为：" + percentFormat.format(percent) + "，误判率为：" + percentFormat.format(bingo));
    }
}
