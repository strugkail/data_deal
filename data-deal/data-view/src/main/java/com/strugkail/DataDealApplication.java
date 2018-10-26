package com.strugkail;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
@EnableAsync
@MapperScan(basePackages = "com.strugkail.mapper")
/**
 * created by strugkail on 2018/6/19 0019
 * @author strugkail
 */
public class DataDealApplication {
	public static void main(String[] args) {
		SpringApplication.run(DataDealApplication.class, args);
	}
}
