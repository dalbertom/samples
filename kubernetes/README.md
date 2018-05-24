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
