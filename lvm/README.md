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

# Other
update label
```
e2label /dev/data-ci96/data-ci96 data-ci96
```

# Shrink volume https://wiki.archlinux.org/title/Resizing_LVM-on-LUKS
lvresize -L 26516m /dev/usb-vg/root
pvdisplay # (Allocated PE * PE Size + not usable) * 1024 * 1024
pvresize --setphysicalvolumesize 27786215424B /dev/mapper/usb
pvs -v --segments /dev/mapper/usb
pvmove --alloc anywhere /dev/mapper/usb:14561-14804
pvdisplay
cryptsetup status /dev/mapper/usb # (Allocated PE + unusable extents) * 4 MiB/extent / sector size
cryptsetup resize --size 54272000 /dev/mapper/usb
