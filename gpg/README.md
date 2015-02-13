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

