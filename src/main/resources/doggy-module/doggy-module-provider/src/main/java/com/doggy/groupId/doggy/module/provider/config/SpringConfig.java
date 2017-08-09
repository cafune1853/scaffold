package com.doggy.groupId.doggy.module.provider.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author doggy
 *         Created on 2016-07-08.
 */
@Configuration
@ComponentScan(basePackages = {"com.doggy.groupId.doggy.module.provider"})
@EnableAspectJAutoProxy
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
@ImportResource({"classpath:dubbo-provider.xml", "classpath:mybatis.xml"})
@Slf4j
public class SpringConfig {
    @Bean
    public PropertySourcesPlaceholderConfigurer propertyConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public DataSource druidDataSourcce(@Value("${db.url}") String dbUrl, @Value("${db.user.name}") String dbUserName, @Value("${db.pwd}") String dbPwd){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setTestWhileIdle(true);
        druidDataSource.setMinIdle(10);
        druidDataSource.setMaxActive(100);
        druidDataSource.setPoolPreparedStatements(true);
        druidDataSource.setUrl(dbUrl);
        druidDataSource.setUsername(dbUserName);
        druidDataSource.setPassword(dbPwd);
        return druidDataSource;
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource datasource){
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(datasource);
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("mybatis.xml"));
        sqlSessionFactoryBean.setMapperLocations(new Resource[]{new ClassPathResource("all-mapper.xml")});
        return sqlSessionFactoryBean;
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.doggy.groupId.doggy.module.provider.dao");
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        return mapperScannerConfigurer;
    }

    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource){
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }
}
