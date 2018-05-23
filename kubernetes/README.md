# URL
https://appian.udemy.com/learn-devops-the-complete-kubernetes-course/learn/v4/t/lecture/6018348?start=0

# Install
```
brew install kubectl
brew cask install minikube
```

# Run
```
minikube start
minikube status
kubectl run hello-minikube --image=k8s.gcr.io/echoserver:1.4 --port=8080
kubectl expose deployment hello-minikube --type=NodePort
minikube service hello-minikube --url
```
