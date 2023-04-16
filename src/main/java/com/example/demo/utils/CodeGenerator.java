package com.example.demo.utils;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * MyBatis-Plus代码生成器
 *
 * @author dongxu
 */
public class CodeGenerator {

    /**
     * 数据源配置
     */
    private static final DataSourceConfig.Builder DATA_SOURCE_CONFIG = new DataSourceConfig
            .Builder("jdbc:mysql://govee-test-cluster-v1.cluster-cviu5goyqplm.us-east-1.rds.amazonaws.com:3306/os?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&useSSL=false",
            "developer", "8f85676336ad8f88");

    private static final String pkgPath = System.getProperty("user.dir") + "/src/main/java";
    private static final String pkgXml = System.getProperty("user.dir") + "/src/main/resources/mapper/mall";
    private static final String pkgModel = System.getProperty("user.dir") + "/src/main/java/govee/app/mall/pojo/model";
    private static final String pkgService = System.getProperty("user.dir") + "/src/main/java/govee/app/mall/service";
    private static final String pkgServiceImpl = System.getProperty("user.dir") + "/src/main/java/govee/app/mall/service/impl";
    private static final String pkgController = System.getProperty("user.dir") + "/src/main/java/govee/app/mall/controller";


    public static void main(String[] args) {
        FastAutoGenerator.create(DATA_SOURCE_CONFIG)
                // 全局配置
                .globalConfig((scanner, builder) ->
                {
                    builder.author(scanner.apply("请输入作者名称？"))
                            .fileOverride()
                            .outputDir(pkgPath)//输出位置
                            .enableSwagger()//开启swagger
                            .dateType(DateType.SQL_PACK)
//                            .enableKotlin()//开启kotlin
                    ;
                })
                // 包配置
                .packageConfig((scanner, builder) -> {
                    builder.parent(scanner.apply("请输入包名？"))
                            .entity("pojo.model")
                            .pathInfo(new HashMap<OutputFile, String>() {{
                                put(OutputFile.xml, pkgXml);
                                put(OutputFile.entity, pkgModel);
                                put(OutputFile.service, pkgService);
                                put(OutputFile.serviceImpl, pkgServiceImpl);
                                put(OutputFile.controller, pkgController);
                            }});
                })
                // 策略配置
                .strategyConfig((scanner, builder) -> builder.addInclude(getTables(scanner.apply("请输入表名，多个英文逗号分隔？所有输入 all")))
                        .controllerBuilder().enableRestStyle().enableHyphenStyle()
                        .entityBuilder()
                        .enableLombok()
                        .build())
                /*
                    模板引擎配置，默认 Velocity 可选模板引擎 Beetl 或 Freemarker
                   .templateEngine(new BeetlTemplateEngine())
                   .templateEngine(new FreemarkerTemplateEngine())
                 */
                .execute();
    }

    // 处理 all 情况
    protected static List<String> getTables(String tables) {
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }
}
