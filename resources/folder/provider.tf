provider "aws_test" {
    # region = "ap-south-1"
    version = "= 3.3.0"
}

provider "template" {
    version = "= 2.1.2"
}

provider "vault" {
    version = "= 2.12.2"
}

terraform {
  backend "consul" {
    address = "localhost"
    scheme  = "http"
    path    = "cmp/tf/states/ca1/ops"
  }
}
