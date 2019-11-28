FROM ubuntu:latest

# Eclipse does not provide a clean "latest" link, at the time of writing
ARG EclipseMirror=http://mirror.ibcp.fr/pub/eclipse
ARG EclipseVersion=technology/epp/downloads/release/2019-09/R/eclipse-jee-2019-09-R-linux-gtk-x86_64.tar.gz
ARG EclipseSourceUrl=${EclipseMirror}/${EclipseVersion}
ARG EclipseTemp=/tmp/eclipse.tar.gz

# Dependencies
ARG DependenciesBasic="software-properties-common wget tar default-jre"
ARG DependenciesAwsCli="awscli"
ARG DependenciesEclipse="libgtk-3-0 libgtk-3-dev libcanberra-gtk-module libswt-cairo-gtk-3-jni libswt-gtk-3-java libswt-glx-gtk-3-jni libswt-gtk-3-jni"

# User ID and Group ID
ARG uid=1000
ARG gid=1000

RUN apt-get update && \
    echo "Get required utils for later installs" && \
    apt-get install -y ${DependenciesBasic} ${DependenciesAwsCli} ${DependenciesEclipse} && \
    apt-get update && \
    echo "Download and Install Eclipse (the default version on Ubuntu 18.04 LTS does not work at the time of writing)" && \
    wget ${EclipseSourceUrl} -O ${EclipseTemp} && \
    tar -xf ${EclipseTemp} -C /usr/bin && \
    rm ${EclipseTemp} && \
    echo "Clean up" && \
    apt-get clean autoclean && \
    apt-get autoremove -y && \
    rm -rf /var/lib/apt/lists/* && \
    echo "Create the develop user for security reasons" && \
    useradd -ms /bin/bash developer && \
    echo "Make sure the display can be forwarded to the host" && \
    echo "developer:x:${uid}:${gid}:Developer,,,:/home/developer:/bin/bash" >> /etc/passwd && \
    echo "developer:x:${uid}:" >> /etc/group && \
    echo "develop   ALL=(ALL:ALL) ALL" >> /etc/sudoers && \
    chown ${uid}:${gid} -R /home/developer

USER developer
WORKDIR /home/developer
VOLUME /home/developer
CMD /usr/bin/eclipse/eclipse
