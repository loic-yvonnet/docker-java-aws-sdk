#!/bin/bash

# Directories
export shells_directory="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"
export root_directory=${shells_directory}/../..
export workspace_directory=${root_directory}/workspace
export tools_directory=${root_directory}/tools
export docker_directory=${tools_directory}/docker

# Docker
export docker_image=loic-yvo/ubuntu/aws/eclipse
export docker_image_version=${docker_image}:latest
export container_alias=awseclipse
export docker_compose_path=${docker_directory}/compose.yml
export docker_root_directory=/home/developer
