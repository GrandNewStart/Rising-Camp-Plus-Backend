# 2. Java 웹앱 만들기

## 목표: 모던 스프링 부트 접근법을 통해 투두 리스트 웹앱을 만든다.

### 프로젝트 생성
다음과 같은 설정으로 프로젝트를 생성한다.

![spring initializr](./spring-initializr-2.png)

---

### JSP로 리디렉션하여 웹페이지 띄우기

1. build.gradle에 다음과 같은 의존성을 추가한다.
```groovy
implementation 'org.apache.tomcat.embed:tomcat-embed-jasper'
```
2. application.properties에는 다음과 같은 두줄을 입력한다. 이는 앞으로 jsp 파일의 경로를 호출할 때 편하게 하기위해 미리 경로와 확장자를 선언해놓는 것이다.
```
(application.properties)

spring.mvc.view.prefix=/WEB-INF/jsp/
spring.mvc.view.suffix=.jsp
```

3. src > main > resources > META-INF > resources > WEB-INF > jsp 디렉터리를 생성한다. 그리고 여기에 jsp 파일을 저장할 것이다. 다음과 같은 간단한 jsp 파일을 작성해보자.
```html
(hello.jsp)

<html>
    <head>
        <title>Hello, World!</title>
    </head>
    <body>
        This is amazing!
    </body>
</html>
```

4. 이제 다음과 같은 Controller 클래스를 만들자.
```java
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

    @RequestMapping("say-hello-jsp")
    public String sayHelloJSP() {
        return "hello";
    }

}
```

이렇게 하고 앱을 실행한다. 브라우저에 'localhost:8080/say-hello-jsp'를 치면 해당 HTML 파일이 렌더링 된 모습을 볼 수 있다.

![browser](./browser-1.png)

---

### 로그인 화면

같은 방식으로 로그인 페이지를 만들어보자. src > main > resources > META-INF > resources > WEB-INF > jsp > login.jsp 파일을 만들고 다음과 같이 HTML을 입력하자. 여기서 'name'이라는 자바 변수를 가져다 쓰려고 한다. ${} 이렇게 변수를 표현하는 것을 Expression Language라고 한다. 그럼 이 name 값은 어디서 오는가.
```html
<html>
    <head>
        <title>Log In</title>
    </head>
    <body>
        Welcome ${name}
    </body>
</html>
```

다음과 같이 ModelMap이라는 것을 이용한다. 아래 함수는 localhost:8080/login?name={name} 처럼 쿼리로 변수 name을 받고 있는데, 이 값을 그대로 ModelMap에 전달해주면 된다.
```java
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LogInController {

    @RequestMapping(value = "login")
    public String goToLogInPage(@RequestParam String name, ModelMap model) {
        model.put("name", name);;
        return "login";
    }

}
```

앱을 실행하고 브라우저에서 localhost:8080/login?name=Jinwoo를 호출하면 다음과 같은 화면이 뜰 것이다.
 
![browser](./browser-2.png)

이제 실제 입력창을 만들어보자. login.jsp를 다음과 같이 변경한다.
```html
<html>
    <head>
        <title>Log In</title>
    </head>
    <body>
        Welcome
        <form method="post">
            Name: <input type="text" name="name"/>
            Password: <input type="password" name="password"/>
            <input type="submit">
        </form>
    </body>
</html>
```

로그인에 성공했을 때의 화면을 welcome.jsp로 다음과 같이 작성한다. name과 password 변수를 전달받는 것을 알 수 있다.
```html
<html>
    <head>
        <title>Welcome</title>
    </head>
    <body>
        <div>Welcome</div>
        <div>Your name: ${name}</div>
        <div>Your password: ${password}</div>
    </body>
</html>
```

그리고 LogInController는 다음과 같다.
```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LogInController {

    // 'localhost:8080/login' 으로 접속했을 때
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String goToLogInPage() {
        return "login";
    }

    // 'localhost:8080/login'에서 'submit' 버튼을 눌러 POST 메서드를 실행했을 때
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String goToWelcomePage(@RequestParam String name, @RequestParam String password, ModelMap map) {
        map.put("name", name);
        map.put("password", password);
        return "welcome";
    }

}

```

---

### 로깅하는 방법

```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LogInController {

    // org.slf4j의 Logger 객체를 만들어준다.
    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "login")
    public String goToLogInPage(@RequestParam String name, ModelMap model) {
        model.put("name", name);
        // debug를 호출하면 로그 레벨이 'debug'일 때만 노출된다.
        logger.debug("Request param is {}", name);
        // info를 호출하면 로그 레벨이 'info'일 때만 노출된다.
        logger.info("Request param is {}", name);
        // 'warn' 레벨도 있다.
        logger.warn("Request param is {}", name);
        return "login";
    }

}
```

로그 레벨은 application.properties에서 클래스 단위로 설정할 수 있다.
```
(application.properties)

logging.level.org.springframework=info // 전체에 대해선 info
logging.level.com.ade.myfirstwebapp.login.LogInController=debug // LogInController에 대해선 debug
```

---

### 아키텍쳐

- Model 1 Architecture
    - 모든 코드가 View에 집중되어있다.
    - 아주 복잡한 JSP.
    - 유지보수 힘듦.
- Model 2 Architecture
    - Model - Controller - View의 분리된 구조.
    - 유지보수 쉬움.
    - 하지만 컨트롤러 사이에 공통된 기능이 있을 때 분리하기 힘듦.
- Model 2 Front Controller
    - 모든 흐름이 단일 컨트롤러로 집중 (프론트 컨트롤러)
    - 공통 기능은 프론트 컨트롤러에 구현
    - 하위 컨트롤러로 세분화.
- Dispatcher Servlet
    - 프론트 컨트롤러가 HTTP 요청 수신
    - 올바른 컨트롤러 메서드를 확인하고 해당 컨트롤러에게 작업 위임.
    - 프론트 컨트롤러가 HTTP 응답 발신
