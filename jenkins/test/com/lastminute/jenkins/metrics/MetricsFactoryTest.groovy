package com.lastminute.jenkins.metrics

import com.lastminute.jenkins.mocks.FakeMetricsApiClass
import org.junit.Test

class MetricsFactoryTest
{
  private MetricsFactory metricsFactory = new MetricsFactory(new FakeMetricsApiClass(), "testNode", "testApp")

  @Test
  void createATimer()
  {
    Timer timer = metricsFactory.timer("image", "build")
    assert timer.getMetricName() == "pipelines.testNode.testApp.image.build"
  }

  @Test
  void nodeNameRemoveAfterLastDash()
  {
    def node = "docker-k8s-abc123"
    assert metricsFactory.aggregateDynamicNodes(node) == "docker-k8s"

  }
  @Test
  void nodeNameWithALotOfDash()
  {
    def node = "selenium-chrome-k8s-abc123"
    assert metricsFactory.aggregateDynamicNodes(node) == "selenium-chrome-k8s"

  }

  @Test
  void nodeNameWithoutDashRemoveNothing()
  {
    def node = "docker_without_dash"
    assert metricsFactory.aggregateDynamicNodes(node) == "docker_without_dash"

  }
}
