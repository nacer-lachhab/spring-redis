package com.nacer.redisApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
	
	@GetMapping("/{idProduct}")
	@Cacheable(key = "#idProduct",
			   value = "Product",
			   unless ="#result.price > 555")
	//le cache est un hash nomé "Product", les clés sont les idProduct
	//unless: pour faire des conditions
	//result: spring lang exp refere au resultat de la derniere operation
	public Product save(@PathVariable int idProduct) {
		return productDao.findById(idProduct);
	}
	
	@PostMapping
	public Product save(@RequestBody Product product) {
		return productDao.save(product);
	}
	
	@DeleteMapping("/{idProduct}")
	@CacheEvict(key = "#idProduct",value = "Product")
	//va se répercuter sur le cache
	//va supprimer l'element dans le hash "Product" avec la clé "#idProduct" 
	public String delete(@PathVariable int idProduct) {
		return productDao.delete(idProduct);
	}
	
	@GetMapping("/all")
	public List<Product> get() {
		return productDao.findAll();
	}
}
