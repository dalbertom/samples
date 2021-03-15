terraform apply -auto-approve


https://appian.udemy.com/course/learn-devops-infrastructure-automation-with-terraform/learn/lecture/16667146#overview
https://github.com/wardviaene/terraform-course

# Reference Documentation
* https://www.terraform.io/downloads.html
* AWS Resources: https://www.terraform.io/docs/providers/aws/
* List of providers: https://www.terraform.io/docs/providers/index.html
* List of AMIs for ubuntu: https://cloud-images.ubuntu.com/locator/ec2/

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
var.myvar
"${var.myvar}"
var.mymap.mykey
var.mymap["mykey"]
"${var.mymap["mykey"]}"
var.mylist[0]
element(var.mylist, 0)
slice(var.mylist, 0, 2)
```

Variables can also be placed in in a `*.tfvars` file

```
provider "aws" {
}
```

# Provision Software
## File uploads
```
provisioner "file" {
  source = "app.conf"
  destination = "/etc/myapp.conf"
}
```

### uses ssh, so it should use a connectionn
```
resource "aws_instance" "example" {
  ami = "${lookup(var.AMIS, var.AWS_REGION)}"
  instance_type = "t2.micro"
  provisioner "file" {
    source = "app.conf"
    destination = "/etc/myapp.conf"
    connection {
      user = "${var.instance_username}"
      password = "${var.instance_password}"
    }
  }
}
```
Rather than using passwords, typically use ssh keypair
```
resource "aws_key_pair" "david-key" {
  key_name = "mykey"
  public_key = "ssh-rsa my-public-key"
}

resource "aws_instance" example" {
  ami = "${lookup(var.AMIS, var.AWS_REGION)}"
  instance_type = "t2.micro"
  key_name = "${aws_key_pair.mykey.key_name}"
  provisioner "file" {
    source = "app.conf"
    destination = "/etc/myapp.conf"
    connection {
      user = "${var.instance_username}"
      private_key = "${file(${var.path_to_private_key})}"
    }
  }
}
```

## Execute a script
```
provisioner "remote-exec" {
  inline = [
    "chmod +x /opt/script.sh",
    "/opt/script.sh arguments"
  ]
}
```

# Output
```
output "ip" {
  value = aws_instance_example.private_ip
}
```

# Remote state
backend.tf
```
terraform {
  backend "consul" {
    address = "dmeo.consul.io" #hostname of consul cluster
    path = "terraform/myproject"
  }
}
```
```
# This needs aws credentials in aws configure, it cannot use variables
terraform {
  backend "s3" {
    bucket = "mybucket"
    key = "terraform/myproject"
    region = "eu-west-1"
  }
}
```

It's possible to specify a read-only remote store directly in the .tf file
```
data "terraform_remote_state" "aws-state" {
  backend = "s3"
  config {
    bucket = "terraform-state"
    key = "terraform.tfstate"
    access_key = "${var.AWS_ACCESS_KEY}"
    secret_key = "${var.AWS_SECRET_KEY}"
    region = "${var.AWS_REGION}"
  }
}
```

# Datasources
```
data "aws_ip_ranges" "european_ec2" {
  regions = ["eu-west-1", "eu-central-1"]
  services = ["ec2"]
}

resource "aws_security_group" "from_europe" {
  name = "from_europe"
  ingress {
    from_port = "443"
    to_port = "443"
    protocol = "tcp"
    cidr_blocks = ["${data.aws_ip_ranges.european_ec2.cidr_blocks}"]
  }
  tags {
    CreateDate = "${data.aws_ip_ranges.european_ec2.create_date}"
    SyncToken = "${data.aws_ip_ranges.european_ec2.sync_token}"
  }
}
```

# Template Provider
```
data "template_file" "my-template" {
  template = "${file("templates/init.tpl")}"
  vars {
    myip = "${aws_instance.database1.private_ip}"
  }
}

resource "aws_instance" "web" {
  # ...
  user_data = "${data.template_file.my-template.rendered}"
}
```

# Modules
* They make the terraform code more organized
* Use third party modules
* Reuse parts of your code

## Use a module from git
```
module "module-example" {
  source = "github.com/wardviaene/teerraform-module-example"
}
```

## Use a module from a local folder
```
module "module-example" {
  source = "./module-example"
}
```

## Pass arguments to the module
```
module "module-example" {
  source = "./module-example"
  region = "us-east-1'
  ip-range = "10.0.0.0/8"
  cluster-size = "3"
}
```

# Terraform command overview
apply: applies state
destroy: destroys managed state
fmt: formats files to canonical format and style
get: download and update modules
graph: create visual representation of configuration or execution path
import ADDRESS ID: find infrastructure resource identified by ID and import the state with resource id ADDRESS
output NAME: output any resources
plan: show changes that would be made to infrastructure
refresh: refreshes remote state. Identifies differences between state file and remote state
remote: configure state storage
show: show human readable output from state or plan
state: advanced state management, e.g. rename a resource with terraform state mv
taint: manually mark resource as tainted to destroy and re-create a resource
validate: validate syntax
untaint: undo a taint

# Terraform with AWS
* EC2-Classic is one big network where all AWS customers could launch their instances in (NOT RECOMMENDED)
* Smaller to medium setups, one VPC (per region) will be suitable for your needs
* An instance launched in one VPC can never communicate with an instance in another VPC using their private IP addresses
** They could communicate still, but using their public IP (NOT RECOMMENDED
** You can also link 2 VPCs, called peering

## IAM - Identity and Access Management
* Users can have Groups
* Users can authenticate, optionally using a token or use an access key and secret key
* Roles can give users/services (temporary) access

E.g. create a role mybucket-access and assign to an EC2 instance at boot time

* IAM roles only work on EC2 instances

```
resource "aws_iam_group" "administrators" {
  name = "administrators"
}
resource "aws_iam_policy_attachment" "administrators-attach" {
  name = "admininstrators-attach"
  groups = ["${aws_iam_group.administrators.name}"]
  policy_arn = "arn:aws:iam::aws:policy/AdministratorAccess"
}
```

* You can also create your own policy
```
resource "aws_iam_group" "administrators" {
  name = "administrators"
}
resource "aws_iam_group_policy" "my_developer_policy" {
  name = "my_administrators_policy"
  group = "${aws_iam_group.administrators.id}"
  policy = <<EOF
{
  "Version":"2012-10-17",
  "Statement": [
    {
      "Effect": "Allow",
      "Action": "*",
      "Resource": "*"
    }
  ]
}
EOF
}
```

* Create a user and attach it to a group
```
resource "aws_iam_user" "admin1" {
  name = "admin1"
}
resource "aws_iam_user" "admin2" {
  name = "admin2"
}
resource "aws_iam_group_membership" "administrators-users" {
  name = "administrators-users"
  users = [
    "${aws_iam_user.admin1.name}",
    "${aws_iam_user.admin2.name}",
  ]
  group = "${aws_iam_group.administrators.name}"
}
```

## IAM Roles
Creating a role that we want to attach to an EC2 instance
```
resource "aws_iam_role" "s3-mybucket-role" {
  name = s3-mybucket-role"
  assume_role_policy = <<EOF
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Action": "sts:AssumeRole",
      "Principal": {
        "Service": "ec2.amazonaws.com"
      },
      "Effect": "Allow",
      "Sid": ""
    }
  ]
}
EOF
}
resource "aws_iam_instance_profile" "s3-mybucket-role-instanceprofile" {
  name = "s3-mybucket-role"
  roles = ["${aws_iam_roles.s3-mybucket-role.name}"]
}
```
Attach the role to an EC2 instance
```
resource "aws_instance" "example" {
  ami = "${lookup(var.AMIS, var.AWS_REGION)}"
  instance_type = "t2.micro"
  subnet_id = "${aws_submnet.main-public-1.id}"
  vpc_security_group_ids = ["${aws_security_group.allow-ssh.id{"]
  key_name = "${aws_key_pair.mykeypair.key_name}"

  #role
  iam_instance_profile = "${aws_iam_instance_profile.s3-mybucket-role-instanceprofile.name}"
}
```

Creating the bucket
```
resource "aws_s3_bucket" "b" {
  bucket = "mybucket-c29df1"
  acl = "private"
  tags {
    Name = "mybucket-c29df1"
  }
}
```

Add permissions using a policy document
```
resource "aws_iam_role_policy" "s3-mybucket-role-policy" {
  name = "s3-mybucket-role-policy"
  role = "${aws_iam_role.s3-mybucket-role.id}"
  policy = <<EOF
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Effect": "Allow",
      "Action": [
        "s3:*"
      ],
      "Resource": [
        "arn:aws:s3:::mybucket-c29df1",
        "arn:aws:s3:::mybucket-c29df1/*"
      ]
    }
  ]
}
EOF
}
```

## Load Balancer
* The ELB can be used as SSL terminator
 * If using ELBs, Amazon can provide SSL Certificates for you
* ELBs can spread over multiple Availability Zones for higher fault tolerance
* ELB is comparable to a nginx/haproxy, but then provided as a service

* Two types of load balancers
 * Classic Load Balancer (ELB)
  * Routes traffic based on network information
  * e.g. forwards all traffic from port 80 to 8080
 * Application Load Balancer (ALB)
  * routes traffic based on application level information
  * e.g. can route /api and /website to different EC2 instances

### Classic Load Balancer (ELB)
```
resource "aws_elb" "my-elb" {
  name = "my-elb"
  subnets = ["${aws_subnet.main-public-1.id}", "${aws_subnet.main-public-2.id}"]
  security_groups = ["${aws_security_group.elb-securitygroup.id}"]
  listener {
    instance_port = 80
    instance_protocol = "http"
    lb_port = 80
    lb_protocol = "http"
  }
  health_check {
    healthy_threshold = 2
    unhealthy_threshold = 2
    timeout = 3
    target = "HTTP:80/"
    interval = 30
  }
  instances = ["${aws_instance.example-instance.id}"] #optional, you can also attach an ELB to an autoscaling group
  cross_zone_load_balancing = true
  connection_draining = true
  connection_draining_timeout = 400
  tags {
    Name = "my-elb"
  }
}
```

### Application Load Balancer
```
resource "aws_alb" "my-alb" {
  name = "my-alb"
  subnets = ["${aws_subnet.main-public-1.id}", "${aws_subnet.main-public-2.id}"]
  security_groups = ["${aws_security_group.elb-securitygroup.id}"]
  tags {
    Name = "my-alb"
  }
}
```
Then specify a target group
```
resource "aws_alb_target_group" "frontend-target-group" {
  name = "alb-target-group"
  port = 80
  protocol = "HTTP"
  vpc_id = "${aws_vpc.main.id}"
}
```
Attach instances to the target group
```
resource "aws_alb_target_group_attachment" "frontend-attachment-1" {
  target_group_arn = "${aws_alb_target_group.frontend-target-group.arn}"
  target_id = "${aws_instance.example-instance.id}"
  port = 80
}
```
Specify the listeners separately
```
resource "aws_alb_listener" "frontend-listeners" {
  load_balancer_arn = "${aws_alb.my-alb.arn}"
  port = "80"
  default_action {
    target_group_arn = "${aws_alb_target_group.frontend-target-group.arn}"
    type = "forawrd"
  }
}
```

# Advanced Terraform Usage
## Interpolation
String variable
```
var.name
  ${var.SOMETHING}
```
Map variable
```
var.MAP["key"]
  ${var.AMIS["us-east-1"]}
  ${lookup(var.AMIS, var.AWS_REGION)}
```
List variable
```
var.LIST, var.LIST[i]
  ${var.subnets[i]}
  ${join(",", var.subnets)}
```
Outputs of a module
```
module.NAME.output
  ${module.aws_vpc.vpcid}
```
Count information
```
count.FIELD
  ${count.index}
```
Path information
```
path.TYPE
  path.cwd
  path.module
  path.root
```
Meta information
```
terraform.FIELD
  terraform.env
```
Math
```
${2 + 3 * 4}
```

## Conditionals
condition ? trueval : falseval
```
resource "aws_instance" "myinstance" {
  countn = "${var.env == "prod" ? 2 : 1}"
}
```

## Built-In Functions
* ${file("mykey.pub")}

| Function | Description | Example |
|---------------------------------|-------------|---------|
| basename(path)                  | returns filename of path | basename("/home/ubuntu/file.txt") |
| coalesce(string1, strin2)       | returns first non-empty value    | coalesce("", "", "hello")                       |
| element(list, index)            | a single element from a list     | element(module.vpc.public_subnets, count.index) |
| format(format, args)            | format string                    | format("server-%03d", count.index + 1)          |
| formatlist(format, args)        | format list                      |                                                 |
| index(list, element)            | finds an index of an element     | index(aws_instance.foo.\*.tags.Env, "prod")     |
| join(delim, list)               | joins a list together            | join(",", var.AMIS)                             |
| list(item1, item2)              | creates a new list               | join(":", list("a", "b", "c"))                  |
| lookup(map, key, [default])     | performs  lookup on a map        | lookup(map("k", "v"), "k", "not found")         |
| lower(string)                   | lowercase of string              | lower("Hello")                                  |
| map(key, value)                 | returns a new map                | map("k", "v", "k2", "v2")                       |
| merge(map1, map2, ...)          | union of map                     | merge(map("k", "v"), map("k2", "v2"))           |
| replace(string, search, replace | search and replace on string     | replace("aaab", "a", "b")                       |
| slice(list, index, count)       | slice list                       | slice(list("a", "b", "c"), 0, 2) -> ["a", "b"]  |
| split(delim, string)            | split string into a list         | split(",", "a,b,c,d")                           |
| substr(string, offset, length)  | extract susbstring               | substr("abcde", -3, 3) -> "cde"                 |
| timestamp()                     | timestamp                        |                                                 |
| upper(string)                   | uppercase string                 |                                                 |
| uuid()                          | creates a UUID                   |                                                 |
| values(map)                     | returns values of a map          |                                                 |

## For and For Each Loops
new in terraform 0.12 -> for and for_each
e.g.
[ for s in ["this is a", "list"] : upper(s) ]

You can loop over a list or a map

* For loops are typically used when assigning a value to an argument
  * security_groups = ["sg-12345", "sg-567"]

For_each loops are not used when assigning a value to an argument, but rather to repeat nested blocks
```
resource "aws_security_group" "example" {
  name = "example"
  dynamic "ingress" {
    for_each = [ 22, 443 ]
    content {
      from_port = ingress.value
      to_port = ingress.value
      protocol = "tcp"
    }
  }
}
```

## Terraform Project Structure
* Ideally, you want to separate your development and production environments completely
* For complete isolation, it's best to create multiple AWS accounts, one for dev, one for prod, one for billing
* Splitting out terraform in multiple projects will also reduce the resources that need to be managed on `terraform apply`

E.g.
```
modules/vpc
modules/instances

dev/
prod/
```
## Terraform lock file
## Manipulating state
