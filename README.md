# 🩺척척박(Vac)사



1. [TOC]



### 📃 1. 서비스 소개

- 전염병 및 백신 관련 정보 제공
- 이상반응 빅데이터 활용



### 💡 2. 기획 배경

- 전염병과 백신에 대한 정보를 한곳에서 모아볼 수 없을까?

> 감염률, 사망률, 백신 접종률 및 각종 전염병에 대한 정보를 한번에!
>
> 선별진료소, 병원, 약국 정보까지!



- 백신 접종 후 나타난 이상반응 증상이 백신 부작용일까 걱정돼!

> 백신 부작용이 걱정된다면 커뮤니티에서 다른 사람들과 증상을 공유해보면 어떨까?



### ✒️ 3. 디자인

##### 컨셉

- 심플한 레이아웃 및 UI/UX
- 테마컬러 : `#0054FF`

##### 로고

<img src="README.assets/KakaoTalk_20220404_095743291.png" alt="KakaoTalk_20220404_095743291" style="zoom:150%;" />

### ⚙️ 4. 주요 기능

##### 홈



##### 지도



##### 게시판



##### 유용한 정보



##### 유저



### 💻 5. 기술 스택

##### Frontend

- Vue.js

##### Backend

- Java
- SpringBoot
- JPA

##### Database

- MySQL
- Hadoop

##### Infra

- EC2
- Jenkins
- Docker
- GitLab



### 🔧 6. 아키텍처

![image-20220405120633410](README.assets/image-20220405120633410.png)



### 🗃 7. 파일 구조

```
📁backend
├── 📁src
|    ├── 📁main
|    │   ├── 📁java/com/ssafy/vacsa
|    │   │   ├── 📁config
|    │   │   ├── 📁controller
|    │   │   ├── 📁dto
|    │   │   ├── 📁jwt
|    │   │   │   └── 📁service
|    │   │   ├── 📁model
|    │   │   ├── 📁repository
|    │   │   ├── 📁service
|    │   │   └── Application
|    │   └── 📁resources
|    │       └── application.properties
|    └── 📁test
├── 📁gradle
|    └── 📁wrapper
|
📁frontend
├── 📁public
├── 📁src
|　　├── 📁api
|　　├── 📁assets
|　　├── 📁components
│  　├── 📁
|    │   ├── 📁
|    │   ├── 📁
|    │   ├── 📁
|    │   ├── 📁
|    │   ├── 📁
|    │   └── 📁
|　　├── 📁config
|　　├── 📁plugins
|　　├── 📁router
|　　├── 📁store
|　　├── 📁views
|　　└── App.vue
├──.env.local
|
📁crawling
```



### 👨‍💻 8. 팀소개

- 김한주: 팀장 & BE & FE
- 김동규: BE & FE
- 김종범: BE & FE
- 안지애: BE & FE
- 전슬민: FE & 문서작성 & UCC
- 정해윤: BE & FE



### 📚 9. 기타 관련 정보

| 구분 | 이름         | 링크                                                         |
| ---- | ------------ | ------------------------------------------------------------ |
| 기획 | 기능명세     | [노션-기능명세](https://www.notion.so/5acf7bb9f43a473492e5468204a1d5e6) |
| 설계 | 와이어프레임 | [노션-와이어프레임](https://www.notion.so/30f0bd384c6e4efa92bac6df457c939d) |
| 설계 | ERD          | [노션-ERD](https://www.notion.so/ERD-3a3c9fbdd3c543eabf479d87af2b117d) |
| 기타 | 회의록       | [노션-회의록](https://www.notion.so/Commit-Convention-3ea6d39473b547f8b99cb398f191d3f4) |
| 기타 | 컨벤션       | [노션-컨벤션](https://www.notion.so/4192499153cb4980b2f49b87d852d7b9?v=3bae38ff901a471d9ea13d1f9e69e3f6) |
| 기타 | UCC          |                                                              |

