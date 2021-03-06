# reactor-demo
Demonstration of how to use Reactor automation framework and it extensions to enable WebService, WebUI, iOS and Android automation.

## execution report
1. install vagrant and virtualbox

2. run
  ```
mkdir -p ~/.reactor && cd ~/.reactor && wget https://raw.githubusercontent.com/tascape/reactor-report/master/doc/reactor.sh -O reactor.sh && bash reactor.sh
  ```

3. wait - an Ubuntu VM will be created and configured with Java, Tomee, Nginx, MySQL, etc.

4. an empty report page will open in your default web browser (**bookmark this - http://127.0.0.1:28088/rr/suites_result.xhtml**)


###### see https://github.com/tascape/reactor-report/tree/master/doc. It is verified on macOS.


## webui

#### 1. run basic demo
```
cd webui
mvn clean install
java -cp target/*:target/dependency/* -Dreactor.comm.WEBBROWSER_TYPE=firefox com.tascape.demo.suite.JsonEditorOnlineSuite
java -cp target/*:target/dependency/* -Dreactor.comm.WEBBROWSER_TYPE=chrome com.tascape.demo.suite.JsonEditorOnlineSuite
```
###### check http://127.0.0.1:28088/rr/suites_result.xhtml
* works on Firefox 46.0, Chrome 53.0

#### 2. page load time
* install Firefox addon https://addons.mozilla.org/en-US/firefox/addon/firebug/
* install Firefox addon https://addons.mozilla.org/en-US/firefox/addon/har-export-trigger/

run page load time demo
```
cd webui
mvn clean install
java -cp target/*:target/dependency/* com.tascape.demo.suite.PageLoadingSuite
```
###### check http://127.0.0.1:28088/rr/suites_result.xhtml
* works on Firefox 46.0
