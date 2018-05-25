# URL
https://appian.udemy.com/learn-devops-the-complete-kubernetes-course/learn/v4/t/lecture/6018348?start=0

# Install
```
brew install kubectl
brew cask install minikube
brew install kops
```

# Run
```
minikube start
minikube status
kubectl run hello-minikube --image=k8s.gcr.io/echoserver:1.4 --port=8080
kubectl expose deployment hello-minikube --type=NodePort
minikube service hello-minikube --url
```

# Concepts
* A "pod" describes an application running on Kubernetes
* A pod can contain one or more tightly coupled containers, that make up the app
  * Those apps can easily communicate with each other using their local port numbers

# Useful Commands
`kubectl get pod` get information about all running pods
`kubectl describe pod <pod>` describe one pod
You must be running Helm 2.8.2. You are currently on Helm 2.9.1.
`kubectl expose pod <pod> --port=444 --name=frontend` expose the port of a pod (creates a new service)
`kubectl port forward <pod> 8080` port forward the exposed pod port to  your local machine
`kubectl attach <podname> -i` attach to the pod
`kubectl exec <pod> -- command` execute a command on the pod
`kubectl label pods <pod> mylabel=awesome` add a new label to a pod
`kubectl run -i --tty busybox --image=busybox --restart=Never -- sh` run a shell in a pod - very useful for debugging

# Switch contexts
kubectl config get-contexts
kubectl config use-context minikube

# Tutorial
git clone https://github.com/wardviaene/kubernetes-course

## Example first-app
kubectl create -f first-app/helloworld.yml
kubectl get pod
kubectl describe pod nodehelloworld.example.com
kubectl port-forward nodehelloworld.example.com 8081:3000

kubectl expose pod nodehelloworld.example.com --type=NodePort --name nodehelloworld-service
minikube service nodehelloworld-service --url

## Replication Controller
kubectl create -f kubernetes-course/replication-controller/helloworld-repl-controller.yml
kubectl get pods
kubectl scale --replicas=4 -f kubernetes-course/replication-controller/helloworld-repl-controller.yml
kubectl get pods
kubectl get rc
kubectl scale --replicas=1 rc/helloworld-controller
kubectl get pods
kubectl delete rc/helloworld-controller
kubectl get pods

## Deployment
kubectl get deployments
kubectl get rs # replica sets
kubectl get pods --show-labels
kubectl rollout status deployment/helloworld-deployment
kubectl set image deployment/hello-world-deployment k8s-demo=k8s-demo:2
kubectl edit deployment/helloworld-deployment
kubectl rollout history deployment/helloworld-deployment
kubectl rollout undo deployment/helloworld-deployment --to-revision=n

Deployment Objects are preferred over replica sets and replication controllers

## Services
`kubectl expose` creates a new service for the pod so it can be accessed externally
Endpoints:
* ClusterIP: virtual IP only reachable from within cluster (default)
* NodePort: port that is same on each node and is reachable externally
* LoadBalancer: created by cloud provider

By default, service can only run between ports 30000-32767, but can be overridden via the --service-node-port-range= argument to kube-apiserver (in the init scripts)

## Node Labels
`kubectl label nodes node1 hardware=high-spec`
```
nodeSelector:
  hardware: high-spec
```

`kubectl get nodes --show-labels`
kubectl get pods
kubectl describe pod


## Health checks
Two types of health checks:
* Run a command periodically
* Periodic checks on a URL (HTTP)

```
livenessProbe:
  httpGet:
    path: /
    port: 3000
  initialDelaySeconds: 15
  timeoutSeconds: 30
```

kubectl edit deployment/helloworld-deployment

## Secrets
To generate secrets using files:
```
$ echo -n "root" > ./username.txt
$ echo -n "password" > ./password.txt
$ kubectl create secret generic db-user-pass --from-file=./username.txt --from-file=./password.txt
```

Can also be an SSH key or SSL certificate
```
$ kubectl create secret generic ssl-certificate --from-file=ssh-privatekey=~/.ssh/id_rsa --ssl-cer-=sshl-cert=mysslcert.crt
```

Using yaml definitions
```
kind: Secret
data:
  password: cm9vdA== (base64)
  username: cGFzc3dvcmQ= (base64)
```

Create a pod that exposes secrets as environment variables
```
kind: Pod
spec:
  containers:
    env:
      - name: SECRET_USERNAME
        valueFrom:
          secretKeyRef:
            name: db-secret
            key: username
      - name: SECRET_PASSWORD
```

Provide secrets in a file
```
kind: Pod
spec:
  containers:
  - name: k8s-demo
    volumeMounts:
      - name: credvolume
        mountPath: /etc/creds
        readOnly: true
  volumes:
  - name: credvolume
    secret:
      secretName: db-secrets
```

kubectl create -f deployment/helloworld-secrets.yml
kubectl get secrets
kubectl create -f deployment/helloworld-secrets-volumes.yml
kubectl get pods
kubectl describe pod helloworld-deployment-6d4c6b79d9-68t6t
kubectl exec helloworld-deployment-6d4c6b79d9-68t6t -i -t -- /bin/bash
> cat /etc/creds/username
> cat /etc/creds/password

## Web UI
$ minikube dashboard
$ minikube dashboard --url

## Service Discovery
* The DNS service can be used within pods to find other services running on the same cluster
* Multiple containers within 1 pod do not need this service, they can contact each other directly via localhost:port

## Using ConfigMap
```
env:
  - name: DRIVER
    valueFrom:
      configMapKeyRef:
        name: app-config
        key: driver
  - name: DATABASE
```
```
kubectl create configmap nginx-config --from-file=configmap/reverseproxy.conf
kubectl get configmap
kubectl get configmap nginx-config -o yaml
kubectl create -f configmap/nginx.yml
kubectl create -f configmap/nginx-service.yml
minikube service helloworld-nginx-service --url
```

## Ingress
```
kubectl create -f ingress/ingress.yml
kubectl create -f ingress/nginx-ingress-controller.yml
kubectl create -f ingress/echoservice.yml
kubectl create -f ingress/helloworld-v1.yml
kubectl create -f ingress/helloworld-v2.yml
```

## Volumes

