# Authenticate to Vault via LDAP
`vault auth -method=ldap username=$USER`

# get policy list
`vault policy list`

# get roles
`vault list auth/token/roles`

# get groups for user
`vault read auth/ldap/users/$USER`

# Temporarily share credentials

## Using cubbyhole directly
This allows for 3 uses: e.g. 1 write, 2 read
```
vault token create -field=token -policy=default -use-limit=3 -display-name='description-of-the-token' > token.txt
VAULT_TOKEN=$(cat token.txt) vault write cubbyhole/mysecret username=hello password=world
VAULT_TOKEN=$(cat token.txt) vault read cubbyhole/mysecret
```

## Using wrapping token (which uses cubbyhole under the hood)
This is good for one time use, or 5 minute expiration by default
```
vault write -field=wrapping_token sys/wrapping/wrap username=hello password=world > token.txt
vault unwrap $(cat token.txt)
```
