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
