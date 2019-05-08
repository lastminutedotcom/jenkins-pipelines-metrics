package com.lastminute.jenkins.mocks

class FakeJenkinsCommands implements Serializable
{
  void echo(message){ println message}

  void sh(){}

  void timeout(time, timeoutClosure ){ println "Timeout : " + time }

  void wrap(map, cl) { cl()}

  void input(message){ println "INPUT:" + message}

  void error(message) {println "ERROR:" + message}

  void stage(title)   { println "\n---------- " + title + " --------" }

  Map httpRequest(request) {
    println(request)
    def result = [status: 200]
    if(request.url.contains("service-management"))
      result.content = '{"systemId": "id", "number": "CHG123"}'
    if(request.url.contains("/info"))
      result.content = '{"deploymentName": "fake-stable", ' +
          '"events": [{ "message":  "::message::", "reason": "::reason::", "kind": "::kind::"}]}'
    return result
  }
}
