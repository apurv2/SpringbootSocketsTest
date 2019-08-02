package meetme.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import meetme.redis.model.User;

@Configuration
public class CacheConfiguration {

//    @Bean
//    JedisConnectionFactory jedisConnectionFactory() {
//        JedisConnectionFactory jdf = new JedisConnectionFactory();
////        jdf.setHostName("redis3.krt6ts.0001.use2.cache.amazonaws.com");
////        jdf.setPort(6379);
//        return jdf;
//    }
//    @Bean
//    RedisTemplate<String, User> redisTemplate() {
//        RedisTemplate<String, User> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(jedisConnectionFactory());
//        return redisTemplate;
//    }
}
