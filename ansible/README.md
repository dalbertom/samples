# Ansible commands

```
ansible <group> -m <module> -a <args>
ansible all -m ping
ansible webservers -a "cat /etc/hosts" -s
ansible all -a "free -m"
ansible webservers -m setup
ansible all -m shell -a "ifconfig | grep inet" -s
ansible webservers -m service -a "name=nginx state=restarted" -s --forks=1
cd roles && ansible-galaxy init nginx
```
