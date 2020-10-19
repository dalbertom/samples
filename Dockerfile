FROM ubuntu
RUN apt-get update && apt-get install -y git vim
WORKDIR /home
COPY . /home
