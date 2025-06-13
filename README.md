# 🗺️ Way-Post Back-End

[![Build](https://img.shields.io/badge/build-passing-brightgreen.svg)](https://github.com/your-org/way-post-back)
[![License: MIT](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.0+-brightgreen)](https://spring.io/projects/spring-boot)
[![MyBatis](https://img.shields.io/badge/MyBatis-2.3+-orange)](https://mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/)

> 여행 경험을 지도 기반으로 공유하는 소셜 커뮤니티, **Way-Post**의 백엔드 서버입니다.  
> Spring Boot + MyBatis 기반으로 구축되었으며, Swagger(OpenAPI 3.0)로 REST API 문서를 제공합니다.

---

## 🚀 주요 기능

- 🔐 회원가입 / 로그인 (OAuth2 포함)
- 📬 이메일 인증 시스템
- 📍 게시물 작성 및 조회 (위치 기반)
- ❤️ 좋아요 및 댓글 기능 (프론트 연동 예정)
- 📦 Swagger(OpenAPI 3.0) 기반 API 문서 자동화

---

## 🧰 기술 스택

| 분류 | 사용 기술 |
|------|-----------|
| Language | Java 17 |
| Framework | Spring Boot 3.x |
| ORM | MyBatis |
| DB | MySQL |
| 인증 | Spring Security + JWT + OAuth2 (Google, Naver, Kakao) |
| API 문서화 | Springdoc + Swagger UI |
| 빌드 | Maven |
| 배포 환경 | (선택사항: AWS, Docker 등) |

---

## 📦 사용 라이브러리 (pom.xml 기준)

- `com.mysql:mysql-connector-j`
- `io.jsonwebtoken:jjwt-api`
- `io.jsonwebtoken:jjwt-impl`
- `io.jsonwebtoken:jjwt-jackson`
- `org.locationtech.jts:jts-core`
- `org.mybatis.spring.boot:mybatis-spring-boot-starter`
- `org.mybatis.spring.boot:mybatis-spring-boot-starter-test`
- `org.projectlombok:lombok`
- `org.springdoc:springdoc-openapi-starter-webmvc-ui`
- `org.springframework.boot:spring-boot-devtools`
- `org.springframework.boot:spring-boot-starter-aop`
- `org.springframework.boot:spring-boot-starter-data-redis`
- `org.springframework.boot:spring-boot-starter-mail`
- `org.springframework.boot:spring-boot-starter-oauth2-client`
- `org.springframework.boot:spring-boot-starter-security`
- `org.springframework.boot:spring-boot-starter-test`
- `org.springframework.boot:spring-boot-starter-thymeleaf`
- `org.springframework.boot:spring-boot-starter-validation`
- `org.springframework.boot:spring-boot-starter-web`
- `org.springframework.security:spring-security-test`
- `org.thymeleaf.extras:thymeleaf-extras-springsecurity6`

---

## 🏗️ 프로젝트 구조

```
way-post-back/
├── src/
│   └── main/
│       ├── java/com/waypost/waypost/
│       │   ├── controller/   # REST API 컨트롤러
│       │   ├── service/      # 비즈니스 로직
│       │   ├── repository/   # DB 접근 계층
│       │   ├── dto/          # 요청/응답 객체
│       │   ├── entity/       # 도메인 엔티티
│       │   └── config/       # Swagger, Security 설정
│       └── resources/
│           └── mapper/       # MyBatis XML 매퍼
```

---

## 🧪 Swagger API 문서 사용 방법

> OpenAPI 3.0 스펙 기반의 자동 문서가 제공됩니다.

### ✅ 접속 경로
```
https://way-post-back.onrender.com/swagger-ui.html
```

### ✅ Swagger에 인증 헤더 추가 (JWT 사용 시)
- Swagger UI 오른쪽 상단 `Authorize` 버튼 클릭
- 다음 형식으로 입력:
```
Bearer {your_jwt_token}
```

---

## 🛠️ 실행 방법

```bash
# 1. Git clone
git clone https://github.com/your-org/way-post-back.git

# 2. DB 설정 (application.yml 또는 application.properties)

# 3. 빌드 및 실행
./mvnw spring-boot:run
```

---

## 📂 주요 API

- ✅ `/api/posts` - 게시물 등록 및 조회
- ✅ `/auth/signin` - 로그인
- ✅ `/email/send` - 이메일 인증 발송
- ✅ `/oauth2/redirect` - 소셜 로그인 리디렉션

> 전체 엔드포인트는 Swagger에서 확인 가능

---

## 🙋‍♂️ 기여 가이드

1. 이슈 또는 피처 브랜치 생성
2. Pull Request 생성 및 리뷰 요청
3. Merge 전 테스트 필수

---

## 📄 License

본 프로젝트는 MIT License 하에 공개되어 있습니다.
