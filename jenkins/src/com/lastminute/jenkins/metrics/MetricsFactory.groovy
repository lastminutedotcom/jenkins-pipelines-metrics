package com.lastminute.jenkins.metrics

import com.codahale.metrics.MetricRegistry

class MetricsFactory implements Serializable
{
  private final Object metricsClazz
  private baseMetricPath

  MetricsFactory(metricsClazz, String nodeName, String appName){
    this.metricsClazz = metricsClazz
    this.baseMetricPath =  MetricRegistry.name("pipelines", aggregateDynamicNodes(nodeName), appName)
  }

  Timer timer(String... metricPath)
  {
    def timerName = MetricRegistry.name(baseMetricPath, metricPath)
    return new Timer(metricsClazz, timerName)
  }

  @NonCPS
  protected String aggregateDynamicNodes(String nodeName)
  {
    nodeName.contains("-") ? nodeName.substring(0, nodeName.lastIndexOf("-")) : nodeName
  }
}
