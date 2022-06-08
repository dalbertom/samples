# Visual VM Debugging

From: https://itnext.io/profiling-jvm-applications-remotely-using-visualvm-c0df9816aabf and https://stackoverflow.com/questions/1609961/visualvm-over-ssh
1. ssh -D 9696 10.125.57.31

  permissions.txt:
  ```
  grant {
    permission java.security.AllPermission;
    };
  ```
  jstatd -J-Djava.security.policy=permissions.txt -J-Djava.rmi.server.logCalls=true -J-Djava.rmi.server.hostname=10.125.57.31
  - or -
  java -Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=1089 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false Main

1. /Applications/VisualVM.app/Contents/Resources/visualvm/bin/visualvm -J-DsocksProxyHost=localhost -J-DsocksProxyPort=9696
