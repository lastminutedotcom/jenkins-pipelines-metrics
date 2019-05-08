package com.lastminute.jenkins.metrics


import com.lastminute.jenkins.mocks.FakeMetricsApiClass
import org.junit.Test

class TimerTest
{
  public static final int sleepTime = 10l
  private FakeMetricsApiClass metricsApiClass = new FakeMetricsApiClass()

  @Test
  void timerCalculateDurationAndCallRegistryUpdate()
  {
    Timer timer = new Timer(metricsApiClass, "my.test")
    sleep(sleepTime)
    timer.stop()

    def elapsedTimeMillis = metricsApiClass.metricRegistry().timer("jenkins.my.test").getSnapshot().getMean() / 1000000
    assert elapsedTimeMillis > sleepTime: "MeasuredTime is: " + elapsedTimeMillis
  }
}