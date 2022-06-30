
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

variable "test" {

}

variable bucket_settings {
  type = object({
    location           = string
    storage_class      = string
    versioning_enabled = bool
    bucket_policy_only = bool
    lifecycle_rules = map(object({
      action = map(string)
      condition = object({
        age                   = number
        with_state            = string
        created_before        = string
        matches_storage_class = list(string)
        num_newer_versions    = number
      })
    }))
  })
  default = {
        location           = europe-west4
        storage_class      = REGIONAL
        versioning_enabled = true
        bucket_policy_only = true
        lifecycle_rules = {
            bucket_policy_only = true
        }
        location = europe-west4
    }
}


variable "availability_zone_names" {
  type    = list(string)
  default = ["us-west-1a"]
}