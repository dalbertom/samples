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
