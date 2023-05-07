package com.nacer.redisApp.config;

import org.springframework.boot.r2dbc.ConnectionFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.nacer.redisApp.entity.Product;

@Configuration
@EnableRedisRepositories
public class RedisConfig {

	//jedis: connector equivalent to JDBC [java + Redis]
	@Bean
	public JedisConnectionFactory myConnectionFactory() {
		
//		return ConnectionFactoryBuilder.derivedFrom()
		
		RedisStandaloneConfiguration configuration = 
				new RedisStandaloneConfiguration("localhost",6379);
		return new JedisConnectionFactory(configuration);
	}
	
	//equivalent de JdbcTemplate
	@Bean
	public RedisTemplate<String,Object> template(){
		RedisTemplate<String,Object> template = new RedisTemplate<>();
		template.setConnectionFactory(myConnectionFactory());
		template.setKeySerializer(new StringRedisSerializer());
		template.setHashKeySerializer(new StringRedisSerializer());
		template.setHashKeySerializer(new JdkSerializationRedisSerializer());
		template.setHashValueSerializer(new JdkSerializationRedisSerializer());
		template.setEnableTransactionSupport(true);
		template.afterPropertiesSet();
		return template;
	}
}
