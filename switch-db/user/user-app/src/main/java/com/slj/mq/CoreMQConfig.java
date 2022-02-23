package com.slj.mq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liufuhong
 * @since 2020-12-09 14:35
 */

@Configuration
public class CoreMQConfig {

//  @Bean
//  public Exchange exchange() {
//    return new TopicExchange(ex);
//  }

//  @Bean
//  public Queue siBoxConfirmQueue() {
//    return new Queue(C_SI_BOX_CONFIRM_QUEUE, true);
//  }
//
//  @Bean
//  public Binding siBoxConfirmBinding(Queue siBoxConfirmQueue, Exchange exchange) {
//    return BindingBuilder.bind(siBoxConfirmQueue).to(exchange).with(SI_BOX_CONFIRM_ROUTING_KEY).noargs();
//  }
//
//  @Bean
//  public Queue soOrderConfirmQueue() {
//    return new Queue(C_SO_ORDER_CONFIRM_QUEUE, true);
//  }
//
//  @Bean
//  public Binding soOrderConfirmBinding(Queue soOrderConfirmQueue, Exchange exchange) {
//    return BindingBuilder.bind(soOrderConfirmQueue).to(exchange).with(SO_ORDER_CONFIRM_ROUTING_KEY).noargs();
//  }
//
//  /**
//   * 入库箱审核队列
//   *
//   * @return
//   */
//  @Bean
//  public Queue wibBoxConfirmQueue() {
//    return new Queue(C_WI_BOX_CONFIRM_QUEUE, true);
//  }
//
//  /**
//   * 出库单审核队列
//   *
//   * @return
//   */
//  @Bean
//  public Queue wobOrderConfirmQueue() {
//    return new Queue(C_WO_ORDER_CONFIRM_QUEUE, true);
//  }
//
//  @Bean
//  public Queue fobOrderConfirmQueue() { return new Queue(C_FO_ORDER_CONFIRM_QUEUE, true);}
//
//  /**
//   * 根据绑定规则将队列绑定到相应的交换机上
//   */
//  @Bean
//  public Binding wibBoxConfirmBinding(Queue wibBoxConfirmQueue, Exchange exchange) {
//    return BindingBuilder.bind(wibBoxConfirmQueue).to(exchange).with(WI_BOX_CONFIRM_ROUTING_KEY).noargs();
//  }
//
//  @Bean
//  public Binding wobOrderConfirmBinding(Queue wobOrderConfirmQueue, Exchange exchange) {
//    return BindingBuilder.bind(wobOrderConfirmQueue).to(exchange).with(WO_ORDER_CONFIRM_ROUTING_KEY).noargs();
//  }
//
//  @Bean
//  public Binding fobOrderConfirmBinding(Queue fobOrderConfirmQueue, Exchange exchange) {
//    return BindingBuilder.bind(fobOrderConfirmQueue).to(exchange).with(FO_ORDER_CONFIRM_ROUTING_KEY).noargs();
//  }
//
//  @Bean
//  public Queue tsOrderConfirmQueue() {
//    return new Queue(C_TS_ORDER_CONFIRM_QUEUE, true);
//  }
//
//  @Bean
//  public Binding tsOrderConfirmBinding(Queue tsOrderConfirmQueue, Exchange exchange) {
//    return BindingBuilder.bind(tsOrderConfirmQueue).to(exchange).with(TS_ORDER_CONFIRM_ROUTING_KEY).noargs();
//  }
//
//  @Bean
//  public Queue fmOrderConfirmQueue(){
//    return new Queue(C_FM_ORDER_CONFIRM_QUEUE,true);
//  }
//  @Bean
//  public Binding fmOrderConfirmBinding(Queue fmOrderConfirmQueue,Exchange exchange){
//    return BindingBuilder.bind(fmOrderConfirmQueue).to(exchange).with(FM_ORDER_CONFIRM_ROUTING_KEY).noargs();
//  }
//
//  @Bean
//  public Queue sibOrderConfirmQueue(){
//    return new Queue(C_SI_ORDER_CONFIRM_QUEUE,true);
//  }
//  @Bean
//  public Binding sibOrderConfirmBinding(Queue sibOrderConfirmQueue,Exchange exchange){
//    return BindingBuilder.bind(sibOrderConfirmQueue).to(exchange).with(SI_ORDER_CONFIRM_ROUTING_KEY).noargs();
//  }
//
//  @Bean
//  public Queue wibOrderConfirmQueue(){
//    return new Queue(C_WI_ORDER_CONFIRM_QUEUE,true);
//  }
//  @Bean
//  public Binding wibOrderConfirmBinding(Queue wibOrderConfirmQueue,Exchange exchange){
//    return BindingBuilder.bind(wibOrderConfirmQueue).to(exchange).with(WI_ORDER_CONFIRM_ROUTING_KEY).noargs();
//  }
//
//  @Bean
//  public Queue wtsOrderConfirmQueue(){
//    return new Queue(C_WTS_ORDER_CONFIRM_QUEUE,true);
//  }
//  @Bean
//  public Binding wtsOrderConfirmBinding(Queue wtsOrderConfirmQueue,Exchange exchange){
//    return BindingBuilder.bind(wtsOrderConfirmQueue).to(exchange).with(WTS_ORDER_CONFIRM_ROUTING_KEY).noargs();
//  }
  @Bean
  public Queue userUpdateQueue(){
    return new Queue(MQConstant.U_USER_UPDATE_QUEUE,true);
  }
  @Bean
  public Binding userUpdateBinding(Queue userUpdateQueue, Exchange exchange){
    return BindingBuilder.bind(userUpdateQueue).to(exchange).with(MQConstant.TEST_USER_UPDATE_ROUTING_KEY).noargs();
  }

  @Bean
  public Queue userQuertQueue(){
    return new Queue(MQConstant.U_USER_QUERY_QUEUE,true);
  }
  @Bean
  public Binding userQueryBinding(Queue userQuertQueue, Exchange exchange){
    return BindingBuilder.bind(userQuertQueue).to(exchange).with(MQConstant.TEST_USER_QUERY_ROUTING_KEY).noargs();
  }

  @Bean
  public Queue tenantAddQue(){
    return new Queue(MQConstant.U_TENANT_ADD_QUEUE,true);
  }
  @Bean
  public Binding tenantAddBind(Queue tenantAddQue, Exchange exchange){
    return BindingBuilder.bind(tenantAddQue).to(exchange).with(MQConstant.TENANT_ADD_ROUTING_KEY).noargs();
  }

  @Bean
  public Queue tenantUpdateQue(){
    return new Queue(MQConstant.U_TENANT_UPDATE_QUEUE,true);
  }
  @Bean
  public Binding tenantUpdateBind(Queue tenantUpdateQue, Exchange exchange){
    return BindingBuilder.bind(tenantUpdateQue).to(exchange).with(MQConstant.TENANT_UPDATE_ROUTING_KEY).noargs();
  }

//  @Bean
//  public Queue userOrderUpdateQueue(){
//    return new Queue(MQConstant.U_O_USER_UPDATE_QUEUE,true);
//  }
//  @Bean
//  public Binding userOrderUpdateBinding(Queue userOrderUpdateQueue, Exchange exchange){
//    return BindingBuilder.bind(userOrderUpdateQueue).to(exchange).with(MQConstant.TEST_USER_ORDER_UPDATE_ROUTING_KEY).noargs();
//  }

}
