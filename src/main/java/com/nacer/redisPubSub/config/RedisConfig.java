package com.nacer.redisPubSub.config;

import java.nio.charset.Charset;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.nacer.redisPubSub.subscriber.ProductReceiver;

@Configuration
public class RedisConfig {

	//jedis: connector equivalent to JDBC [java + Redis]
		@Bean
		public JedisConnectionFactory myConnectionFactory() {
			
//			return ConnectionFactoryBuilder.derivedFrom()
			
			RedisStandaloneConfiguration configuration = 
					new RedisStandaloneConfiguration("localhost",6379);
			return new JedisConnectionFactory(configuration);
		}
		
		//equivalent de JdbcTemplate
		@Bean
		public RedisTemplate<String,Object> template(){
			RedisTemplate<String,Object> template = new RedisTemplate<>();
			template.setConnectionFactory(myConnectionFactory());
			//la methode de serialisation et le format
			//absence de format "UTF-8" cree probleme de caractere speciaux de surplus
			template.setValueSerializer(new GenericToStringSerializer<Object>(Object.class, Charset.forName("UTF-8")));
			return template;
		}
		
		@Bean
		public ChannelTopic topic() {
			//creation d un topic
			return new ChannelTopic("spring-redis-pubsub");
		}
		
		@Bean
		public MessageListenerAdapter listenerAdapter() {
			//enregistrer les instances des listners
			return new MessageListenerAdapter(new ProductReceiver());
		}
		
		@Bean
		//créer un conteneur pour écoute depuis topic/queue Redis.
		//associer le listner au topic
		public RedisMessageListenerContainer listenerContainer() {
			RedisMessageListenerContainer container = 
					new RedisMessageListenerContainer();
			container.setConnectionFactory(myConnectionFactory());
			container.addMessageListener(listenerAdapter(),topic());
			return container;
		}
}
