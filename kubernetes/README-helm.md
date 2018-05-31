# Docs
https://docs.helm.sh/using_helm/#quickstart

# Setup
```console
$ brew install kubernetes-helm
```

# Concepts
* Chart: a helm package, like a Homebrew formula
* Repository: where helm charts are collected
* Release: instance of a chart running in a cluster


# Search

Show all available charts.
```console
$ helm search
```

Find a specific chart.
```console
$ helm search mysql
```

Inspect
```console
$ helm inspect stable/mariadb
```

# Install

