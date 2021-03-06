# Sat May  2 08:31:16 EDT 2020 [How to compile and install Linux Kernel 5.6.9 from source code](https://www.cyberciti.biz/tips/compiling-linux-kernel-26.html)

1. Grab latest kernel from kernel.org
```
wget https://cdn.kernel.org/pub/linux/kernel/v5.x/linux-5.6.9.tar.xz
unxz -v linux-5.6.9.tar.xz
```
1. Verify kernel
```
wget https://cdn.kernel.org/pub/linux/kernel/v5.x/linux-5.6.9.tar.sign
gpg --verify linux-5.6.9.tar.sign
gpg --recv-keys 647F28654894E3BD457199BE38DBBDC86092693E
gpg --verify linux-5.6.9.tar.sign
```
1. Untar the kernel tarball
```
tar xvf linux-5.6.9.tar
```
1. Copy existing Linux kernel config
```
cd linux-5.6.9
cp -v /boot/config-$(uname -r) .config
```
1. Install required compilers and other tools
```
sudo apt-get install -y build-essential libncurses-dev bison flex libssl-dev libelf-dev
#sudo yum group install "Development Tools"
#sudo yum install ncurses-devel bison flex elfutils-libelf-devel openssl-devel
```
1. Configuring the kernel
```
make menuconfig
# make xconfig
# make gconfig
```
1. Compile the kernel
```
make -j $(nproc)
```
1. Install the Linux kernel modules
```
sudo make modules_install
```
1. Install the Linux kernel
```
sudo make install
```
1. Update grub config
```
# These commands are optional as make install does everything but included for historical reasons
sudo update-initramfs -c -k 5.6.9
sudo update-grub
```

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

## Resize encrypted partition

growpart /dev/nvme1n1 1
lsblk
```
NAME        MAJ:MIN RM  SIZE RO TYPE  MOUNTPOINT
nvme0n1     259:2    0   20G  0 disk
└─nvme0n1p1 259:3    0   20G  0 part  /
nvme1n1     259:0    0  100G  0 disk
└─crypt1    253:1    0  100G  0 crypt /vol/crypt1
nvme2n1     259:1    0   10G  0 disk
└─tmp       253:0    0   10G  0 crypt /mnt
```

cryptsetup resize crypt1
xfs_growfs /dev/mapper/crypt1


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

self-sign a certificate
https://serverfault.com/questions/224122/what-is-crt-and-key-files-and-how-to-generate-them
```
openssl genrsa 2048 > host.key
chmod 400 host.key
openssl req -new -x509 -nodes -sha256 -days 365 -key host.key -out host.cert
```

## bash

### recall history
You can use `fc` to recall and edit previous commands
* `fc` will open an editor with the last command
* `fc -l` will show the commands
* `fc <number>` will edit the specified command

Using history command
* to print a n old command: ![line-number]:p

### variable expansion
Unset environment variables that start with a prefix
`unset ${!DOCKER*}`

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


