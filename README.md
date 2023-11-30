# EasyLog - 가볍고 쉬운 메모 & 일정관리 웹 서비스 백엔드 API
- 조금 더 간편하게 쓸 수 있고, 가볍고 쉬운 기록 & 일정관리용 애플리케이션
- Service Link: https://memo-fe-woad.vercel.app/
- 시연 영상: https://youtu.be/PQMUY5w_oDo
<details>
<summary><strong> 고용노동부 풀스택 개발자 양성과정 우수 프로젝트(1위) 수상 - Click! </strong></summary>
<div markdown="1">
  <img src="https://github.com/soonhankwon/gold-digger-api/assets/113872320/99d28f0c-0261-434c-9590-aefea93dae7e" align="center" height=250px />
</div>
</details>
<br/>

## Table Of Contents
- [아이디어](#아이디어)
- [프로젝트 포인트](#프로젝트-포인트)
- [유저스토리](#유저스토리)
- [기술스택](#기술스택)
- [아키텍처](#아키텍처)
- [API 명세서](#api-명세서)
- [ERD](#erd)
- [핵심문제 해결과정 및 전략](#핵심문제-해결과정-및-전략)
- [TEAM PROJECT6S](#TEAM-PROJECT6S)
<br/>

## 아이디어
- 조금 더 간편하게 쓸 수 있고, 가볍고 쉬운 기록용 + 일정관리용 애플리케이션이 있었으면 좋겠다.
- 흩어져 있는 각각의 기능과 기록들을 한군데로 통합하고 싶다.
- 내가 하나하나 form에 입력을 하지 않아도 하나의 문장만 입력하면 자동으로 일정을 추가해주면 좋겠다.
- 모바일, 웹을 통합해서 사용이 가능하면 좋겠다.
- 카톡에 아무렇게나 적어둔 메모도 저장해주는, 신경쓰지 않아도 한 곳에서 다 모아볼 수 있는 애플리케이션
- 내가 불편한 것은 남에게도 불편할 수 있으니까!
<br/>

## 프로젝트 포인트
- 본연의 기능에 충실하게!
- 브라우저 기반, 모바일 환경과 데스크탑 환경의 통합: 어디에서나 사용할 수 있도록!
- 단 한 곳에서 간단하고 가볍게 기록 및 일정을 관리할 수 있다.
- 기존 애플리케이션에는 없는 편의 기능을 통해 편의성 증대 (Quick Input, 카카오 채팅 연동)
<br/>

## 유저스토리
- 유저는 본 사이트에 들어와 카카오 소셜 로그인을 통해 서비스를 이용합니다. 
- 메모 서비스
  - 사용자는 메모를 제목, 내용을 작성하여 등록합니다. 언제든지 수정 및 삭제 할 수 있습니다.
  - 디렉토리 트리 구조로 디렉토리 안에 디렉토리와 메모를 등록할 수 있고, 드래그 앤 드랍 및 수정, 삭제 할 수 있습니다.
- TO-DO 서비스
  - 사용자는 TO-DO와 캘린더에 직접 입력을 하여 TO-DO를 등록할 수 있습니다. 
  - 퀵 인풋에 입력 시 AI 파싱을 통해 자동으로 날짜에 맞춰 캘린더 등록 및 TO-DO를 등록할 수 있습니다.
- 카카오 채널 채팅 연동
  - 사용자는 모바일에서 카카오 채널(비즈니스 채널)에 메모를 입력하여 메모를 등록할 수 있습니다.
<br/>

## 기술스택
### 언어 및 라이브러리
- Java 17
- SpringBoot 3.1.5
- Spring Data JPA 3.1.5
- Spring Data MongoDB 3.1.5
- Spring Validation 3.1.5
- Spring WebFlux 6.0.13
- Spring Security 6.1.5
- Spring Oauth2
- JJWT 0.12.3
- Swagger v3 2.2.9
### 데이터베이스
- MySQL 8.0.33
- MongoDB
### DevOps
- AWS EC2
- AWS RDS
- MongoDB Cloud
- GitHub Actions
- Docker
<br/>

## 아키텍처
![ultimate drawio](https://github.com/soonhankwon/tech-mentor-backend/assets/113872320/5dca77e6-d145-48a6-87c1-61ca0f87032d)

## api 명세서
- Swagger : https://easylog.shop/swagger-ui/index.html#/
<br/>

## erd
### RDB ERD
![rdb-erd](https://github.com/soonhankwon/gold-digger-api/assets/113872320/d7daf492-51f1-4284-8b80-359eadc1d03b)

### NoSQL ERD
![memo-nosql-erd drawio](https://github.com/soonhankwon/gold-digger-api/assets/113872320/f2aede5c-e9f3-4969-beee-aa6e6a496e3f)
<br/>

## 핵심문제 해결과정 및 전략
### 디렉토리 - 메모 트리 구조를 어떻게 구현해야 효율적일까?
---
- 기능 요구사항을 만족시키기 위해 디렉토리 안에 디렉토리 리스트와 메모 리스트가 들어있는 구조를 구현해야 했습니다.
  - 프론트에서는 유저에게 디렉토리 구조를 보여줘야 함 (ex Window 탐색기)
- 메모 서비스의 특성상 쓰기, 수정 비율이 높을 것으로 예상되었습니다.
- RDB를 사용한다면?
  - 다음과 같은 Entity Class를 만들어 주는 것을 생각할 수 있습니다.
    <details>
    <summary><strong> RDB Entity Class - Click! </strong></summary>
    <div markdown="1">       
    
    ````java
    @NoArgsConstructor
    @Entity
    public class MemoRdb {
    
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
    
        private String title;
    
        private String content;
    
        @ManyToOne
        @JoinColumn(name = "directory_id")
        private DirectoryRdb directory;
    
        @ManyToOne
        @JoinColumn(name = "user_id")
        private User user;
    }
    
    @NoArgsConstructor
    @Entity
    public class DirectoryRdb {
    
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
    
        private String name;
    
        @ManyToOne
        @JoinColumn(name = "user_id")
        private User user;
    
        @ManyToOne
        @JoinColumn(name = "parent_directory_id")
        private DirectoryRdb parentDirectory;
    
        @ManyToOne
        @JoinColumn(name = "child_directory_id")
        private DirectoryRdb childDirectory;
    
        @ManyToOne
        @JoinColumn(name = "memo_id")
        private MemoRdb memo;
    }
    ````
  </div>
  </details>

  - 디렉토리 계층 구조를 구현하기위해 parent, child directory FK가 필요합니다.
  - 메모, 디렉토리 둘 다 **유저 FK**가 필요합니다.
  - 백엔드 로직에서 필연적으로 많은 **Join**이 발생할 것을 예상할 수 있습니다.
- 가장 큰 문제점 → 복잡도
  - 프론트에서 유저에게 한번에 디렉토리 구조를 보여줘야 하는데 그렇다면 디렉토리에 Depth라는 프로퍼티가 필요해집니다.
  - 디렉토리를 펼칠때마다 DB call 발생 문제 등이 발생합니다.
  - 정교하게 요소를 추가해서 구현할 수는 있겠지만, 부자연스럽게 복잡도가 올라간다는 느낌을 받았습니다.
- 디렉토리 구조엔 구조적으로 NoSQL이 맞는것이 아닐까?
  - 일반적인 Json 응답을 보면 계층구조의 응답 형식을 많이 접할 수 있습니다.
- 그렇다면 Json 구조를 나타내기 위한 적합한 데이터베이스는?
  - 바로 NoSQL이었습니다.
- NoSQL에서 이전에 사용해본 경험이 있고 일정 용량 Cloud를 무료로 사용할 수 있는 MongoDB를 선택했습니다.
  - Spring Data MongoDB를 사용하면 기존 Spring Data JPA와 유사한 방식으로 사용할 수 있어 생산성이 높아질 것이라는 생각도 한 가지 이유였습니다.
    
### MongoDB에서 도큐먼트간의 연관관계를 어떻게 구현해야할까?
---

### MongoDB(NoSQL)에서 인덱스는 어떤식으로 만들며 적용해야할까?
---

### MongoDB를 사용한 TO-DO 도메인 MySQL로 마이그레이션 이슈
---

## TEAM PROJECT6S
### BACKEND
- 권순한, 강신규, 유지완, 김재민
### FRONTEND
- 박소미, 김서아, 양지원
