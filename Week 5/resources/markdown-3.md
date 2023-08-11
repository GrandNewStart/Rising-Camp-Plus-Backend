# 구글 로그인 구현

참고: https://developers.google.com/identity/openid-connect/openid-connect

## 구글 클라우드 설정

1. [구글 클라우드 콘솔](https://console.cloud.google.com/)에 접속한다.

2. 프로젝트가 없으면 프로젝트를 만든다.

3. ['API 및 서비스'](https://console.cloud.google.com/apis/dashboard) 탭을 선택한다.

4. '사용자 인증 정보'를 선택한다.

5. '+ 사용자 인증 정보 만들기' > 'OAuth 클라이언트 ID'를 선택한다.

6. 애플리케이션 유형은 '웹 애플리케이션'으로 선택.

7. 애플리케이션 이름은 대충 입력하고 '승인된 리디렉션 URI'에 'localhost:8080/login/oauth2/code/google'을 입력한다.

8. '만들기'를 누르면 '클라이언트 ID'와 '클라이언트 보안 비밀번호'가 생성된 것을 확인할 수 있다.

---

## 인증 과정

구글 클라우드가 설정되었으면 이제부터 다음과 같은 프로토콜을 따라 사용자 인증을 구현하게 될 것이다.

1. 위조 방지 상태 토큰(anti-forgery state token)을 생성한다.
    
    위조 방지 상태 토큰이란 요청 위조 공격을 방지하기 위한 앱 서비스와 유저간의 상태를 나타내는 유일한 세션 토큰이다. 추후에 구글의 인증 응답값과 이 토큰 값을 대조하며 올바른 유저가 요청을 한 것인지 판단하게 된다. 이 토큰을 흔히 CSRF(cross-site request forgery)토큰이라고 한다. 이 토큰은 보통 고성능 난수 생성기를 이용해 뽑아낸 30자 이상의 문자열을 사용하거나 특정한 상태 변수 값을 백엔드에 저장된 비밀키로 해시한 값을 사용하기도 한다. 다음은 Java로 세션 토큰을 만들어내는 예시이다.
    
2. 구글에 인증 요청을 보낸다.

    구글에서 제공하는 'Discovery document'라는 JSON 파일이 있다. 이는 다음 URI에 HTTPS GET 요청으로 받을 수 있다.
    
    > https://accounts.google.com/.well-known/openid-configuration
    
    이 JSON 문서엔 'authorization_endpoint'라는 키를 가지는 URI가 있는데, 이 URI로 구글에 인증 요청을 날리게 될 것이다. 요청에 들어갈 파라미터는 다음과 같다.
    
    - client_id: (필수) 아까 구글 클라우드를 설정하면서 만든 클라이언트 ID 값이다.
    - response_type: (필수) "code"를 사용한다.
    - scope: (필수) 반환받을 이용자 정보의 범위이다. "openid email"이 기본이다.
    - redirect_uri: (필수) 이 요청에 대한 응답을 받을 URI이다.
    - state: (선택이지만 필수 권장) 1번에서 생성한 위조 방지 상태 토큰이다.
    - nonce: (필수) 리플레이 공격을 방지하기 위한 랜덤 값이다.
    - 이외 여러가지 선택 항목들이 있는데 [여기](https://developers.google.com/identity/openid-connect/openid-connect#authenticationuriparameters)서 확인하자.
    
    결과적으로 다음과 같은 요청을 authorization_endpoint로 날리게 될 것이다.
    
    ```
    https://accounts.google.com/o/oauth2/v2/auth?
        response_type=code&
        client_id=424911365001.apps.googleusercontent.com&
        scope=openid%20email&
        redirect_uri=https%3A//oauth2.example.com/code&
        state=security_token%3D138r5719ru3e1%26url%3Dhttps%3A%2F%2Foauth2-login-demo.example.com%2FmyHome&
        login_hint=jsmith@example.com&
        nonce=0394852-3190485-2490358&
        hd=example.com
    ```

3. 위조 방지 상태 토큰을 검증한다.

    

4. 코드를 액세스 토큰/ID 토큰과 교환한다.

5. ID 토큰으로부터 사용자 정보를 획득한다.

6. 사용자를 인증한다.

---

## 프로젝트 설정

스프링 부트 프로젝트에 다음과 같은 의존성을 추가한다.
```groovy
dependencies {
    ...
    implementation 'org.springframework.boot:spring-boot-starter-web'
    implementation 'org.springframework.boot:spring-boot-starter-security'
    implementation 'org.springframework.boot:spring-boot-starter-oauth2-client'
    ...
}
``` 


