package com.will.redpacket.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

@Configuration
public class DruidConfig {

    @Bean
    public DruidDataSource dataSourceConfig() throws SQLException {
        DruidDataSource ds = new DruidDataSource();
        /*
         * 基本属性
         */
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://127.0.0.1:3306/seckill?characterEncoding=utf-8&useSSL=false&useUnicode=true&serverTimezone=UTC");
        ds.setUsername("root");
        ds.setPassword("");
        /*
         * 配置初始化大小、最小、最
         */
        ds.setInitialSize(1);
        ds.setMinIdle(1);
        ds.setMaxActive(10);
        /*
         * 配置获取连接等待超时的时间
         */
        ds.setMaxWait(60000);
        /*
         * 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
         */
        ds.setTimeBetweenEvictionRunsMillis(60000);
        /*
         * 配置一个连接在池中最小生存的时间，单位是毫秒
         */
        ds.setMinEvictableIdleTimeMillis(300000);

        ds.setValidationQuery("SELECT 'X'");
        ds.setTestWhileIdle(true);
        ds.setTestOnBorrow(false);
        ds.setTestOnReturn(false);

        /*
         * 打开PSCache，并且指定每个连接上PSCache的大小
         */
        ds.setPoolPreparedStatements(false);
        ds.setMaxPoolPreparedStatementPerConnectionSize(20);
        /*
         * 配置监控统计拦截的filters
         */
        ds.setFilters("stat,wall,log4j");

        return ds;
    }


}
