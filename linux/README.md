# Useful Linux Commands

## Create new file system and mount the device
E.g. assuming the device is /dev/sdb
```
mkfs.ext4 /dev/sdb
mount /dev/sdb /mnt
```

## NFS
Install necessary client software
```
sudo apt-get install -y nfs-common
```

Find locations exported by the NFS server
```
showmount -e <server-ip-address>
```

Mount
```
sudo mount <server-ip-address>:/path/to/share /path/to/mount
cat /proc/mounts
```

Auto mount in /etc/fstab
```
<server-ip-address>:/path/tos/share /path/to/mount nfs auto 0 0
```

## openssl
Check certificate expiration time
```
openssl x509 -in wildcard.crt -noout -text -subject | less
```

Encrypt a file using public ssh rsa key
https://superuser.com/questions/576506/how-to-use-ssh-rsa-public-key-to-encrypt-a-text
```
ssh-keygen -f ~/.ssh/id_rsa.pub -e -m PKCS8 > id_rsa.pem.pub
openssl rsautl -encrypt -pubin -inkey id_rsa.pem.pub -in myMessage.txt -out myEncryptedMessage.txt
openssl rsautl -decrypt -inkey ~/.ssh/id_rsa -in myEncryptedMessage.txt -out myDecryptedMessage.txt
```

## bash

### recall history
You can use `fc` to recall and edit previous commands
* `fc` will open an editor with the last command
* `fc -l` will show the commands
* `fc <number>` will edit the specified command

Using history command
* to print a n old command: ![line-number]:p

## Hardware troubleshooting
`lsmod` - show status of modules in the Linux Kernel
`lshw` - list hardware
`lshw -C network`
`lspci` - list all PCI devices

### xargs
Run stuff in parallel with xargs, e.g.
```
cat /tmp/todo | grep -v -f /tmp/done | xargs -n 2 -P 16 sh -c 'if [ 0 -ne $(curl -s --netrc-optional $1/consoleText | grep -c "Unable to instantiate") ]; then echo "$0 $1"; fi; echo $1 >> /tmp/done' | tee -a /tmp/output
```

## ssh
Fix issue with "Host key verification failed"
```
ssh-keyscan -H -t rsa hostname  >> ~/.ssh/known_hosts
```

## gnupg gpg
GPG does not have enough entropy
This happens when trying to create a gpg key inside a VM
Install rng-tools: `sudo apt-get install rng-tools`
Then run `sudo rngd -r /dev/urandom` before generating key.

cat /proc/sys/kernel/random/entropy_avail

## iptables

https://www.howtogeek.com/177621/the-beginners-guide-to-iptables-the-linux-firewall/

Three different chains:
* input: controls behavior of incoming connections
* forward: used for incoming connections that are not delivered locally. Used for routing or NATing
* output: used for outgoing connections

To accept connections by default:
```
iptables --policy INPUT ACCEPT
iptables --policy OUTPUT ACCEPT
iptables --policy FORWARD ACCEPT
```

To deny all connections, use DROP instead of ACCEPT

### Connection-specific responses
* Accept: Allow the connection
* Drop: Act like it never happened. Best if you don't want the source to realize your system exists
* Reject: Don't allow, but send back error.

### Allowing or Blocking Specific Connections
iptables -A appends rule at the end of the chain
iptables -I inserts rule at the beginning of the chain
#### Examples
Block connections from IP: `iptables -A INPUT -s 10.10.10.10 -j DROP`
Block connections from range: `iptables -A INPUT -s 10.10.10.0/24 -j DROP` or `iptables -A INPUT -s 10.10.10.0/255.255.255.0 -j DROP`
Block connections to a specific port: `iptables -A INPUT -p tcp --dport ssh -s 10.10.10.10 -j DROP`

### Connection States
Allow ssh connections from 10.10.10.10 but disallow connections to 10.10.10.10
```
iptables -A INPUT -p tcp --dport ssh -s 10.10.10.10 -m state --state NEW,ESTABLISHED -j ACCEPT
iptables -A OUTPUT -p tcp --sport 22 -d 10.10.10.10 -m state --state ESTABLISHED -j ACCEPT
```

### Save changes
* Ubuntu: `sudo /sbin/iptables-save`
* RedHat/CentOS: `/sbin/service iptables save` or `/etc/init.d/iptables save`

### Other commands:
* List: `iptables -L`
* Flush (clear all rules): `iptables -F`
