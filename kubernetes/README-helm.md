# Docs
https://docs.helm.sh/using_helm/#quickstart

# Setup
```console
$ brew install kubernetes-helm
```

To install version 2.7.2
```console
$ brew log kubernetes-helm
$ brew install https://github.com/Homebrew/homebrew-core/raw/607a5f63620b8ca1ba169222874827f00e4f77de/Formula/kubernetes-helm.rb
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

