package com.lastminute.jenkins.mocks

import java.util.concurrent.TimeUnit

class FakeMetricsApiClass
{
  def registry = new FakeRegistry()

  def metricRegistry()  {return registry }

  static class FakeRegistry implements Serializable
  {
    def metrics = [:]
    Object timer(name){
      if(!metrics.name)
        metrics.name = new FakeTimerMetric()
      return metrics.name
    }
    void remove(name){
      metrics.remove(name)
    }
  }

  static class FakeTimerMetric implements Serializable
  {
    private long metricValue
    private TimeUnit unit
    private count = 0
    def update(value, unit){
      metricValue = value
      this.unit = unit
      count++
    }
    def getCount(){ return  count}
    def getSnapshot(){ return [getMean: { -> metricValue}] }
  }
}
