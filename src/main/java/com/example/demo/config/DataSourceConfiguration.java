package com.example.demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@MapperScan(basePackages = "com.example.demo.mapper", sqlSessionTemplateRef = "mallSqlSessionTemplate")
public class DataSourceConfiguration {

    @Bean(name="mallDataSource")
    @ConfigurationProperties(prefix = "spring.shardingsphere.datasource.mall-master")
    public DataSource mallMasterDataSource() {
        DruidDataSource mallMasterDataSource  = new DruidDataSource();
        return mallMasterDataSource ;
    }

    @Bean
    public Interceptor pageHelper () {
        Interceptor pageHelper = new PageInterceptor();
        Properties properties = new Properties();
        properties.setProperty("reasonable", "false");
        properties.setProperty("helperDialect", "mysql");
        pageHelper.setProperties(properties);
        return pageHelper;
    }


    @Primary
    @Bean
    public SqlSessionFactory mallSqlSessionFactory(
            @Qualifier("mallDataSource") DataSource mallShardingDataSource, Interceptor pageHelper) {
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();


        bean.setDataSource(mallShardingDataSource);
        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Interceptor[] plugins =  new Interceptor[]{pageHelper};
        bean.setPlugins(plugins);
        bean.setGlobalConfig(new GlobalConfig().setMetaObjectHandler(new MyMetaObjectHandler()));
        try {
            bean.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Primary
    @Bean
    public SqlSessionTemplate mallSqlSessionTemplate(@Qualifier("mallSqlSessionFactory") SqlSessionFactory mallSqlSessionFactory) {
        return new SqlSessionTemplate(mallSqlSessionFactory);
    }

    @Primary
    @Bean
    public DataSourceTransactionManager mallTransactionManager(@Qualifier("mallDataSource") DataSource mallShardingDataSource) {
        return new DataSourceTransactionManager(mallShardingDataSource);
    }

}