package com.benz.redis.api.config;

import com.benz.redis.api.listener.Consumer;
import com.benz.redis.api.model.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

@Configuration
public class RedisConfiguration {

    @Bean
    public ChannelTopic channelTopic()
    {
        return new ChannelTopic("PRODUCT_TOPIC");
    }

        @Bean
        public RedisMessageListenerContainer messageListenerContainer(MessageListenerAdapter messageListenerAdapter)
        {
            RedisMessageListenerContainer container =
                    new RedisMessageListenerContainer();

            container.setConnectionFactory(getJedisConnectionFactory());
            container.addMessageListener(messageListenerAdapter,channelTopic());

            return container;
        }

        @Bean(name = "productConfig")
        public RedisTemplate<String, Product> getProductConfig()
        {
            RedisTemplate<String,Product> redisTemplate
                    =new RedisTemplate<>();
            redisTemplate.setConnectionFactory(getJedisConnectionFactory());
            redisTemplate.setValueSerializer(jackson2JsonRedisSerializer());
            return redisTemplate;
        }

    @Bean
    public RedisTemplate<String,Object> redisTemplate()
    {
        RedisTemplate<String,Object> redisTemplate
                =new RedisTemplate<>();
        redisTemplate.setConnectionFactory(getJedisConnectionFactory());
        return redisTemplate;
    }

    @Bean
    public MessageListenerAdapter messageListenerAdapter(Consumer consumer)
    {
        MessageListenerAdapter listenerAdapter = new MessageListenerAdapter(consumer);
        listenerAdapter.setSerializer(jackson2JsonRedisSerializer());
        return listenerAdapter;
    }

    private Jackson2JsonRedisSerializer<Product> jackson2JsonRedisSerializer()
    {
        return new Jackson2JsonRedisSerializer<Product>(Product.class);
    }


    private JedisConnectionFactory getJedisConnectionFactory()
    {
        return new JedisConnectionFactory(new RedisStandaloneConfiguration("127.0.0.1",6379));
    }
}
