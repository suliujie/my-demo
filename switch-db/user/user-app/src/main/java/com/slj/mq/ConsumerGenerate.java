//package com.slj.mq;
//
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.core.RabbitAdmin;
//
///**
// * @author suliujie
// * @since 2022-02-21 11:09
// */
//public class ConsumerGenerate {
//
//  /**
//   * 创建消费者
//   *
//   * @param connectionFactory
//   * @param rabbitAdmin
//   * @param exchangeName
//   * @param queueName
//   * @param routingKey
//   * @param autoDelete
//   * @param durable
//   * @param autoAck
//   * @return
//   * @throws Exception
//   */
//  public static DynamicConsumer genConsumer(ConnectionFactory connectionFactory, RabbitAdmin rabbitAdmin,
//      String exchangeName, String queueName, String routingKey, boolean autoDelete, boolean durable,
//      boolean autoAck) throws Exception {
//    MQContainerFactory fac =
//        MQContainerFactory.builder().topicExchange(exchangeName).queue(queueName).autoDeleted(autoDelete)
//            .autoAck(autoAck).durable(durable).routingKey(routingKey).rabbitAdmin(rabbitAdmin)
//            .connectionFactory(connectionFactory).build();
//    return new DynamicConsumer(fac, queueName);
//  }
//
//}
