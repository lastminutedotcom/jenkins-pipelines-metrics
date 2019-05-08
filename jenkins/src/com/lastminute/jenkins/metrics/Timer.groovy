package com.lastminute.jenkins.metrics

import com.codahale.metrics.Clock

import java.util.concurrent.TimeUnit

class Timer implements Serializable
{
  private long startTime
  String name
  private final Object metricsClazz

  Timer(metricsClazz, String name)
  {
    this.metricsClazz = metricsClazz
    this.name = name
    this.startTime = Clock.defaultClock().getTick()
  }

  @NonCPS
  void stop()
  {
    def thisTimer = metricsClazz.metricRegistry().timer(name)
    thisTimer.update(Clock.defaultClock().getTick() - startTime, TimeUnit.NANOSECONDS)
  }

  String getMetricName()
  {
    return name
  }

  long getStartTime()
  {
    return startTime
  }
}
