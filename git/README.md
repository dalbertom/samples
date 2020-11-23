
# Merge two repositories together

## Using git subtree
cd projectA
git remote add -t master -f projectB git@github.com:appian/projectB
git subtree add -P appian-libraries/projectB/ projectB/master

## using git merge
cd projectA
git remote add -t master -f projectB git@github.com:appian/projectB
git merge -s ours --no-commit --allow-unrelated-histories projectB/master
git read-tree --prefix=appian-libraries/projectB/ -u projectB/master
git commit -m 'Merge projectB/master'
