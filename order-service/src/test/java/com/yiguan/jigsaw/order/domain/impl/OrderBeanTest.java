package com.yiguan.jigsaw.order.domain.impl;

import com.yiguan.jigsaw.order.domain.OrderStatus;
import com.yiguan.jigsaw.order.domain.entity.Order;
import com.yiguan.jigsaw.order.services.event.emitted.OrderPaid;
import net.imadz.lifecycle.LifecycleException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.repository.CrudRepository;
import com.yiguan.jigsaw.order.repositories.OrderRepository;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderBeanTest extends BizObjectTest {
  @Test(expected = NullPointerException.class)
  public void demo() throws Throwable {
    Order orderInternalState = new Order();
    orderInternalState.setId(1L);
    orderInternalState.setState(OrderStatus.Created);

    final CrudRepository repository = mock(OrderRepository.class);
    when(repository.findOne(1L)).thenReturn(orderInternalState);

    OrderBean order = createBizBean(OrderBean.class, Long.valueOf(1L), repository);

    try {
      order.accept();
    } catch (LifecycleException ex) {
      throw ex.getCause();
    }
  }

}