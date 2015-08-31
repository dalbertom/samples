# Packages
The eCryptfs kernel module is available in all Linux kernels since version 2.6.19, released November 30, 2006. eCryptfs is built directly into some kernels (such as Ubuntu), while on others, you may need to:
`modprobe ecryptfs`

## Installation
Debian `apt-get install ecryptfs-utils`

# Encrypting a data directory
To encrypt a single data directory as a user and mount it manually later, run
```
ecryptfs-setup-private --nopwcheck --noautomount
```
The option `--nopwcheck` enables you to choose a passphrase different to the user login passphrase.
The script automatically creates `~/.Private/` and `~/.ecryptfs/`. It will also ask for two passphrases:
## login passphrase
This is the password you will have to enter each time you want to mount the encrypted directory. If you want auto-mounting on login to work, it has to be the same password you use to login to your user account.

## mount passphrase
Used to derive the actual file encryption master key. You should not enter a custom one unless you know what you are doing - instead press Enter to let it auto-generate a secure random one. It will be encrypted using the login passphrase and stored in this encrypted form in `~/.ecryptfs/wrapped-passphrase`. Make sure this file does not get lost, otherwise you can never access your encrypted folder again! You might want to run `ecryptfs-unwrap-passphrase` to see the mount passphrase in unecrypted form, write it down on a piece of paper, and keep it in a safe (or similar), so you can use it to recover your encrypted data in case the *wrapped-passphrase* fileis accidentally lost/corrupted or in case you forget the login passphrase.

# Encrypting a home directory

# Using ecryptfs-simple
Use `ecryptfs-simple` if you just want to use eCryptfs to mount arbitrary directories in the way you can with EncFS. The package is available to be installed as `ecryptfs-simple`.

# References
http://ecryptfs.org/downloads.html
https://wiki.archlinux.org/index.php/ECryptfs#Setup_example_overview
