#!/usr/bin/env bash

if [ "x$1" == "x" ]; then
  echo "usage: $0 j6<Team ID>@cluster.p.ssafy.io"
  exit 0
fi

# 하둡 클러스터 HDFS에 Symptom_final input 디렉토리 생성
ssh -i ../hadoop.pem $1 'bash -ic "hdfs dfs -mkdir -p Symptom_final/input"'

# 데이터 생성
ssh -i ../hadoop.pem $1 'bash -ic "hdfs dfs -put /home/j6a402/data/Symptom_final.txt Symptom_final/input/file01"'

# input 파일 내용 조회
ssh -i ../hadoop.pem $1 'bash -ic "hdfs dfs -cat Symptom_final/input/*"'

#j6a402@CLUSTER:
#j6a402@cluster.p.ssafy.io:/home/j6a402/data