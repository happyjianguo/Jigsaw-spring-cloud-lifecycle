package com.yiguan.jigsaw.order.biz.fsm;

import com.yiguan.jigsaw.order.biz.OrderStatus;
import net.imadz.lifecycle.StateConverter;

public class OrderStatusConverter implements StateConverter<OrderStatus> {
  @Override
  public String toState(OrderStatus orderStatus) {
    return orderStatus.name();
  }

  @Override
  public OrderStatus fromState(String stateName) {
    return OrderStatus.valueOf(stateName);
  }
}
