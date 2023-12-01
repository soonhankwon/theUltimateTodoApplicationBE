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
### Story.
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
- 그렇다면 Json 구조를 저장하고 조회하기 위한 적합한 데이터베이스는?
  - 바로 NoSQL이었습니다.
- NoSQL에서 이전에 사용해본 경험이 있고 일정 용량 Cloud를 무료로 사용할 수 있는 MongoDB를 선택했습니다.
  - Spring Data MongoDB를 사용하면 기존 Spring Data JPA와 유사한 방식으로 사용할 수 있어 생산성이 높아질 것이라는 생각도 한 가지 이유였습니다.

### Action.
---
- 도메인 클래스에 @Document를 활용해서 **컬렉션 도큐먼트**를 만들어주었습니다.
    - MemoStore: 사용자별로 딱 하나 가지고 있는 **루트 디렉토리 도큐먼트**
    - Directory: 디렉토리와 메모 도큐먼트 **컬렉션**을 가지고 있는 도큐먼트
    - Memo: 메모 도큐먼트
- MongoDB 컬렉션의 객체는 **ObjectId(RDB의 PK와 같은 개념)** 를 가져야하기 때문에 *`org.springframework.data.annotation.Id`* 의 **@ID**를 사용했습니다.

### Result.
---
- 복잡하지 않고 자연스럽게 구현된 디렉토리 - 메모 트리 구조
![memostore-response](https://github.com/soonhankwon/gold-digger-api/assets/113872320/14389fc5-eafe-4b62-b4bf-9e255ae298a6) 
- 모든 API를 ObjectId를 파라미터로 사용하게 함으로써 조회시 불필요한 탐색 방지
- 프론트엔드에서의 API 활용도
  - 메인 MemoStore 조회 API를 기반으로 부가적인 API를 사용하도록 설계함으로써 API를 활용하기 쉬웠다는 피드백을 받았습니다.

### MongoDB에서 도큐먼트간의 연관관계를 어떻게 구현해야할까?
---
## Story.
---
- Spring Data JPA에서는 @OneToOne, @OneToMany, @ManyToOne 등의 애노테이션을 통해 연관관계를 설정할 수 있었습니다.
- 하지만 Spring Data MongoDB는 처음 사용해보는 상황이었고 Directory에 연관된 Directory와 Memo를 모두 조회할 수 있어야 했습니다.
- 레퍼런스 검색 결과 MongoDB의 연관관계 설정 방법은?
    - **@DBRef** 애노테이션을 사용하는 것 입니다.

## Action.
---
- 요구사항을 구현하기 위해서는 1:N 관계를 만들어줘야 했습니다.
    - 디렉토리는 여러 디렉토리와 여러 메모를 가지고 있다.
- Spring Data MongoDB에서 1:N 관계를 설정하기 위해서 **List** 에 **@DBRef**를 적용시켜서 관계를 설정해주었습니다.
    <details>
    <summary><strong> @DBRef 1:N 관계 설정 CODE - Click! </strong></summary>
    <div markdown="1">
    
  ```java
        @Document(collection = "memo-stores")
        public class MemoStore {
        
            @Id
            private String id;
        
            @Indexed
            private String email;
        
            @DBRef
            private List<Directory> directories = new ArrayList<>();
        
            @DBRef
            private List<Memo> memos = new ArrayList<>();
        }
  ```
  </div>
  </details>
        
## Result.
---
- **@DBRef**를 활용한 **연관관계 설정**으로 디렉토리와 메모를 **조회, 생성, 드래그 앤 드랍** 시 이상없이 **연관된 객체들을 조회, 생성, 삭제** 할 수 있었습니다.

### MongoDB(NoSQL)에서 인덱스는 어떤식으로 만들며 적용해야할까?
---
## Story.
---
- 메모 서비스를 사용하는데 가장 중요한 객체는 **MemoStore** 였습니다.
    - MemoStore: 유저의 메모 저장소이며 모든 API가 해당 객체를 기반으로 작동 (**Master Table**이라고 생각할 수 있다.)
- API에서 반복적이고 빈번하게 사용되는 쿼리가 무엇인가 분석했습니다.
- 그것은 바로 **유저의 이메일로 메모 저장소를 조회**하는 쿼리입니다.
    - `Optional<MemoStore> findMemoStoreByEmail(String email);`
- 그렇다면 email을 인덱스로 만들어주면 좋겠는데, **MongoDB에서는 어떻게 하지?**
    - **@Indexed** 애노테이션을 사용해 인덱스를 만들어줄 수 있었습니다.

## Action.
---
- 간단하게 인덱스를 생성할 수 있겠다고 기대했었다, 하지만
  <details>
    <summary><strong> @Indexed 적용 CODE - Click! </strong></summary>
    <div markdown="1">       
    
    ```java
      @Document(collection = "memo-stores")
      public class MemoStore {
      
          @Id
          private String id;
      
          @Indexed(unique = true)
          private String email;
      		.....
      }
    ```
  </div>
  </details

- 문제발생: MongoDB에 들어가서 확인한 결과 인덱스가 생성되지 않았습니다.
- 이유: 레퍼런스를 조사한 결과 Spring Data MongoDB 3.0 부터 **자동 인덱스 생성이 디폴트로 꺼져있다.**
    - auto-index-creation=false
- 해결: application.properties 에서 MongoDB 설정을 바꿔주었습니다.
```groovy
spring.data.mongodb.auto-index-creation=true
```

## Result.
---
- 성공적으로 email 인덱스를 생성했습니다.
![memostore-index](https://github.com/soonhankwon/gold-digger-api/assets/113872320/7280dc99-8e73-45fc-9870-2e788ee0e9b7)
- 인덱스 미적용시 평균 76ms → 적용시 평균 65ms
- 개선율은 평균적으로 `13.16%`을 보였습니다.

### MongoDB를 사용한 TO-DO 도메인 MySQL로 마이그레이션 이슈
---

## TEAM PROJECT6S
### BACKEND
- 권순한, 강신규, 유지완, 김재민
### FRONTEND
- 박소미, 김서아, 양지원
