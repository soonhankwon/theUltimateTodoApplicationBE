# EasyLog - 가볍고 쉬운 메모 & 일정관리 웹 서비스 백엔드 API
- 조금 더 간편하게 쓸 수 있고, 가볍고 쉬운 기록 & 일정관리용 애플리케이션
- Service Link: https://memo-fe-woad.vercel.app/
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
  - 디렉토리와 메모 구조로 디렉토리 안에 디렉토리와 메모를 등록할 수 있고, 드래그 앤 드랍을 할 수 있습니다. 
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

## 핵심문제 해결과정 및 전략

## TEAM PROJECT6S
