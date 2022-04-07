## 1. 하둡 클러스터에서 wordcount 실행

```bash

j6a402@cluster.p.ssafy.io

# C 드라이브 dev 폴더에 소스코드 압축 해제(C:\dev\skeleton-hadoop-maven) 후 Git Bash 실행
# (인증서는 프로젝트 상위 예시->c:/dev/hadoop.pem 에 위치시킴)
# (다른위치라면 remote_*.sh 파일들에서 hadoop.pem 및 위치정보 수정)
$ cd c:/dev/skeleton-hadoop-maven

# 하둡 클러스터 서버에 환경변수를 설정(.bashrc)합니다. (서버당 1번만 실행)
$ ./remote_set_hadoop_env.sh j6a402@cluster.p.ssafy.io

# 하둡 클러스터에 wordcount input 데이터 생성 (1번만 실행)
# 데이터에 따라 <> 안을 다르게 설정
$ ./remote_prepare_<corona>_data.sh j6a402@cluster.p.ssafy.io

# 로컬에서 빌드 후 빌드된 jar를 하둡 클러스터에 업로드 후 wordcount 예제 실행
# 데이터에 따라 <> 안을 다르게 설정
$ ./remote_build_run_<corona>.sh j6a402@cluster.p.ssafy.io
```

# 2. sqoop을 사용하여 mysql 과 연동

```bash

#  database list 출력 (연결 확인용)
$ sqoop list-databases --connect jdbc:mysql://localhost:3306 --username <username> --P

# mysql에서 데이터 import
$ sqoop import --connect jdbc:mysql://localhost:3306/<database> --table <table> --target-dir /user/hadoop/<dir> --username <username> --P -m 1

# mysql로 데이터 export
$ sqoop export --connect jdbc:mysql://localhost:3307/<database> --table <table> --export-dir /user/hadoop/<dir>/part-m-00000 --username <username> --P
```
