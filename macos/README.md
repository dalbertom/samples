# Useful macOS Commands

## Check logs
E.g. to see the logs for Docker
```
syslog -w -k Sender Docker
```
`-w` is akin to `tail -f`
`-k` takes key value parameters.

## File system usage
* fs_usage
* opensnoop
* iosnoop

## Networking

https://apple.stackexchange.com/questions/24066/how-to-simulate-slow-internet-connections-on-the-mac

Comment from ubershmekel:
Mac OS X 10.10+ users need to use dnctl and pfctl but documented usage examples aren't easy to find.

```
# Configure `pfctl` to use `customRule`.
(cat /etc/pf.conf && echo "dummynet-anchor \"customRule\"" && echo "anchor \"customRule\"") | sudo pfctl -f -

# Define `customRule` to pipe traffic to `pipe 1`.
# Note this is the actual port definition, not a textual comment
echo "dummynet in quick proto tcp from any to any port 443 pipe 1" | sudo pfctl -a customRule -f -

# Define what `pipe 1` should do to traffic
sudo dnctl pipe 1 config delay 10000
sudo dnctl pipe 1 config bw 10Kbit/s

# DO NOT FORGET to undo these when you're done
sudo dnctl -q flush
sudo pfctl -f /etc/pf.conf
````
If you want to go all out and shape everything you can use:

```
echo "dummynet in quick proto tcp from any to any pipe 1" | sudo pfctl -a customRule -f -
```
I believe this also affects localhost pipes which slowed down my vs-code debugging, so be mindful of that.

### man pages
https://www.manpagez.com/man/8/dnctl/
https://www.manpagez.com/man/8/pfctl/


https://apple.stackexchange.com/questions/24066/how-to-simulate-slow-internet-connections-on-the-mac
https://blog.leiy.me/post/bw-throttling-on-mac/

### Setup

```
pfctl -e
(cat /etc/pf.conf - <<EOF
dummynet-anchor customRule
anchor customRule
EOF
) | pfctl -f -
dnctl pipe 1 config bw 50KByte/s delay 500
#dnctl pipe 1 config bw 50KByte/s delay 500 plr 0.05
```

### Filter communication bidirectionally
```
pfctl -a customRule -f - <<EOF
dummynet in quick proto tcp from any port 443 to any pipe 1
dummynet out quick proto tcp from any to any port 443 pipe 1
EOF
```

#### Can use hostname, but not as effective? perhaps bulk of transfer happens elsewhere
```
pfctl -a customRule -f - <<EOF
dummynet out quick proto tcp from any to artifacts.eng.appianci.net port 443 pipe 1
dummynet out quick proto tcp from artifacts.eng.appianci.net port 443 to any pipe 1
EOF
```

### Check status
```
pfctl -s all
pfctl -a customRule -s all
pfctl -s rules
dnctl show
nettop -m tcp -t wifi
```

### Teardown
```
dnctl -q flush
pfctl -f /etc/pf.conf
pfctl -d

pfctl -a customRule -F info
pfctl -F info
```
