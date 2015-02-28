# How to Use DM-Crypt to Create an Encrypted Volume on an Ubuntu VPS

Based on tutorial:
https://www.digitalocean.com/community/tutorials/how-to-use-dm-crypt-to-create-an-encrypted-volume-on-an-ubuntu-vps

## Install dm-crypt tools
```
apt-get update
apt-get install cryptsetup
```

## Create Non-sparse empty file

### Alternative 1
To create a 512MB file on our home directory, we can type:
```
fallocate -l 512M /root/test1
```

### Alternative 2
Using the `dd` command we can write zeroes to the entire area that we are provisioning to our file by using the `/dev/zero` pseudo-device.
```
dd if=/dev/zero of=/root/test2 bs=1M count=512
```

Instead of `/dev/zero` you can use `/dev/urandom` or `/dev/random` to mimic encrypted data, but it takes longer to generate.

## Creating a dm-crypt LUKS Container in the File
Before formatting the file, we should create a LUKS partition. LUKS (Linux Unified Key Setup), is a standard for disk encryption. This is the basic layer that all of our other data will sit on top of.

```
cryptsetup -y luksFormat /root/test1
```

We can check the file to see it is known as a LUKS encrypted file:
```
file /root/test1
```

To open the container:
```
cryptsetup luksOpen /root/test1 volume1
```

This opens the LUKS device, and maps it to the name that we supplied, `volume1`, in this case, creates a file at `/dev/mapper/volume`

## Creating and Mounting the File System

To use the standard ext4 filesystem:
```
mkfs.ext4 -j /dev/mapper/volume1
```

Next step is to mount the device:
Create mount location
```
mkdir /mnt/files
```

Mount the file system
```
mount /dev/mapper/volume1 /mnt/files
```

See the file is part of available filesystems:
```
df -h
```

## Unmounting the Filesystem and Closing the LUKS Container
Unmount the device
```
umount /mnt/files
```

Close the volume to secure its contents
```
cryptsetup luksClose volume1
```

## Straight Forward Usage Procedure
To separate initial creation procedures from daily usage:
* Open the LUKS file: `cryptsetup luksOpen /root/test1 volume1`
* Mount the device: `mount /dev/mapper/volume1 /mnt/files`
* Unmount the device: `umount /mnt/files`
* Close the LUKS file to encrypt data: `cryptsetup luksClose volume1`
