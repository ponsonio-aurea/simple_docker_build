FROM gradle:jdk8

USER root

RUN apt-get update && \
    apt-get upgrade -y && \
    apt-get install software-properties-common git -y && \
    apt-get update
RUN apt-get update && apt-get install -y locales && rm -rf /var/lib/apt/lists/* \
    && localedef -i en_US -c -f UTF-8 -A /usr/share/locale/locale.alias en_US.UTF-8

ENV LANG en_US.UTF-8
