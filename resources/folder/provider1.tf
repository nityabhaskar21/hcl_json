provider "aws" {
  version = "3.3.0"
}

provider "google" {
  alias = "google-dev"
  project = "acme-app"
  region  = "us-central1"
}

provider "gitlab" {
token = var.TERRA_TOKEN
base_url = "https://gitlab.com/api/v4/"
}