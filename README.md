# Spring Boot OAuth2


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
+-- security      // 인증 관련 핵심
+-- service       // 비즈니스 로직
+-- type          // 도메인 타입
+-- utils         // 유틸
+-- Application
```


## 구현 내용

1. 회원가입

2. 로그인
    * 토큰 발행
    
3. 요청 (토큰으로 인증)
    * any
    
    * admin
    
    * user
    
    
## 주요 시스템

1. Filter

    * 요청을 받아낸다.

2. AuthenticationManager

    * AuthenticationProvider 주머니
    
    * 등록된 Authentication Provider에 접근할 수 있는 유일한 객체

3. ProviderManager

    * 검색된 프로바이더에서 인증 객체를 처리

4. AuthenticationProvider
    
    * 진짜 인증이 일어나는 곳
    
    * 인증 가능 여부를 체크
    
    * 인증 객체를 만들어 돌려준다.
     
    * 필요에 맞게 구현하고 인증 관리자에 등록시키면 된다.

5. Authentication

    * 실제 인증 정보를 가지고 있는 곳

---

결국 우리가 구현해야 하는 것

1. 요청을 받아낼 필터

2. AuthenticationManager에 등록시킬 AuthenticationProvider

3. 인증 정보를 담을 Model

4. 각 인증에 따른 구현체

5. 성공 / 실패 핸들러

6. 인증에 사용할 Authentication 객체


## JWT

* JSON Web Token의 약자

* JWT가 좋은 이유는 토큰 자체가 데이터를 가지고 있다는 점이다.

* JWT 토큰은 .를 구분자로 세 부분으로 나누어져 있다.

    * JOSE Header
    
        * JWT 토큰을 어떻게 해석해야 하는지 명시 
    
    * JWT Claim Set
    
        * 실제 토큰의 바디로 토큰에 포함할 내용을 넣는다.
        
        * 토큰에 값을 넣어두면 데이터베이스 조회를 할 필요 없이 바로 확인 가능하다.
        
        * 그러므로 민감 데이터를 넣으면 안된다.
        
        * 인코딩 특성상 데이터가 많아지면 굉장히 커질 수 있으니 주의해야 한다.   
    
    * Signature
    
        * *가장 중요하다.*
        
        * 위변조 여부 검사
    
* Claims 기본값

    * iss: Issuer

    * sub: Subject

    * aud: Audience

    * exp: Expiration Time

    * nbf: Not Before

    * iat: Issued At

    * jti: JWT ID

## 외부 인증 연동 (TODO)


요즘은 Implicit Grant Flow이 대세

![Implicit Grant Flow](assets/implicit_grant_flow.png)


## 참고 내용

* http://www.bubblecode.net/en/2016/01/22/understanding-oauth2/

* https://tools.ietf.org/html/rfc7519