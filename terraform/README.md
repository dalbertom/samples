terraform apply -auto-approve


https://appian.udemy.com/course/learn-devops-infrastructure-automation-with-terraform/learn/lecture/16667146#overview

# Variables
```
variable "myvar" {
  type = string
  default = "hello terraform"
}
variable "mymap" {
  type = map(string)
  default = {
    mykey = "my value"
  }
}
variable "mylist" {
  type = list
  default = [1, 2, 3]
}
```

```
terraform console
var.mylist[0]
element(var.mylist, 0)
slice(var.mylist, 0, 2)
```

Variables can also be placed in in a `*.tfvars` file
