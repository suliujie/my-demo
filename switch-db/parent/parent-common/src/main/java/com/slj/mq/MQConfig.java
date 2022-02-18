package com.slj.mq;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 消息队列配置
 *
 * @author liufuhong
 * @since 2020-12-03 16:58
 */


@Configuration
public class MQConfig {

  @Bean
  public Exchange exchange() {
    return new TopicExchange(MQConstant.EXCHANGE);
  }

}
