//package com.slj.mq;
//
//import com.rabbitmq.client.BuiltinExchangeType;
//import lombok.Builder;
//import lombok.Data;
//import org.springframework.amqp.core.AcknowledgeMode;
//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.BindingBuilder;
//import org.springframework.amqp.core.DirectExchange;
//import org.springframework.amqp.core.Exchange;
//import org.springframework.amqp.core.FanoutExchange;
//import org.springframework.amqp.core.Queue;
//import org.springframework.amqp.core.TopicExchange;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.core.RabbitAdmin;
//import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
//import org.springframework.beans.factory.FactoryBean;
//import org.springframework.util.StringUtils;
//
///**
// * @author suliujie
// * @since 2022-02-21 11:07
// */
//@Data
//@Builder
//public class DynamicConsumerContainerFactory implements FactoryBean<SimpleMessageListenerContainer> {
//  private BuiltinExchangeType exchangeType;
//
//  private String directExchange;
//  private String topicExchange;
//  private String fanoutExchange;
//
//  private String queue;
//  private String routingKey;
//
//
//  private Boolean autoDeleted;
//  private Boolean durable;
//  private Boolean autoAck;
//
//  private ConnectionFactory connectionFactory;
//  private RabbitAdmin       rabbitAdmin;
//  private Integer           concurrentNum;
//
//  // 消费方
//  private DynamicConsumer consumer;
//
//
//  private Exchange buildExchange() {
//    if (null != directExchange) {
//      exchangeType = BuiltinExchangeType.DIRECT;
//      return new DirectExchange(directExchange);
//    } else if (null != topicExchange) {
//      exchangeType = BuiltinExchangeType.TOPIC;
//      return new TopicExchange(topicExchange);
//    } else if (null != fanoutExchange) {
//      exchangeType = BuiltinExchangeType.FANOUT;
//      return new FanoutExchange(fanoutExchange);
//    } else {
//      if (StringUtils.isEmpty(routingKey)) {
//        throw new IllegalArgumentException("defaultExchange's routingKey should not be null!");
//      }
//      exchangeType = BuiltinExchangeType.HEADERS;
//      return new DirectExchange("");
//    }
//  }
//
//
//  private Queue buildQueue() {
//    if (StringUtils.isEmpty(queue)) {
//      throw new IllegalArgumentException("queue name should not be null!");
//    }
//
//    return new Queue(queue, durable == null ? false : durable, false, autoDeleted == null ? true : autoDeleted);
//  }
//
//
//  private Binding bind(Queue queue, Exchange exchange) throws Exception {
//    if(exchange instanceof DirectExchange){
//      return  BindingBuilder.bind(queue).to((DirectExchange) exchange).with(routingKey);
//    }
//    else if(exchange instanceof TopicExchange){
//      return  BindingBuilder.bind(queue).to((TopicExchange) exchange).with(routingKey);
//    }
//    else if(exchange instanceof FanoutExchange){
//      return  BindingBuilder.bind(queue).to((FanoutExchange) exchange);
//    }
//    else {
//      throw new Exception("绑定的类型不存在");
//    }
//    //return  BindingBuilder.bind(queue).to(exchange).with(routingKey);
//    //exchange.binding(queue, exchange, routingKey);
//  }
//
//
//  private void check() {
//    if (null == rabbitAdmin || null == connectionFactory) {
//      throw new IllegalArgumentException("rabbitAdmin and connectionFactory should not be null!");
//    }
//  }
//
//
//  @Override
//  public SimpleMessageListenerContainer getObject() throws Exception {
//    check();
//
//    Queue queue = buildQueue();
//    Exchange exchange = buildExchange();
//    Binding binding = bind(queue, exchange);
//
//    rabbitAdmin.declareQueue(queue);
//    rabbitAdmin.declareExchange(exchange);
//    rabbitAdmin.declareBinding(binding);
//
//    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//    container.setRabbitAdmin(rabbitAdmin);
//    container.setConnectionFactory(connectionFactory);
//    container.setQueues(queue);
//    container.setPrefetchCount(20);
//    container.setConcurrentConsumers(concurrentNum == null ? 1 : concurrentNum);
//    container.setAcknowledgeMode(autoAck ? AcknowledgeMode.AUTO : AcknowledgeMode.MANUAL);
//
//
//    if (null != consumer) {
//      container.setMessageListener(consumer);
//    }
//    return container;
//  }
//
//  @Override
//  public Class<?> getObjectType() {
//    return SimpleMessageListenerContainer.class;
//  }
//
//}