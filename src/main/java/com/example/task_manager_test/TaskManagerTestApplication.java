package com.example.task_manager_test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class TaskManagerTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskManagerTestApplication.class, args);
	}

}
