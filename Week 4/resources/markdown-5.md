# 3. Java REST API 만들기 (3)

## JPA & Hibernate를 이용해 H2 연결

우선 User 클래스를 엔티티로 만들자. User 클래스 이름 앞에 @Entity 어노테이션을 붙이고 프라이머리 키인 int id 앞에 @Id, @GeneratedValue 어노테이션을 붙이자. 그리고 앱을 실행하면 자동으로 User 엔티티가 생성될 것이다. 다만, USER라는 테이블 이름은 예약된 값이기 때문에 다른 이름을 지정해줘야한다. @Entity(name = "user_detail")과 같이 별도의 이름을 부여해주자.

```java
@Entity(name = "user_details")
public class User {

    @Id
    @GeneratedValue
    private int id;
    
    ...
}
```

앱을 실행하고 localhost:8080/h2-consle에 접속하면 USER_DETAILS라는 테이블 스키마가 만들어져 있는 것을 확인할 수 있을 것이다.
![h2-console](./h2-console-6.png)

그리고 데이터를 초기화하기 위해 src > main > resources에 data.sql 파일을 만들고 최초 데이터를 넣는 SQL을 입력하자.
```sql
insert into user_details (id, name, birth_date)
values (10002, 'BBB', current_date());

insert into user_details (id, name, birth_date)
values (10001, 'AAA', current_date());

insert into user_details (id, name, birth_date)
values (10003, 'CCC', current_date());
```

이대로 실행을 하면 에러가 날 것이다. 아직 스키마만 있고 테이블이 생성되지 않은 상황에서 데이터를 추가하려했기 때문이다. 그래서 데이터를 추가할 때, 테이블이 없을 시 테이블을 만드는 설정을 application.properties에 추가하도록 하자.
```
(application.properties)
...
spring.jpa.defer-datasource-initialization=true
...
```
