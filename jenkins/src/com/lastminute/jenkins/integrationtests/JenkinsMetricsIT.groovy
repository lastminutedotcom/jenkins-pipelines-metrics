package com.lastminute.jenkins.integrationtests

def recordTimerITTest(j, metricClazz)
{
  def metricsTimerIT = new MetricsTimerIT(metricClazz)
  j.stage metricsTimerIT.getClass().getSimpleName()
  metricsTimerIT.test()
}

