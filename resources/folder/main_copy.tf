variable "image_id" {
  type = string
}

provider "azure" {
  version = "3.3.0"
}

variable "availability_zone_names" {
  type    = list(string)
  default = ["us-west-1a"]
}

variable "test" {
}

variable "docker_ports" {
  type = list(object({
    internal = number
    external = number
    protocol = string
  }))
  default = [
    {
      internal = 8300
      external = 8300
      protocol = "tcp"
    }
  ]
}

variable "key" {
  type = "string"
}

variable "images" {
  type = "map"

  default = {
    us-east-1 = "image-1234"
    us-west-2 = "image-4567"
  }
}

variable "zones" {
  type = "list"
  default = ["us-east-1a", "us-east-1b"]
}