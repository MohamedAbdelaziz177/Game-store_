//package com._Abdelaziz26.Game.Configurations;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.cache.RedisCacheConfiguration;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//
//
//@Configuration
//public class RedisConfig {
//
//
//
//    @Bean
//    RedisCacheManager redisCacheManager(RedisConnectionFactory connectionFactory) {
//
//        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
//                .entryTtl(java.time.Duration.ofMinutes(10))
//                .disableCachingNullValues();
//
//        return RedisCacheManager.builder()
//                .cacheDefaults(redisCacheConfiguration)
//                .build();
//
//    }
//
//    @Bean
//    RedisConnectionFactory redisConnectionFactory()
//    {
//        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
//
//        jedisConnectionFactory.setHostName("localhost");
//        jedisConnectionFactory.setPort(6379);
//
//        return jedisConnectionFactory;
//    }
//
//
//}
