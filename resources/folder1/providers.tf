provider "aws" {
  version = "3.3.0"
}

provider "google" {
  project = "acme-app"
  alias = "google-dev"
  region  = "us-central1"
}

provider "oci" {}

provider "oci" {
  tenancy_ocid     = var.tenancy_ocid
  user_ocid        = var.current_user_ocid
  # fingerprint      = var.fingerprint
  # private_key_path = var.private_key_path
  region           = var.region
}
