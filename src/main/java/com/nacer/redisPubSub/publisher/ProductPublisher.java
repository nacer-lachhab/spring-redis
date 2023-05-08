package com.nacer.redisPubSub.publisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nacer.redisPubSub.model.Product;

@RestController
public class ProductPublisher {

	@Autowired //depuis le bean dans config
	private RedisTemplate template;
	
	@Autowired //depuis le bean dans config
	private ChannelTopic topic;
	
	@PostMapping("/send")
	public String publish(@RequestBody Product product) {
		System.out.println(product);
		template.convertAndSend(topic.getTopic(),product.toString());
		return "Event published in Redis Topic.....";
	}
}
