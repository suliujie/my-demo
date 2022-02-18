package com.slj.mq;
import com.slj.service.impl.UserServiceImpl;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import slj.UserDTO;

/**
 * @author liufuhong
 * @since 2020-12-09 14:35
 */

@Component
public class CoreMQHandler {

  @Autowired
  private UserServiceImpl userService;
//  private final RFIDService rfidService;
//  private final OrderSyncTaskService orderSyncTaskService;
//
//  public CoreMQHandler(RFIDService rfidService,
//      OrderSyncTaskService orderSyncTaskService) {this.rfidService          = rfidService;
//                                                  this.orderSyncTaskService = orderSyncTaskService;
//  }
//
//
//  @RabbitListener(queues = C_SI_BOX_CONFIRM_QUEUE)
//  public void handleSiBoxConfirm(BoxEvent event)
//      throws BusinessException {
//
//    rfidService.createFromSiBoxConfirm(event);
//  }
//
//  @RabbitListener(queues = C_SO_ORDER_CONFIRM_QUEUE)
//  public void handleSoOrderConfirm(OrderEvent event) throws BusinessException {
//
//    rfidService.createFromSoOrderConfirm(event);
//    orderSyncTaskService.createOrderSyncTask(event,OrderSyncTaskType.SO,OrderSyncTaskStatus.NONE,OrderSyncTaskAction.ORDER_CONFIRM);
//  }
//
//  /**
//   * 仓库入库箱审核同步rfid
//   *
//   * @param boxEvent
//   * @throws Exception
//   */
//  @RabbitListener(queues = C_WI_BOX_CONFIRM_QUEUE)
//  public void handleWibBoxConfirm(
//      BoxEvent boxEvent)
//      throws Exception {
//    rfidService.wibBoxConfirmSyncRfid(boxEvent);
//  }
//
//
//  /**
//   * 出库单审核同步rfid
//   *
//   * @param orderEvent
//   * @throws Exception
//   */
//  @RabbitListener(queues = C_WO_ORDER_CONFIRM_QUEUE)
//  public void handleWobOrderConfirm(
//      OrderEvent orderEvent)
//      throws Exception {
//    rfidService.wobConfirmSyncRfid(orderEvent);
//    orderSyncTaskService.createOrderSyncTask(orderEvent,OrderSyncTaskType.WO,OrderSyncTaskStatus.NONE,OrderSyncTaskAction.ORDER_CONFIRM);
//  }
//
//  /**
//   * 出库单审核同步rfid
//   *
//   * @param orderEvent
//   * @throws Exception
//   */
//  @RabbitListener(queues = C_FO_ORDER_CONFIRM_QUEUE)
//  public void handleFobOrderConfirm(
//      OrderEvent orderEvent)
//      throws Exception {
//    rfidService.confirmFobSyncRfid(orderEvent);
//    orderSyncTaskService.createOrderSyncTask(orderEvent,OrderSyncTaskType.FOB,OrderSyncTaskStatus.NONE,OrderSyncTaskAction.ORDER_CONFIRM);
//
//  }
//
//  @RabbitListener(queues = C_TS_ORDER_CONFIRM_QUEUE)
//  public void handleTsOrderConfirm(OrderEvent orderEvent) throws BusinessException {
//    rfidService.createFromTsOrderConfirm(orderEvent);
//    orderSyncTaskService.createOrderSyncTask(orderEvent,OrderSyncTaskType.STS,OrderSyncTaskStatus.NONE,OrderSyncTaskAction.ORDER_CONFIRM);
//  }
//  //todo 暂时取消
//  /*@RabbitListener(queues = C_FM_ORDER_CONFIRM_QUEUE)
//  public void handleFmOrderConfirm(OrderEvent orderEvent){
//
//    orderSyncTaskService.createOrderSyncTask(orderEvent, OrderSyncTaskType.FM, OrderSyncTaskStatus.NONE, OrderSyncTaskAction.ORDER_CONFIRM);
//  }*/
//
//  @RabbitListener(queues = C_WI_ORDER_CONFIRM_QUEUE)
//  public void handleWibOrderConfirm(OrderEvent orderEvent) throws BusinessException {
//    orderSyncTaskService.createOrderSyncTask(orderEvent,OrderSyncTaskType.WI,OrderSyncTaskStatus.NONE,OrderSyncTaskAction.ORDER_CONFIRM);
//  }
//
//  @RabbitListener(queues = C_SI_ORDER_CONFIRM_QUEUE)
//  public void handleSibOrderConfirm(OrderEvent orderEvent) throws BusinessException {
//    orderSyncTaskService.createOrderSyncTask(orderEvent,OrderSyncTaskType.SI,OrderSyncTaskStatus.NONE,OrderSyncTaskAction.ORDER_CONFIRM);
//  }
//
//  @RabbitListener(queues = C_WTS_ORDER_CONFIRM_QUEUE)
//  public void handleWTsOrderConfirm(OrderEvent orderEvent) throws BusinessException {
//    rfidService.createFromWTsOrderConfirm(orderEvent);
//    orderSyncTaskService.createOrderSyncTask(orderEvent,OrderSyncTaskType.WTS,OrderSyncTaskStatus.NONE,OrderSyncTaskAction.ORDER_CONFIRM);
//  }

  @RabbitListener(queues = MQConstant.U_USER_UPDATE_QUEUE)
  public void handle(UserDTO userDTO){
    userService.update(userDTO);
  }

//  @RabbitListener(queues = MQConstant.U_O_USER_UPDATE_QUEUE)
//  public void handle2(UserDTO userDTO){
//    userService.update(userDTO);
//    OrderDTO orderDTO=new OrderDTO();
//    orderDTO.setAge(userDTO.getAge());
//    orderDTO.setUserName(userDTO.getUserName());
//    orderDTO.setDataSourceId(userDTO.getDataSourceId()+"Order");
//    RequestHeaderHolder.setDataSourceId(orderDTO.getDataSourceId());
//    orderService.update(orderDTO);
//  }

}
