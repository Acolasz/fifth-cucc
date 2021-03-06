package hu.kukutyin.fifth.bean.config;

import org.mybatis.spring.annotation.MapperScan;

import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "hu.kukutyin.fifth.dao.mapper")
public class MyBatisConfig {
}
