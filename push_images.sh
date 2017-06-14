#!/bin/bash
# push docker images to hub.docker.com

for file in `ls .`
do
  if [ -d ./$file ]
  then
    cd $file && mvn docker:build -DpushImage -DskipTests && cd ..
  fi
done