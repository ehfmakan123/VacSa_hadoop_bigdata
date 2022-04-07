#!/usr/bin/env bash

if [ "x$1" == "x" ]; then
  echo "usage: $0 j6<Team ID>@cluster.p.ssafy.io"
  exit 0
fi

# 하둡 클러스터 HDFS에 corona_final input 디렉토리 생성
ssh -i ../hadoop.pem $1 'bash -ic "hdfs dfs -mkdir -p corona_final/input"'

# 데이터 생성
ssh -i ../hadoop.pem $1 'bash -ic "hdfs dfs -put /home/j6a402/data/corona_final.txt corona_final/input/file01"'

# input 파일 내용 조회
ssh -i ../hadoop.pem $1 'bash -ic "hdfs dfs -cat corona_final/input/*"'

#j6a402@CLUSTER:
#j6a402@cluster.p.ssafy.io:/home/j6a402/data