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
