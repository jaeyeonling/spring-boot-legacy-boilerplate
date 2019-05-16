# Spring Boot Boilerplate


## 환경
* JDK 12
* Gradle 5.4
* Spring Boot 2.1
  * Web
  * Data JPA
* MySQL 8
* Redis 5
* Hibernate
* Lombok

## Docker
```
docker-compose -f docker-compose.yml up
```

## 폴더 구조
```
.
+-- config        // 설정 파일
+-- controller    // 컨트롤러
+-- entity        // DB 도메인 객체
+-- exception     // 에러 관련
+-- model         // 도메인 객체
+-- properties    // 설정 값
+-- repository    // DB 인터페이스
+-- service       // 비즈니스 로직
+-- type          // 도메인 타입
+-- utils         // 유틸
+-- Application
```