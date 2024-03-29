# Nix

## Install
https://nix.dev/install-nix

```
curl -L https://nixos.org/nix/install | sh
```

## Ad hoc shell environments

```
nix-shell -p cowsay lolcat
cowsay Hello, Nix! | lolcat
```

### Search for packages
https://search.nixos.org/

### Towards reproducibility
```
nix-shell -p git --run "git --version" --pure -I nixpkgs=https://github.com/NixOS/nixpkgs/archive/2a601aafdc5605a5133a2ca506a34a3a73377247.tar.gz
```

### Garbage collection
```
nix-collect-garbage
```

## Reproducible interpreted scripts

```
#!/usr/bin/env nix-shell
#! nix-shell -i bash --pure
#! nix-shell -p bash cacert curl jq python3Packages.xmljson
#! nix-shell -I nixpkgs=https://github.com/NixOS/nixpkgs/archive/2a601aafdc5605a5133a2ca506a34a3a73377247.tar.gz

curl https://github.com/NixOS/nixpkgs/releases.atom | xml2json | jq .
```

## Declarative shell environments with `shell.nix`

### Temporary shell
```
nix-shell -p git neovim nodejs
```

### A basic `shell.nix` file
```
nix-shell
```

Set `GIT_EDITOR` to use `nvim` from the shell environment
Use `shellHook` to run some commands before entering the shell environment

## Nix language basics

### Interactive evaluation
```
nix repl
1 + 2
{ a.b.c = 1; }
:p { a.b.c = 1; }
```

### Evaluating Nix files
Use `nix-instantiate --eval` to evaluate the expression in a Nix file

```
nix-instantiate --eval file.nix
nix-instantiate --eval [default.nix]
```

### Recursive attribute set
```
rec {
    one = 1;
    two = one + 1;
    three = two + 1;
}
```

### let expression or let binding
```
let
 a = 1;
in
a + a
```
```
let
 b = a + 1;
 a = 1;
in
a + b
```
The bindings have local scope. Only expressions within the `let` expression itself can access the newly declared names.

### attribute access with `.`
```
let
  attrset = { x = 1; };
in
attrset.x
```
```
{ a.b.c = 1; }
```

### the `with` expression
allows access to attributes without repeatedly referencing their attribute set
```
let
  a = {
    x = 1;
    y = 2;
    z = 3;
  };
in
with a; [ x y z ]
```

### `inherit` is shorthand for assigning the value of a name from an existing scope to the same name in a nested scope
It is for convenience to avoid repeating the same name multiple times
```
let
  x = 1;
  y = 2;
in
{
  inherit x y;
}
```

It is also possible to `inherit` names from a specific attribute set with parenthesis
```
let
  a = { x = 1; y = 2; };
in
{
  inherit (a) x y;
}
```
The fragment `inherit (a) x y;` is equivalent to `x = a.x; y = a.y;`

`inherit` also works inside `let` expressions
```
let
  inherit ({ x = 1; y = 2; }) x y;
in [ x y ]
```

### String interpolation `${ ... }`
Previously known as "antiquotation"
```
let
  name = "Nix";
in
"hello ${name}"
```

```
let
  x = 1;
in
"${x} + ${x} = ${x + x}"
```

Interpolated expressions can be arbitrarily nested.
```
let
  a = "no";
in
"${a + " ${a + " ${a}"}"}"
```

### File system paths
```
/absolute/path
```
```
./relative/path
relative/path
./.
../.
```

#### lookup paths
Also known as the "angle bracket syntax"
```
<nixpkgs>
```
This is a file system path that depends on the value of `builtins.nixPath`

```
<nixpkgs/lib>
```
Avoid lookup paths in production code, as they are impurities which are not reproducible

### Indented strings
Also known as "multi-line strings"

```
''
multi
line
string
''
```
Equal amounts of prepended whitespace are trimmed from the result.
```
''
  one
    two
      three
''
```

### Functions
A function takes exactly one argument

Single argument
```
x: x + 1
```

Multiple arguments via nesting
```
x: y: x + y
```

Attribute set argument
```
{ a, b }: a + b
```

With default values
```
{ a, b ? 0 }: a + b
```

With additional attributes allowed
```
{ a, b, ...}: a + b
```

Named attribute set argument
```
args@{ a, b, ... }: a + b + args.c
```
or
```
{ a, b, ... }@args: a + b + args.c
```

Anonymous functions are called _lambda_

```
let
  f = x: x + 1;
in f
```

#### Calling functions
Also known as "function application"
```
let
  f = x: x + 1;
in f 1
```
```
let
  f = x: x.a;
in
f { a = 1; }
```

Since function and argument are separated by white space, sometimes parenthesis are required to achieve the desired result.
```
(x: x + 1) 1
```

List elements are also separated by white space, therefore the following are different:

Apply `f` to `a`, and put the result in a list. The resulting list has one element.
```
let
  f = x: x + 1;
  a = 1;
in [ (f a) ]
```

Put `f` and `a` in a list. The resulting list has two elements.
```
let
  f = x: x + 1;
  a = 1;
in [ f a ]
```

#### Multiple arguments
Also known as "curried functions"

```
x: y: x + y
```
The above function is equivalent to
```
x: (y: x + y)
```

Example
```
let
  f = x: y: x + y;
in
f 1
```
```
let
  f = x: y: x + y;
in
f 1 2
```

#### Attribute set argument
Also known as "keyword arguments" or "destructuring"
Nix functions can be declared to require an attribute set with specific structure as argument.
This is denoted by listing the expected attribute names separated by commas and enclosed in braces
Example:
```
let
  f = { a, b }: a + b;
in
f { a = 1; b = 2; }
```

Counter-example: Error: called with unexpected argument 'c'
```
let
  f = { a, b }: a + b;
in
f { a = 1; b = 2; c = 3; }
```

#### Default values
Also known as "default arguments"
Example:
```
let
  f = {a, b ? 0}: a + b;
in
f { a = 1; }
```

#### Additional attributes
Example:
```
let
  f = {a, b, ...}: a + b;
in
f { a = 1; b = 2; c = 3; }
```

#### Named attribute set argument
Also known as "@ pattern", "@ syntax", or "'at' syntax"
An attribute set argument can be given a name to be accessible as a whole.
This is denoted by prepending or appending the name to the attribute set argument, separated by the at sign.
Example
```
{ a, b, ...}@args: a + b + args.c
```
or
```
args@{a, b, ...}: a + b + args.c
```
```
let
  f = { a, b, ...}@args: a + b + args.c;
in
f { a = 1; b = 2; c = 3; }
```

### Function libraries
In addition to the [built-in operators](https://nixos.org/manual/nix/stable/language/operators.html) there are two widely used libraries.

#### builtins
Also known as "primitive operations" or "primops"

```
builtins.toString
```

#### import
`import` takes a path to a Nix file, reads it to evaluate the contained Nix expression, and returns the resulting value. If the path points to a directory, the file `default.nix` in that directory is used instead.

```
import ./file.nix
```

#### pkgs.lib
The `nixpkgs` repository contains an attribute set called `lib`, which provides a large number of useful functions

```
let
  pkgs = import <nixpkgs> {};
in
pkgs.lib.strings.toUpper "search paths considered harmful"
```

### Impurities
One impurity in the Nix language that is relevant: reading files from the file system as _build inputs_
Build inputs are files that derivations refer to in order to describe how to derive new files.
When run, a derivation will only have access to explicitly declared build inputs.
The only way to specify build inputs in the Nix language is explicitly with:
* File system paths
* Dedicated functions

### Paths
Whenever a file system path is used in string interpolation, the contents of that file are copied to a special location in the file system, the _Nix store_, as a side effect.
The evaluated string the contains the Nix store path assigned to that file.

### Fetchers
Files to be used as build inputs do not have to come from the file system
The Nix language provides built-in impure functions to fetch files over the network during evaluation:
* builtins.fetchUrl
* builtins.fetchTarball
* builtins.fetchGit
* builtins.fetchClosure
They evaluate to a file system path in the Nix store.

### Derivations
