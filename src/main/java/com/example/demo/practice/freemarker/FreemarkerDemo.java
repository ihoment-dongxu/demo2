package com.example.demo.practice.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.SneakyThrows;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Freemarker demo
 *
 * @author dongxu
 * @create 2023-05-29 下午5:52
 */
public class FreemarkerDemo {

    @SneakyThrows
    public static void main(String[] args) {
        // 创建配置类
        Configuration configuration = new Configuration(Configuration.getVersion());
        // 设置模版所在目录
        configuration.setDirectoryForTemplateLoading(new File("/Users/dongxu/Desktop/work/code/demo2/src/main/resources/templates/"));
        // 设置字符集
        configuration.setDefaultEncoding("UTF-8");
        // 加载模版
        Template template = configuration.getTemplate("temp1.ftl");
        // 数据准备
        Map<String, Object> map = new HashMap<>();
        List<Map<String, Object>> productList = new ArrayList<>();
        Map<String, Object> productMap1 = new HashMap<>();
        productMap1.put("name", "灯带");
        productMap1.put("count", "1x");
        productMap1.put("unitPrice", 34.99);
        productMap1.put("amount", 34.99);

        Map<String, Object> productMap2 = new HashMap<>();
        productMap2.put("name", "温度计");
        productMap2.put("count", "2x");
        productMap2.put("unitPrice", 10.00);
        productMap2.put("amount", 20.00);
        productList.add(productMap1);
        productList.add(productMap2);

        map.put("invoiceItems", productList);
        map.put("ticket_header", "深圳市智岩科技有限公司");
        map.put("ticket_footer", "深圳市智岩科技有限公司");

        // 创建Writer对象，输出文件
        FileWriter fileWriter = new FileWriter(new File("/Users/dongxu/Desktop/发票.html"));

        // 填充模版
        template.process(map, fileWriter);

        // 关闭流
        fileWriter.close();
    }

}
