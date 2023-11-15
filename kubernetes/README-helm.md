# Docs
Helm v3.13.0

# Setup
```console
$ brew install helm
```

# Concepts
* Chart: a helm package, like a Homebrew formula
* Repository: where helm charts are collected
* Release: instance of a chart running in a cluster

# https://helm.sh/docs/intro/quickstart/

## Initialize a Helm Chart Repository
Check [Artifact Hub](https://artifacthub.io/packages/search) for available Helm chart repositories
```
helm repo add bitnami https://charts.bitnami.com/bitnami
helm search repo bitnami
```

## Install an Example Chart
```
helm repo update
helm install bitnami/mysql --generate-name
```

## Learn about Releases
```
helm list
helm status mysql-1700006295
```

## Uninstall a Release
```
helm uninstall mysql-1700006295
helm uninstall --keep-history mysql-1700006295
```

# Search

```
helm search hub # searches Artifact Hub
helm search repo # searches repositories that were added (local operation)
```

```
helm search hub wordpress
helm repo add brigade https://brigadecore.github.io/charts
helm search repo brigade
```

# Install
```
helm install happy-panda bitnami/wordpress
```

## Status
```console
$ helm status happy-panda
```

## Customizing the Chart before installing
```console
$ helm show values bitnami/wordpress > values.yaml
# edit values.yaml
$ helm install -f values.yaml bitnami/wordpress happy-panda
# edit values.yaml
$ helm upgrade -f values.yaml happy-panda bitnami/wordpress
$ helm get values happy-panda
```

## History and Rollback
```console
$ helm history happy-panda
$ helm rollback happy-panda 1
```

## Uninstall
```console
$ helm uninstall happy-panda
$ helm uninstall --keep-history happy-panda
$ helm list --uninstalled
$ helm list --all
```

# Create your own charts
```console
$ helm create deis-workflow
$ helm lint
$ helm package deis-workflow
$ helm install deis-workflow ./deis-workflow-0.1.0.tgz
```
