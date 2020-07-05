terraform apply -auto-approve


https://appian.udemy.com/course/learn-devops-infrastructure-automation-with-terraform/learn/lecture/16667146#overview
https://github.com/wardviaene/terraform-course

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
