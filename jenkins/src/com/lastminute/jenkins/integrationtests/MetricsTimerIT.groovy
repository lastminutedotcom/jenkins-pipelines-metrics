package com.lastminute.jenkins.integrationtests

import com.codahale.metrics.MetricRegistry
import com.lastminute.jenkins.metrics.MetricsFactory
import com.lastminute.jenkins.metrics.Timer
import org.codehaus.groovy.runtime.DefaultGroovyStaticMethods


class MetricsTimerIT implements Serializable
{
  private final Object metricsClazz

  MetricsTimerIT(metricsClazz)
  {
    this.metricsClazz = metricsClazz
  }

  void test()
  {
    def metricName = MetricRegistry.name("pipelines", "nodeName", "appName", "test", "something")
    metricsClazz.metricRegistry().remove(metricName)

    MetricsFactory metrics = new MetricsFactory(metricsClazz, "nodeName", "appName")
    Timer testTimer = metrics.timer("test", "something")

    DefaultGroovyStaticMethods.sleep(100l)
    testTimer.stop()

    assert metricsClazz.metricRegistry().timer(metricName): "Timer " + metricName + " don't exists"
    def actualCount = metricsClazz.metricRegistry().timer(metricName).getCount()
    assert actualCount == 1: "Actual Count value is:" + actualCount

    def mean = metricsClazz.metricRegistry().timer(metricName).getSnapshot().getMean()
    assert mean > 100 * 1000000: "Actual mean value is:" + mean
    assert mean < 200 * 1000000: "Actual mean value is:" + mean
  }
}
