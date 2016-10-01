# reactor-demo
Demonstration of how to use Reactor automation framework and it extensions to enable WebService, WebUI, iOS and Android automation.

## execution report
1. install vagrant and virtualbox

2. run
  ```
mkdir -p ~/.reactor && cd ~/.reactor && wget https://raw.githubusercontent.com/tascape/reactor-report/master/doc/reactor.sh -O reactor.sh && bash reactor.sh
  ```

3. wait - an Ubuntu VM will be created and configured with Java, Tomee, Nginx, MySQL, etc.

4. an empty report page will open in your default web browser (**bookmark this - [http://127.0.0.1:28088/rr/suites_result.xhtml]**)


###### see [https://github.com/tascape/reactor-report/tree/master/doc]. It is verified on macOS.


## webui
```
cd webui
mvn clean install
java -cp target/*:target/dependency/* com.tascape.demo.suite.JsonEditorOnlineSuite
```
###### check [http://127.0.0.1:28088/rr/suites_result.xhtml]
* tried on Firefox 46.0
