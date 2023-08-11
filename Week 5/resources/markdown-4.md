# Spring Security


## 보안의 기초

어떠한 시스템에서든, 사용자는 자원을 가지고 있고 신원을 가지고 있다.
    - 자원: REST API, 어플리케이션, 데이터베이스 등
    - 신원: 자원에 접근하여 동작을 취하는 주체
그렇다면 다음과 같은 핵심적인 문제가 발생한다.
    ***어떻게 사용자를 식별할 것인가***
    ***어떻게 사용자에 따라 자원과 동작에 대한 접근을 구성할 것인가***
이러한 문제에 대한 대응이 각각 **인증(Authentication)**과 **인가(Authorization)**이다.

---

## 보안의 원칙

어떠한 보안의 강도는 가장 약한 부분의 강도와도 같다. 작은 결함도 전체 시스템을 망가뜨릴 수 있다.
안전한 시스템의 6가지 법칙
- 아무것도 믿지마라
- 최소한의 권한만 제공하라
- 완전한 중간지대를 만들어라
- 보안을 여러 레이어로 구현하라
- 최대한 간결하게 하라
- 개방성을 고려하라  

---

## Spring Security

Spring MVC는 다음과 같은 구조로 동작한다.

> REQUEST -> DISPATCHER SERVLET -> CONTROLLER(S)

스프링 애플리케이션으로 오는 모든 요청은 먼저 Dispatcher Servlet으로 간다. Dispatcher Servlet는 일종의 최초의 컨트롤러로서 작동하며, 최종 컨트롤러로 적절하게 라우팅하는 역할을 수행한다. 그런데 Spring Security를 도입하면 여기에 레이어가 하나 더 추가된다.

> REQUEST -> SPRING SECURITY -> DISPATCHER SERVLET -> CONTROLLER(S)

Spring Security가 Dispatcher Servlet에 앞서 모든 요청을 인터셉트한다. 그리고 들어오는 모든 요청에 대해 인증과 인가에 관한 처리를 선제적으로 수행한다.

Spring Security는 서로다른 기능을 제공하는 연속된 필터에 의해 동작한다.
    - Authentication: 유효한 유저인지 판단
    - Authorization: 적절한 권한을 갖춘 유저인지 판단
    - CorsFilter: Cross-Origin Resource Sharing(CORS)
    - CsrfFilter: Cross-Site Request Forgery(CSRF)
    - LogoutFilter, DefaultLoginPageGeneratingFilter, DefaultLogoutPageGeneratingFilter: 기본 로그인/로그아웃 페이지
    - ExceptionTranslationFilter: 발생하는 예외를 적절한 HTTP 응답으로 변환
필터의 순서는 매우 중요하며 크게 다음 순서로 구성한다.
    1. 기본 필터(CORS, CSRF 등)
    2. 인증 필터
    3. 인가 필터
    
---
    
## Authentication/Authorization Filter - 모든 것이 인증된다.

Spring Security 의존성(org.springframework.boot:spring-boot-starter-security)을 추가하고 앱을 실행하면 어떤 경로를 브라우저 창에 입력하더라도 기본 로그인 페이지가 뜨게된다.  
![browser](./browser-2.png)
 이로써 기본적으로 모든 리소스가 인증을 요구하도록 보호받게된다. 심지어 앱에서 정의하지 않은 경로도 같은 보호를 받는다. 인증을 하기 전엔 이 앱이 어떤 리소스를 제공하는지 유추도 할 수 없도록 하기 위함이다. 위에서 설명한 6가지 보안 원칙 중 3번째, '완전한 중간지대를 만들어라'가 이런 의미이다. 인증 필터를 통해 로그인 페이지라는 중간지대를 만들어 앱의 리소스를 1차적으로 보호하는 것이다.

이러한 인증 과정을 'form-based authentication'이라고 하는데 대부분의 웹 어플리케이션들이 적용하고 있는 것이다. 로그인을 하면 세션 쿠키가 생성이 되고 이후 일어나는 모든 요청에 이 세션 쿠키를 포함하게 된다. 로그인 페이지와 로그아웃 페이지를 포함하며 /logout URL을 제공한다.

참고로 아무런 설정이 없는 기본 상태에서 로그인 하려면 ID에는 user, Password에는 콘솔 창에 뜬 일시적 비밀번호를 찾아 입력하면 된다.
![log](./log-1.png) 

로그인을 한 후, 어떤 요청을 하고 결과를 성공적으로 받는다. 그리고 브라우저 개발자 도구 창을 열어 해당 요청에 대한 로그를 보면 헤더에 세션 쿠키 값이 있는 것을 확인할 수 있다.
![browser](./browser-3.png)

그리고 /logout 경로에 접속하면 이렇게 기본 로그아웃 페이지가 나타난다. 로그아웃 버튼을 누르면 다시 로그인 화면으로 돌아가게 된다.
![browser](./browser-4.png) 

브라우저가 아닌 다른 환경에서 API를 호출하면 로그인 페이지가 없으므로 다른 방법을 통해 인증을 하게된다. 예를들어 아이디를 "ade", 패스워드를 "haha" 라고 설정했다고 하자. 참고로 그러한 설정은 application.properties에서 다음과 같이 할 수 있다.
```
spring.security.user.name=ade
spring.security.user.password=haha
```
그리고 이 상태에서 {ID}:{PASSWORD} 형식의 스트링을 Base64로 인코딩한다. 즉, "ade:haha"를 Base64로 인코딩하여 "YWRlOmhhaGE="와 같은 스트링을 만드는 것이다. 그리고 이것을 HTTP 요청 헤더에 Authorization 필드에 "Basic YWRlOmhhaGE=" 처럼 넣어주면 로그인 한 것과 같은 효과를 발휘할 것이다. 포스트맨에서 친절하게 'Basic Auth'라는 기능을 통해 바로 이 것을 구현해준다.
![postman](./postman-1.png)
이렇게 아이디와 패스워드를 입력하고 요청을 날리면
![postman](./postman-2.png)
이처럼 "Authorization" 필드에 Base64 스트링이 들어가고 성공적으로 응답을 받아오는 것을 확인할 수 있다.

다만 이렇게 Spring Security가 기본적으로 제공하는 인증 필터는 편리하지만 너무나도 간단하고 원시적이기에 실제 서비스에선 쓰이지 않는다.

---

## CSRF Filer

은행 웹사이트에 접속하여 세션 쿠키가 브라우저에 저장되었다. 그리고 로그아웃을 하지 않은채로 어떤 악의적인 웹사이트에 접속했다. 그러면 그 웹사이트는 세션 쿠키를 이용해 내 계좌에서 이체를 수행할 수 있다. 이러한 행위를 CSRF, Cross-Site Request Forgery라고 한다.

CSRF를 예방하는 방법은 여러가지가 있는데 Spring Security에 기본 적용된 것은 '토큰 동기화'이다. API 요청이 이뤄질 때 마다 각각 다른 토큰을 생성한다. 그리고 새로운 API 동작을 수행할 때 마다 이전 동작에서 발생한 토큰을 요구하도록 한다. 만약 이 토큰이 이미 사용된 것이거나 전혀 다른 값이면 CSRF로 간주하여 에러를 반환한다. 예를들어 '/logout' 페이지의 소스를 살펴보자.
![browser](./browser-5.png)

"name"이 "_csrf"라고 되어있는 태그에 "value"로 임의의 긴 문자열이 들어가있다. 이것이 이전 API 요청에서 발생한 토큰이다. 만약 이 값이 없거나 적절하지 않은 값으로 바뀌면 로그아웃 버튼을 눌러도 에러만 반환될 것이다.  
