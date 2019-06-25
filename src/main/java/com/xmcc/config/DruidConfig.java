package com.xmcc.config;


import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.google.common.collect.Lists;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DruidConfig {


    @Bean
    @ConfigurationProperties(prefix = "spring.druid")//导入数据源
    public DruidDataSource druidDataSource(){

        DruidDataSource druidDataSource = new DruidDataSource();

        //Lists.newArrayList()相当于new ArrayList(); goole提供
        druidDataSource.setProxyFilters(Lists.newArrayList(statFilter()));
        return druidDataSource;

    }

  //配置过滤数据
    @Bean
    public StatFilter statFilter(){

        StatFilter statFilter = new StatFilter();
        statFilter.setLogSlowSql(true);
        statFilter.setSlowSqlMillis(5);
        statFilter.setMergeSql(true);
        return statFilter;

    }

    @Bean
    public ServletRegistrationBean servletRegistrationBean(){
        //localhost:8888/sell/druid
        return new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
    }

}
