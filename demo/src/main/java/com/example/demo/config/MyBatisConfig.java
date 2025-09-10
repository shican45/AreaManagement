package com.example.demo.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
public class MyBatisConfig {
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        // 设置 XML 映射文件位置
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/*.xml"));

        // 设置实体类包路径（可选）
        sessionFactory.setTypeAliasesPackage("com.example.demo.entity");
        return sessionFactory.getObject();
    }
}