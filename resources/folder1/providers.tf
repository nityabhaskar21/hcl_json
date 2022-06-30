provider "aws" {
  version = "3.3.0"
}

provider "google" {
  project = "acme-app"
  alias = "google-dev"
  region  = "us-central1"
}

provider "oci" {}
