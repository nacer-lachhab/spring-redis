package com.nacer.redisPubSub.subscriber;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ProductReceiver implements MessageListener{

	@Override
	public void onMessage(Message message, byte[] pattern) {
		// TODO Auto-generated method stub
		log.info("Event Received, Product:**** {}",message);
	}
}
