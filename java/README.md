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

# Memory usage

From: http://www.openkb.info/2014/06/how-to-check-java-memory-usage.html

`jps` to find process

1. ps and pmap can show total reserved memory from OS.
```
ps auwx|egrep "MEM|2304"|grep -v grep
pmap -x 2304
```

2. jmap and jstat can show used space of heap&stack.
```
jmap -heap 2304|egrep ":|used     ="
```

```
jstat -gc 2304
```
New Generation(used memory) = S0U+S1U+EU
concurrent mark-sweep generation(used memory) = OU
Perm Generation(used memory)=PU.

3. jmap -histo can show top heap memory objects
```
jmap -histo -F 2304 | less
```

# https://medium.com/zaloni-engineering/troubleshooting-high-cpu-and-memory-leaks-in-java-processes-fa962775351d
```
set -u
pid=$(pgrep java)
for i in {1..600}; do
read -r load1m load5m <<<$(uptime | grep -oE 'load average: .*' | cut -d : -f 2 | tr '.' ',' | cut -d , -f 1,3 | tr ',' ' ')
if [ $load1m -ge $load5m ]; then
        date >> all.log
        top -n 1 -b -H -p $pid | tr -s ' ' | awk '{ printf("0x%x %s\n", $1, $0) }' | sed -n '8,13p' > top5.log
        cat top5.log >> all.log
        jstack $pid > jstack.log
        for i in $(awk '{print "/nid=" $1 "/,/^$/p"}' top5.log); do
                sed -n $i jstack.log >> all.log
        done
fi
sleep 5
done
```

Do a thread dump using jcmd
```
jcmd $(pgrep java) Thread.print > td.log
```
