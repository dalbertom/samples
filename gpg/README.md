Create Master Key
=================

`gpg --gen-key`

To help with entropy in a virutal machine:
`sudo aptitude install haveged`

Add subkey
----------
`gpg --edit-key <uid>`

List keys
---------
List public keys: `gpg -k`
List secret keys: `gpg -K`

Export keys
-----------
Export public keys: `gpg --export -o filename`
Export secret keys: `gpg --export-secret-keys -o filename`

Create a revocation certificate
-------------------------------
`gpg --output filename-revocation-cert --gen-revoke <uid>`

Exporting the keys
------------------
```
gpg --export-secret-keys --armor <uid> > uid.secret.gpg-key
gpg --export --armor <uid> uid.public.gpg-key
```

Removing master key
-------------------
```
gpg --export-secret-subkeys <uid> > uid.subkeys
gpg --delete-secret-key <uid>
gpg --import uid.subkeys
shred --remove uid.subkeys
```

Importing/Merging (secret) subkey into existing secret key
----------------------------------------------------------
From: https://lists.gnupg.org/pipermail/gnupg-users/2010-August/039307.html
> How to import a subkey into an existing secret key?

Use gpgsplit and recombine. E.g. One machine has a key with 2 encryption subkeys: A and B.

```
mkdir /tmp/key1
cd /tmp/key1
gpg --homedir ~/.gnupg-bak --export-secret-keys <keyid> | gpgsplit
```

The files created by gpgsplit are parts that together make up the whole key. Filenames are a sequence number and after the dash it’s the packet type (the packet type in human-readable form is after the dot)

E.g.
```
000001-005.secret_key: the primary key
000002-013.user_id: the user id
000003-002.sig: the signature that binds the userid and preferences
000004-007.secret_subkey: First secret subkey, encryption key A
000005-002.sig: Signature binding A to primary key
000006-007.secret_subkey: Second secret subkey, encryption key B
000007-002.sig: Signature binding B to primary key
```
`cat * | gpg --list-packets` lists more details about the packets (same order).

```
mkdir /tmp/key2
cd /tmp/key2
gpg --export-secret-keys pass | gpgsplit
```

Then copy over missing secret_subkey and sig file (rename destination names so it does not overwrite other files, and preserve alphabetic order).

The difficulty you initially ran into is that GnuPG will not import a key it already has in the keyring, even if the subkeys are different. So after making a backup of everything, you delete the key already in the keyring. I suggest making the backup before even starting all this, to avoid disasters if you got something wrong.

```
cat * > secret_key.gpg
gpg --delete-secret-and-public-keys pass
gpg --import secret_key.gpg
```
