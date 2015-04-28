# LVM, Demystified

Based on http://www.linuxjournal.com/content/lvm-demystified

## Step 1. Create Physical Volumes
Configure drives as potential candidates to be added to a volume group:
```
pvcreate /dev/sdb
pvcreate /dev/sdc
```
Use `pvdisplay` or `pvscan` to show the status of any existing LVM Physical Volumes

## Step 2. The Volume Group
Create a volume group using the two physical volumes that were created
```
vgcreate my_volume_group /dev/sdb /dev/sdc
```

## Step 3. Logical Volumes
To create logical volumes:
```
lvcreate -L 3G -n 3gig my_volume_group
```

Then run `lvdisplay` to see the changes.

After creating the logical volumes, they can be used as block devices. To be able to mount it, you need to add a filesystem:
```
mkfs.ext4 /dev/my_volume_group/3gig
mount -t ext4 /dev/my_volume_group/3gig /mnt
```

## Other: Loopback filesystem
```
dd if=/dev/zero of=/mnt/loopback.img bs=1M count=10000
mkfs.ext4 /mnt/loopback.img
mkdir /mnt/loopback
mount -o loop /mnt/loopback.img /mnt/loopback
umount /mnt/loopback
losetup /dev/loop2 /mnt/loopback.img
pvscan
vgscan
vgextend work /dev/loop2
lvextend /dev/work/repo /dev/loop2
resize2fs /dev/work/repo
```

Save as `/etc/init/losetup.conf`
```
description     “Setup loop devices after filesystems are mounted”

start on mounted MOUNTPOINT=/
task
exec losetup /dev/loop2 /mnt/loopback.img
```
