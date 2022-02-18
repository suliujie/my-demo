package com.slj.business;

import com.slj.OrderDTO;
import com.slj.OrderServiceInterface;
import com.slj.persistence.OrderRepository;
import com.slj.pojo.Order;
import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author suliujie
 * @since 2022-02-16 9:58
 */
@Service
@DubboService
public class OrderService implements OrderServiceInterface {

  private final Logger log= LoggerFactory.getLogger(OrderService.class);
@Autowired
private OrderRepository repository;
  @Override
  public void update(OrderDTO orderDTO) {
    log.info("开始更新单据"+orderDTO.toString());
    Order order = repository.getByCloud(orderDTO.getUserName());
    order.setAge(orderDTO.getAge());
    repository.update(order);
    log.info("单据更新成功");
  }
}
