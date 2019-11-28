== Docker Container for AWS SDK for Java ==

This repo was an attempt to install and configure automatically the AWS Toolkit for Eclipse in a Docker container. At the time of writing, I did not find an easy and reliable way to install plugins to Eclipse via command line. Besides, the display port redirection did not work for me (even for a simple xclock). Hence, I could not run Eclipse from a Docker container.

This repo contains also a simple Amazon S3 Glacier sample that sends a simple file to a predefined Vault. Contrary to the code example on the Amazon Getting Started guide, the code here does not rely on deprecated APIs.

To run this code on Eclipse from Ubuntu, you need to:
* Create an AWS account.
* Log in to the AWS Console and create an Administrator account from the IAM console.
* Create a Secret Key from the IAM console.
* Create an Amazon S3 Glacier Vault named HighVaultageRock in the EU-WEST-3 region.
* `sudo apt-get install -y default-jre tar wget awscli`.
* The Eclipse version provided by Ubuntu does not work. Hence:
    * `wget http://mirror.ibcp.fr/pub/eclipse/technology/epp/downloads/release/2019-09/R/eclipse-jee-2019-09-R-linux-gtk-x86_64.tar.gz -O eclipse.tar.gz`
    * `sudo tar -xf eclipse.tar.gz /usr/bin`
    * `rm eclipse.tar.gz`
* Run Eclipse and install the AWS Toolkit from Help -> Install New Software (https://aws.amazon.com/eclipse).
* Log in with the Secret Key previously created.
* Open the `./workspace`.
* In the Run Configurations..., make sure that an environment variable is set: `AWS_REGION=eu-wast-3`.
* Build and run.
* Be sure to remember to delete the created Archive and the Vault.
