package com.melot.nuggets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//(exclude = { DataSourceAutoConfiguration.class, MongoAutoConfiguration.class}
//		) //排除无用AutoConfiguration
//@ComponentScan("com.melot") //指定扫描包
//@MapperScan(basePackages = "com.melot.delay.gift.consumer.dao", sqlSessionFactoryRef = "sqlSessionFactory_tshow") //mybatis扫描
//@ImportResource("classpath:conf/disconf.xml") //引入disconf
public class App {
	// CHECKSTYLE:OFF
    public static void main(final String[] args) {
        SpringApplication.run(App.class, args);
    }
}
