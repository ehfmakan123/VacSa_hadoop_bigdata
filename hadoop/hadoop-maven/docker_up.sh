#!/usr/bin/env bash
export PJT_DIR_NAME=skeleton-hadoop-maven

if [ ! "$( docker container inspect -f '{{.State.Running}}' hadoop )" == "true" ]; then
    docker run -d -v c:/dev/skeleton-hadoop-maven:/home/hadoop/skeleton-hadoop-maven \
    -p 9870:9870 -p 8088:8088 -p 16010:16010 -p 10002:10002 -p 14040:4040 -p 9995:9995 \
    --name hadoop hibuz/zeppelin-dev yarn,hbase,hive
    sleep 2
    docker start hadoop
fi

if [[ "$OSTYPE" =~ ^msys  || "$OSTYPE" =~ ^cygwin ]]; then
    winpty docker exec -it hadoop sh -c "cd ~/skeleton-hadoop-maven && /bin/bash"
else
    docker exec -it hadoop sh -c "cd ~/skeleton-hadoop-maven && /bin/bash"
fi
