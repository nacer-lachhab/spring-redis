package com.nacer.redisApp.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor 
@NoArgsConstructor
@RedisHash("Product") //eq to @Entity+@Table("Product") in redis
public class Product implements Serializable{

	@Id
	private int id;
	private String name;
	private int qte;
	private double price;
}
