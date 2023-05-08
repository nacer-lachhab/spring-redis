package com.nacer.redisPubSub;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RedisSpringAppPubSubApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(RedisSpringAppPubSubApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("redis-pub-sub App running with success....");
	}

}
