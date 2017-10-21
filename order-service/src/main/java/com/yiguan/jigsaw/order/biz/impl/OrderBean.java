package com.yiguan.jigsaw.order.biz.impl;

import com.google.common.eventbus.Subscribe;
import com.yiguan.jigsaw.order.biz.OrderBO;
import com.yiguan.jigsaw.order.biz.OrderStatus;
import com.yiguan.jigsaw.order.biz.entity.Order;
import com.yiguan.jigsaw.order.biz.fsm.OrderFSM;
import com.yiguan.jigsaw.order.biz.fsm.OrderStatusConverter;
import com.yiguan.jigsaw.order.biz.repo.OrderRepository;
import com.yiguan.jigsaw.order.service.args.OrderCreationReq;
import com.yiguan.jigsaw.order.service.event.inbound.ArtifactShippingStarted;
import com.yiguan.jigsaw.order.service.event.inbound.ArtifactSigned;
import com.yiguan.jigsaw.order.service.event.outbound.OrderPaid;
import net.imadz.lifecycle.annotations.Event;
import net.imadz.lifecycle.annotations.LifecycleMeta;
import net.imadz.lifecycle.annotations.StateIndicator;
import net.imadz.lifecycle.annotations.state.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@LifecycleMeta(OrderFSM.class)
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class OrderBean extends BizObjectBase<OrderBean, Order, Long> implements OrderBO {

  @SuppressWarnings("PMD")
  public OrderBean(OrderCreationReq order) {
    super(new Order());
  }

  @Autowired
  public OrderBean(Long oid) {
    super(oid);
  }

  @Autowired
  public void setRepository(OrderRepository repository) {
    this.repository = repository;
  }

  @StateIndicator
  @Converter(OrderStatusConverter.class)
  public OrderStatus getStatus() {
    return this.internalState.getState();
  }

  @SuppressWarnings("PMD")
  private void setStatus(OrderStatus stateName) {
    this.internalState.setState(stateName);
  }

  @Override
  @Event
  @Subscribe
  public OrderBO orderPaid(OrderPaid orderPaidEvent) {
    return this;
  }

  @Override
  @Event
  @Subscribe
  public OrderBO shippingStarted(ArtifactShippingStarted shippingStartedEvent) {
    return this;
  }

  @Override
  @Event
  @Subscribe
  public OrderBO signedByCustomer(ArtifactSigned artifactSignedEvent) {
    return this;
  }

  @Override
  @Event
  public OrderBO accept() {
    return this;
  }

  @Override
  @Event
  public OrderBO cancel() {
    return this;
  }

}
