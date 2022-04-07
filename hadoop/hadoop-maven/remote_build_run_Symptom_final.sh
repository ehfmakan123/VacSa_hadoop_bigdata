#!/usr/bin/env bash

if [ "x$1" == "x" ]; then
  echo "usage: $0 j6<Team ID>@cluster.p.ssafy.io"
  exit 0
fi

# 소스 빌드
./mvnw clean package

# 하둡 클러스터 HDFS에 Symptom_final input 디렉토리 생성
ssh -i ../hadoop.pem $1 'bash -ic "hdfs dfs -rm -r Symptom_final/output"'

# 하둡 클러스터 서버에 jar 업로드
scp -i ../hadoop.pem target/ssafy-*.jar $1:~/ssafy.jar

# 맵리듀스 실행
ssh -i ../hadoop.pem $1 'bash -ic "hadoop jar ssafy.jar wordcount Symptom_final/input Symptom_final/output"'

# 실행 결과 조회
ssh -i ../hadoop.pem $1 'bash -ic "hdfs dfs -cat Symptom_final/output/*"'
