package com.nacer.redisApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nacer.redisApp.entity.Product;
import com.nacer.redisApp.repository.ProductDao;

@RestController
@RequestMapping("/redisProduct")
public class ProductController {

	@Autowired
	ProductDao productDao;
	
	@PostMapping
	public Product save(@RequestBody Product product) {
		return productDao.save(product);
	}
	
	@DeleteMapping("/{idProduct}")
	public String delete(@PathVariable int idProduct) {
		return productDao.delete(idProduct);
	}
	
	@GetMapping("/{idProduct}")
	public Product save(@PathVariable int idProduct) {
		return productDao.findById(idProduct);
	}
	
	@GetMapping("/all")
	public List<Product> get() {
		return productDao.findAll();
	}
}
