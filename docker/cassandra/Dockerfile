FROM ubuntu:16.04

# add cassandra repo
RUN echo "deb http://www.apache.org/dist/cassandra/debian 311x main" | tee -a /etc/apt/sources.list.d/cassandra.sources.list


RUN apt-get update && apt-get install -y curl
RUN curl https://www.apache.org/dist/cassandra/KEYS | apt-key add -
RUN apt-key adv --keyserver pool.sks-keyservers.net --recv-key A278B781FE4B2BDA

RUN apt-get update && apt-get install -y openssh-server openjdk-8-jdk cassandra

RUN mkdir /var/run/sshd
RUN echo 'root:THEPASSWORDYOUCREATED' | chpasswd
RUN sed -i 's/PermitRootLogin prohibit-password/PermitRootLogin yes/' /etc/ssh/sshd_config

# SSH login fix. Otherwise user is kicked off after login
RUN sed 's@session\s*required\s*pam_loginuid.so@session optional pam_loginuid.so@g' -i /etc/pam.d/sshd

ENV NOTVISIBLE "in users profile"
RUN echo "export VISIBLE=now" >> /etc/profile

EXPOSE 22

COPY conf/cassandra.yaml /etc/cassandra/cassandra.yaml
COPY entrypoint.sh entrypoint.sh

CMD ./entrypoint.sh
