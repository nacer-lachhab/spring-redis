package com.nacer.redisApp.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.nacer.redisApp.entity.Product;

@Repository
public class ProductDao {
		
	private static final String HASH_KEY = "Product";
	@Autowired
	private RedisTemplate template;
	
	public Product save(Product product) {
		template.opsForHash()
				.put(HASH_KEY,product.getId(), product);//hash,key,value
		return product;
	}
	
	public List<Product> findAll() {
		return template.opsForHash().values(HASH_KEY);
	}
	
	public Product findById(int idProduct) {
		return (Product) template.opsForHash().get(HASH_KEY,idProduct);
	}
	
	public String delete(int idProduct) {
		Long number = template.opsForHash().delete(HASH_KEY,idProduct);
		return number +" elements deleted!!!!";
	}
}
