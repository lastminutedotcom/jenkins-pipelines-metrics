package com.lastminute.jenkins.integrationtests


import com.lastminute.jenkins.mocks.FakeMetricsApiClass
import com.lastminute.jenkins.mocks.FakeJenkinsCommands
import org.junit.Test

class JenkinsIntegrationTests
{

  @Test
  void invokeMetricsTest()
  {
    def closure = new JenkinsMetricsIT()

    closure.recordTimerITTest(new FakeJenkinsCommands(), new FakeMetricsApiClass())
  }
}
