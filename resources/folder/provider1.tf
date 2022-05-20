provider "aws" {
  version = "3.3.0"
}

provider "google" {
  alias = "google-dev"
  project = "acme-app"
  region  = "us-central1"
}