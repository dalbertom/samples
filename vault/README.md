# Authenticate to Vault via LDAP
`vault auth -method=ldap username=$USER`

# get policy list
`vault policy list`

# get roles
`vault list auth/token/roles`

# get groups for user
`vault read auth/ldap/users/$USER`
