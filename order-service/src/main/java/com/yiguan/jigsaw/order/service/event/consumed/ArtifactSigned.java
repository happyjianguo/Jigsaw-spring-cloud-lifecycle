package com.yiguan.jigsaw.order.service.event.consumed;

import lombok.Getter;

@Getter
public class ArtifactSigned implements LogisticLifecycleEvent {
  private Long orderId;
}
